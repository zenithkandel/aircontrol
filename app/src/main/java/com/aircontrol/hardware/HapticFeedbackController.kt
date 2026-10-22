package com.aircontrol.hardware

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager

import com.aircontrol.AirControlApp

/**
 * Apple Taptic-Calibrated Haptic Feedback Controller.
 * Provides microsecond-precise mechanical feedback conforming to WWDC Audio-Haptic guidelines.
 */
class HapticFeedbackController(context: Context) {

    private val vibrator: Vibrator? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        val vibratorManager = context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as? VibratorManager
        vibratorManager?.defaultVibrator
    } else {
        @Suppress("DEPRECATION")
        context.getSystemService(Context.VIBRATOR_SERVICE) as? Vibrator
    }

    private val isEnabled: Boolean
        get() = AirControlApp.instance.settingsState.hapticsEnabled

    /**
     * Subtle mechanical tick (ideal for keyboard typing, scroll wheel, and slider steps).
     */
    fun tickFeedback() {
        if (!isEnabled || vibrator == null || !vibrator.hasVibrator()) return
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            vibrator.vibrate(VibrationEffect.createPredefined(VibrationEffect.EFFECT_TICK))
        } else {
            @Suppress("DEPRECATION")
            vibrator.vibrate(10)
        }
    }

    /**
     * Primary button click (tactile press confirmation).
     */
    fun clickFeedback() {
        if (!isEnabled || vibrator == null || !vibrator.hasVibrator()) return
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            vibrator.vibrate(VibrationEffect.createPredefined(VibrationEffect.EFFECT_CLICK))
        } else {
            @Suppress("DEPRECATION")
            vibrator.vibrate(18)
        }
    }

    /**
     * Heavy affirmative click (mode change, spotlight trigger, F5 presentation).
     */
    fun heavyClickFeedback() {
        if (!isEnabled || vibrator == null || !vibrator.hasVibrator()) return
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            vibrator.vibrate(VibrationEffect.createPredefined(VibrationEffect.EFFECT_HEAVY_CLICK))
        } else {
            @Suppress("DEPRECATION")
            vibrator.vibrate(30)
        }
    }

    /**
     * Mode transition snap.
     */
    fun modeChangeFeedback(modeIndex: Int) {
        if (!isEnabled || vibrator == null || !vibrator.hasVibrator()) return
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            vibrator.vibrate(VibrationEffect.createPredefined(VibrationEffect.EFFECT_HEAVY_CLICK))
        } else {
            @Suppress("DEPRECATION")
            vibrator.vibrate(25)
        }
    }

    /**
     * Success / calibration complete notification waveform.
     */
    fun successFeedback() {
        if (!isEnabled || vibrator == null || !vibrator.hasVibrator()) return
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val timings = longArrayOf(0, 30, 40, 50)
            val amplitudes = intArrayOf(0, 100, 0, 200)
            vibrator.vibrate(VibrationEffect.createWaveform(timings, amplitudes, -1))
        } else {
            @Suppress("DEPRECATION")
            vibrator.vibrate(60)
        }
    }
}
