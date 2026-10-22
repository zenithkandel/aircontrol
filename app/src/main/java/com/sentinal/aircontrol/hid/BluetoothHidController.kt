package com.sentinal.aircontrol.hid

import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothHidDevice
import android.bluetooth.BluetoothHidDeviceAppQosSettings
import android.bluetooth.BluetoothHidDeviceAppSdpSettings
import android.bluetooth.BluetoothManager
import android.bluetooth.BluetoothProfile
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.sentinal.aircontrol.model.ConnectionState
import com.sentinal.aircontrol.model.HidKeyCodes
import java.util.concurrent.Executors

/**
 * Universal Bluetooth HID Peripheral Controller.
 * Enables Android to function natively as a Bluetooth Mouse + Keyboard + Consumer Peripheral.
 */
@SuppressLint("MissingPermission")
class BluetoothHidController(private val context: Context) {

    companion object {
        private const val TAG = "BluetoothHidController"
        const val DEVICE_NAME = "Sentinal"
    }

    private val bluetoothManager = context.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
    val bluetoothAdapter: BluetoothAdapter? = bluetoothManager.adapter
    private var hidDevice: BluetoothHidDevice? = null

    private var targetDevice: BluetoothDevice? = null
    private var isAppRegistered = false
    private var currentButtons: Byte = 0
    private var lastReportedButtons: Byte = 0

    private val executor = Executors.newSingleThreadExecutor()
    private val mainHandler = Handler(Looper.getMainLooper())

    var onConnectionStateChanged: ((ConnectionState, BluetoothDevice?) -> Unit)? = null
    var onActionFeedback: (() -> Unit)? = null

    private val serviceListener = object : BluetoothProfile.ServiceListener {
        override fun onServiceConnected(profile: Int, proxy: BluetoothProfile) {
            if (profile == BluetoothProfile.HID_DEVICE) {
                hidDevice = proxy as BluetoothHidDevice
                registerHidApp()
            }
        }

        override fun onServiceDisconnected(profile: Int) {
            if (profile == BluetoothProfile.HID_DEVICE) {
                hidDevice = null
                isAppRegistered = false
                targetDevice = null
                notifyState(ConnectionState.DISCONNECTED, null)
            }
        }
    }

    private val hidCallback = object : BluetoothHidDevice.Callback() {
        override fun onAppStatusChanged(pluggedDevice: BluetoothDevice?, registered: Boolean) {
            isAppRegistered = registered
            Log.d(TAG, "HID App registered: $registered, plugged: ${pluggedDevice?.name}")
            if (registered) {
                val connected = pluggedDevice ?: hidDevice?.connectedDevices?.firstOrNull()
                if (connected != null) {
                    targetDevice = connected
                    notifyState(ConnectionState.CONNECTED, connected)
                } else {
                    notifyState(ConnectionState.ADVERTISING, null)
                }
            } else {
                notifyState(ConnectionState.DISCONNECTED, null)
            }
        }

        override fun onConnectionStateChanged(device: BluetoothDevice?, state: Int) {
            Log.d(TAG, "Connection state changed: $state for device: ${device?.name}")
            when (state) {
                BluetoothProfile.STATE_CONNECTED -> {
                    targetDevice = device
                    notifyState(ConnectionState.CONNECTED, device)
                }
                BluetoothProfile.STATE_CONNECTING -> {
                    notifyState(ConnectionState.CONNECTING, device)
                }
                BluetoothProfile.STATE_DISCONNECTED -> {
                    val remaining = hidDevice?.connectedDevices?.firstOrNull()
                    if (remaining != null) {
                        targetDevice = remaining
                        notifyState(ConnectionState.CONNECTED, remaining)
                    } else {
                        targetDevice = null
                        notifyState(ConnectionState.ADVERTISING, null)
                    }
                }
            }
        }

        override fun onGetReport(device: BluetoothDevice?, type: Byte, id: Byte, bufferSize: Int) {
            // Reply with zero report
            hidDevice?.replyReport(device, type, id, ByteArray(bufferSize))
        }

        override fun onSetReport(device: BluetoothDevice?, type: Byte, id: Byte, data: ByteArray?) {}
        override fun onSetProtocol(device: BluetoothDevice?, protocol: Byte) {}
        override fun onInterruptData(device: BluetoothDevice?, reportId: Byte, data: ByteArray?) {}
    }

    fun start() {
        val adapter = bluetoothAdapter ?: return
        if (!adapter.isEnabled) return
        adapter.getProfileProxy(context, serviceListener, BluetoothProfile.HID_DEVICE)
    }

    fun stop() {
        hidDevice?.let { dev ->
            if (isAppRegistered) {
                dev.unregisterApp()
            }
            bluetoothAdapter?.closeProfileProxy(BluetoothProfile.HID_DEVICE, dev)
        }
        hidDevice = null
        isAppRegistered = false
        targetDevice = null
        notifyState(ConnectionState.DISCONNECTED, null)
    }

    fun connectDevice(device: BluetoothDevice) {
        targetDevice = device
        hidDevice?.connect(device)
    }

    fun disconnectDevice() {
        targetDevice?.let {
            hidDevice?.disconnect(it)
        }
    }

    fun isConnected(): Boolean = targetDevice != null && isAppRegistered

    fun getConnectedDevice(): BluetoothDevice? = targetDevice

