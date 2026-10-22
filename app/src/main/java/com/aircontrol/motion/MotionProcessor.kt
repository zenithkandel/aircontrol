package com.aircontrol.motion

import com.aircontrol.model.MotionDisplacement
import com.aircontrol.model.SettingsState
import kotlin.math.abs
import kotlin.math.min
import kotlin.math.sqrt

/**
 * High-Precision Angular Velocity Motion Processing Engine.
 *
 * Implements:
 * 1. Gyroscope bias offset subtraction (stationary calibration)
 * 2. Continuous deadzone slope ramp (eliminates micro-tremors without step jumps)
 * 3. First-order IIR low-pass smoothing filter (EMA alpha = 0.45)
 * 4. Dynamic non-linear velocity acceleration curve
 * 5. Sub-pixel fractional accumulator
 * 6. Clamping to valid 8-bit signed HID bounds [-127, 127]
 */
class MotionProcessor(private val settings: SettingsState) {

    private var isPaused: Boolean = false

    // Filter memory
    private var filteredRateX: Float = 0.0f
    private var filteredRateY: Float = 0.0f

    // Sub-pixel fractional accumulators
    private var accumulatedX: Float = 0.0f
    private var accumulatedY: Float = 0.0f

    // Gyroscope bias offsets
    var biasX: Float = 0.0f
        private set
    var biasY: Float = 0.0f
        private set
    var biasZ: Float = 0.0f
        private set

    // Calibration state
    private var isCalibrating: Boolean = false
    private var calibSampleCount: Int = 0
    private var calibSumX: Float = 0.0f
    private var calibSumY: Float = 0.0f
    private var calibSumZ: Float = 0.0f
    private var calibMaxMotion: Float = 0.0f

    var onCalibrationComplete: ((Boolean) -> Unit)? = null

    fun reset() {
        filteredRateX = 0.0f
        filteredRateY = 0.0f
        accumulatedX = 0.0f
        accumulatedY = 0.0f
    }

    fun setPaused(paused: Boolean) {
        isPaused = paused
        if (paused) reset()
    }

    fun isPaused(): Boolean = isPaused

    fun startCalibration() {
        isCalibrating = true
        calibSampleCount = 0
        calibSumX = 0.0f
        calibSumY = 0.0f
        calibSumZ = 0.0f
        calibMaxMotion = 0.0f
    }

    fun isCalibrating(): Boolean = isCalibrating

    /**
     * Process raw gyroscope rates (in deg/s) into discrete mouse displacement.
     * When holding phone in hand pointed towards screen:
     * - Yaw (turning left/right): drives Cursor X
     * - Pitch (tilting up/down): drives Cursor Y
     */
    fun process(rawGxDeg: Float, rawGyDeg: Float, rawGzDeg: Float): MotionDisplacement {
        if (isCalibrating) {
            handleCalibrationSample(rawGxDeg, rawGyDeg, rawGzDeg)
            return MotionDisplacement(0, 0, 0, false)
        }

        if (isPaused) {
            reset()
            return MotionDisplacement(0, 0, 0, false)
        }

        // 1. Gyro Bias Subtraction
        val gx = rawGxDeg - biasX
        val gy = rawGyDeg - biasY
        val gz = rawGzDeg - biasZ

        // 2. Axis Mapping (Portrait remote hold: Yaw around Z/Y axis, Pitch around X axis)
        // With phone in portrait held like a remote:
        // Yaw = Z-axis (or -Y if tilted upright)
        // Pitch = X-axis (tilting nose up/down)
        var rawRateX = gz
        if (settings.invertX) {
            rawRateX = -rawRateX
        }

        var rawRateY = gx
        if (settings.invertY) {
            rawRateY = -rawRateY
        }

        // 3. Continuous Deadzone Suppression
        val cleanRateX = applyDeadzone(rawRateX, settings.deadzoneDps)
        val cleanRateY = applyDeadzone(rawRateY, settings.deadzoneDps)

        // 4. Low-Pass Smoothing Filter (Exponential Moving Average)
        val alpha = settings.filterAlpha
        filteredRateX = (alpha * cleanRateX) + ((1.0f - alpha) * filteredRateX)
        filteredRateY = (alpha * cleanRateY) + ((1.0f - alpha) * filteredRateY)

        // 5. Cursor Acceleration Curve
        val totalSpeed = sqrt((filteredRateX * filteredRateX) + (filteredRateY * filteredRateY))
        val accelMultiplier = applyAcceleration(totalSpeed)

        // 6. Sensitivity Scaling
        val dispX = filteredRateX * settings.sensitivityX * accelMultiplier
        val dispY = filteredRateY * settings.sensitivityY * accelMultiplier

        // 7. Sub-Pixel Fractional Accumulation
        accumulatedX += dispX
        accumulatedY += dispY

        var stepX = accumulatedX.toInt()
        var stepY = accumulatedY.toInt()

        accumulatedX -= stepX.toFloat()
        accumulatedY -= stepY.toFloat()

        // 8. Clamping to valid 8-bit signed HID bounds [-127, 127]
        stepX = stepX.coerceIn(-127, 127)
        stepY = stepY.coerceIn(-127, 127)

        val hasMovement = (stepX != 0 || stepY != 0)
        return MotionDisplacement(
            dx = stepX.toByte(),
            dy = stepY.toByte(),
            wheel = 0,
            hasMovement = hasMovement
        )
    }

    private fun handleCalibrationSample(gx: Float, gy: Float, gz: Float) {
        calibSumX += gx
        calibSumY += gy
        calibSumZ += gz
        calibSampleCount++

        val motionMag = sqrt(gx * gx + gy * gy + gz * gz)
        if (motionMag > calibMaxMotion) {
            calibMaxMotion = motionMag
        }

        // 200 samples at ~100 Hz = 2.0 seconds
        if (calibSampleCount >= 200) {
            isCalibrating = false
            // Rejection threshold if phone was bumped/moved during calibration
            if (calibMaxMotion <= 4.0f) {
                biasX = calibSumX / calibSampleCount
                biasY = calibSumY / calibSampleCount
                biasZ = calibSumZ / calibSampleCount
                onCalibrationComplete?.invoke(true)
            } else {
                // Reject calibration if phone moved too much
                onCalibrationComplete?.invoke(false)
            }
        }
    }

    private fun applyDeadzone(rate: Float, deadzone: Float): Float {
        val absRate = abs(rate)
        if (absRate <= deadzone) {
            return 0.0f
        }
        val sign = if (rate > 0f) 1.0f else -1.0f
        return sign * (absRate - deadzone)
    }

    private fun applyAcceleration(totalSpeed: Float): Float {
        if (!settings.accelEnabled) {
            return 1.0f
        }
        if (totalSpeed <= settings.accelThreshold) {
            return 1.0f
        }
        val excess = totalSpeed - settings.accelThreshold
        val multiplier = 1.0f + (settings.accelGain * excess)
        return min(multiplier, settings.accelMaxScale)
    }

    fun getFilteredRateX(): Float = filteredRateX
    fun getFilteredRateY(): Float = filteredRateY
}
