package com.aircontrol.motion

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import com.aircontrol.model.MotionDisplacement

/**
 * High-Rate Gyroscope and IMU Sensor Controller.
 * Samples angular rates at ~100 Hz (10ms IMU loop).
 */
class SensorController(
    context: Context,
    private val motionProcessor: MotionProcessor
) : SensorEventListener {

    private val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    private val gyroscope: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
    private val accelerometer: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

    private var isRunning: Boolean = false
    private val radToDeg = (180.0f / Math.PI).toFloat()

    var onMotionGenerated: ((MotionDisplacement) -> Unit)? = null
    var onSensorTelemetry: ((gx: Float, gy: Float, gz: Float, filtX: Float, filtY: Float) -> Unit)? = null

    fun start() {
        if (isRunning) return
        gyroscope?.let {
            // SENSOR_DELAY_GAME delivers ~50-100Hz updates
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_GAME)
        }
        accelerometer?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_UI)
        }
        isRunning = true
    }

    fun stop() {
        if (!isRunning) return
        sensorManager.unregisterListener(this)
        motionProcessor.reset()
        isRunning = false
    }

    override fun onSensorChanged(event: SensorEvent) {
        if (event.sensor.type == Sensor.TYPE_GYROSCOPE) {
            // Android gyroscope values are in rad/s:
            // event.values[0] = x (pitch around x)
            // event.values[1] = y (roll around y)
            // event.values[2] = z (yaw around z)
            val gxDeg = event.values[0] * radToDeg
            val gyDeg = event.values[1] * radToDeg
            val gzDeg = event.values[2] * radToDeg

            val displacement = motionProcessor.process(gxDeg, gyDeg, gzDeg)
            if (displacement.hasMovement) {
                onMotionGenerated?.invoke(displacement)
            }

            onSensorTelemetry?.invoke(
                gxDeg, gyDeg, gzDeg,
                motionProcessor.getFilteredRateX(),
                motionProcessor.getFilteredRateY()
            )
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
}