    private fun registerHidApp() {
        val dev = hidDevice ?: return
        val sdp = BluetoothHidDeviceAppSdpSettings(
            DEVICE_NAME,
            "Sentinal 3-in-1 Air Mouse & Presentation Remote",
            "Sentinal",
            BluetoothHidDevice.SUBCLASS1_COMBO,
            HidDescriptors.REPORT_MAP
        )

        val qos = BluetoothHidDeviceAppQosSettings(
            BluetoothHidDeviceAppQosSettings.SERVICE_BEST_EFFORT,
            800,
            9,
            0,
            11250,
            BluetoothHidDeviceAppQosSettings.MAX
        )

        dev.registerApp(sdp, null, qos, executor, hidCallback)
    }

    private fun notifyState(state: ConnectionState, device: BluetoothDevice?) {
        mainHandler.post {
            onConnectionStateChanged?.invoke(state, device)
        }
    }

    // =========================================================================
    // Mouse Reports (Report ID 1)
    // =========================================================================

    fun sendMouseReport(dx: Byte, dy: Byte, wheel: Byte, buttons: Byte = currentButtons): Boolean {
        val dev = targetDevice ?: hidDevice?.connectedDevices?.firstOrNull() ?: return false
        val hid = hidDevice ?: return false
        if (targetDevice == null) {
            targetDevice = dev
            notifyState(ConnectionState.CONNECTED, dev)
        }

        // Suppression filter
        if (dx.toInt() == 0 && dy.toInt() == 0 && wheel.toInt() == 0 && buttons == lastReportedButtons) {
            return false
        }

        val report = byteArrayOf(buttons, dx, dy, wheel)
        val success = hid.sendReport(dev, HidDescriptors.REPORT_ID_MOUSE.toInt(), report)
        if (success) {
            lastReportedButtons = buttons
        }
        return success
    }

    fun mouseClick(button: Byte) {
        mousePress(button)
        mainHandler.postDelayed({
            mouseRelease(button)
        }, 35)
        onActionFeedback?.invoke()
    }

    fun mousePress(button: Byte) {
        currentButtons = (currentButtons.toInt() or button.toInt()).toByte()
        sendMouseReport(0, 0, 0, currentButtons)
    }

    fun mouseRelease(button: Byte) {
        currentButtons = (currentButtons.toInt() and button.toInt().inv()).toByte()
        sendMouseReport(0, 0, 0, currentButtons)
    }

    fun scroll(wheel: Byte) {
        sendMouseReport(0, 0, wheel, currentButtons)
        mainHandler.postDelayed({
            sendMouseReport(0, 0, 0, currentButtons)
        }, 25)
        onActionFeedback?.invoke()
    }

    // =========================================================================
    // Keyboard Reports (Report ID 2)
    // =========================================================================

    fun sendKey(keyCode: Byte, modifier: Byte = HidKeyCodes.MOD_NONE) {
        val dev = targetDevice ?: hidDevice?.connectedDevices?.firstOrNull() ?: return
        val hid = hidDevice ?: return
        if (targetDevice == null) {
            targetDevice = dev
            notifyState(ConnectionState.CONNECTED, dev)
        }

        // 8-byte report: [modifier, reserved, key1, key2, key3, key4, key5, key6]
        val keyReport = byteArrayOf(modifier, 0x00, keyCode, 0x00, 0x00, 0x00, 0x00, 0x00)
        hid.sendReport(dev, HidDescriptors.REPORT_ID_KEYBOARD.toInt(), keyReport)
        onActionFeedback?.invoke()

        // Release key after 50ms hold
        mainHandler.postDelayed({
            val emptyReport = ByteArray(8)
            hid.sendReport(dev, HidDescriptors.REPORT_ID_KEYBOARD.toInt(), emptyReport)
        }, 50)
    }

    // =========================================================================
    // Consumer Control Reports (Report ID 3)
    // =========================================================================

    fun sendConsumer(usage: Short) {
        val dev = targetDevice ?: hidDevice?.connectedDevices?.firstOrNull() ?: return
        val hid = hidDevice ?: return
        if (targetDevice == null) {
            targetDevice = dev
            notifyState(ConnectionState.CONNECTED, dev)
        }

        val report = byteArrayOf((usage.toInt() and 0xFF).toByte(), ((usage.toInt() shr 8) and 0xFF).toByte())
        hid.sendReport(dev, HidDescriptors.REPORT_ID_CONSUMER.toInt(), report)
        onActionFeedback?.invoke()

        mainHandler.postDelayed({
            val emptyReport = byteArrayOf(0, 0)
            hid.sendReport(dev, HidDescriptors.REPORT_ID_CONSUMER.toInt(), emptyReport)
        }, 30)
    }

    // =========================================================================
    // Sentinal Operational Actions
    // =========================================================================

    fun presentationNext() {
        sendKey(HidKeyCodes.KEY_PAGE_DOWN)
    }

    fun presentationPrev() {
        sendKey(HidKeyCodes.KEY_PAGE_UP)
    }

    fun presentationFullscreen() {
        sendKey(HidKeyCodes.KEY_F5)
    }

    fun presentationBlackScreen() {
        sendKey(HidKeyCodes.KEY_B)
    }

    fun reelNext() {
        sendKey(HidKeyCodes.KEY_DOWN_ARROW)
        scroll((-5).toByte())
    }

    fun reelPrev() {
        sendKey(HidKeyCodes.KEY_UP_ARROW)
        scroll(5.toByte())
    }

    fun reelPlayPause() {
        sendKey(HidKeyCodes.KEY_SPACE)
    }
}
