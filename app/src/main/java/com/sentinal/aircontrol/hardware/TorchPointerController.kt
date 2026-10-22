package com.sentinal.aircontrol.hardware

import android.content.Context
import android.hardware.camera2.CameraCharacteristics
import android.hardware.camera2.CameraManager
import android.util.Log

/**
 * Torch / Flashlight Pointer Controller.
 * Provides a physical spotlight beam as the smartphone equivalent
 * to Sentinal's 5V hardware toy laser pointer.
 */
class TorchPointerController(context: Context) {

    companion object {
        private const val TAG = "TorchPointerController"
    }

    private val cameraManager = context.getSystemService(Context.CAMERA_SERVICE) as CameraManager
    private var cameraId: String? = null
    private var isTorchOn: Boolean = false

    init {
        try {
            for (id in cameraManager.cameraIdList) {
                val characteristics = cameraManager.getCameraCharacteristics(id)
                val hasFlash = characteristics.get(CameraCharacteristics.FLASH_INFO_AVAILABLE) ?: false
                val facing = characteristics.get(CameraCharacteristics.LENS_FACING)
                if (hasFlash && facing == CameraCharacteristics.LENS_FACING_BACK) {
                    cameraId = id
                    break
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, "Failed to initialize camera flash", e)
        }
    }

    fun isAvailable(): Boolean = cameraId != null

    fun isTorchActive(): Boolean = isTorchOn

    fun setTorch(enable: Boolean) {
        val id = cameraId ?: return
        try {
            cameraManager.setTorchMode(id, enable)
            isTorchOn = enable
        } catch (e: Exception) {
            Log.e(TAG, "Error setting torch mode to $enable", e)
        }
    }

    fun toggleTorch(): Boolean {
        setTorch(!isTorchOn)
        return isTorchOn
    }
}
