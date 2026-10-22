package com.sentinal.aircontrol.ui

import android.Manifest
import android.annotation.SuppressLint
import android.bluetooth.BluetoothDevice
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.sentinal.aircontrol.AirControlApp
import com.sentinal.aircontrol.R
import com.sentinal.aircontrol.databinding.ActivityMainBinding
import com.sentinal.aircontrol.databinding.DialogSettingsBinding
import com.sentinal.aircontrol.hardware.HapticFeedbackController
import com.sentinal.aircontrol.hardware.TorchPointerController
import com.sentinal.aircontrol.hid.BluetoothHidController
import com.sentinal.aircontrol.model.ConnectionState
import com.sentinal.aircontrol.model.HidKeyCodes
import com.sentinal.aircontrol.model.OperatingMode
import com.sentinal.aircontrol.motion.MotionProcessor
import com.sentinal.aircontrol.motion.SensorController
import com.sentinal.aircontrol.wifi.WifiRemoteServer
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val app by lazy { AirControlApp.instance }
    private val settings by lazy { app.settingsState }

    // Core Hardware & Engine Controllers
    private lateinit var motionProcessor: MotionProcessor
    private lateinit var sensorController: SensorController
    private lateinit var hidController: BluetoothHidController
    private lateinit var torchController: TorchPointerController
    private lateinit var haptics: HapticFeedbackController
    private var wifiServer: WifiRemoteServer? = null

    // Operating Mode
    private var currentMode: OperatingMode = OperatingMode.PRESENTATION

    // Slide Presentation Timer
    private var timerStartTime = 0L
    private var timerRunning = false
    private val timerHandler = Handler(Looper.getMainLooper())

    // LED Simulation
    private val ledHandler = Handler(Looper.getMainLooper())
    private var bleLedBlinkState = false

    // Telemetry display throttle
    private var lastTelemetryUpdate = 0L

    private val permissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { results ->
        val btGranted = results[Manifest.permission.BLUETOOTH_CONNECT] ?: true
        if (btGranted) {
            startHidServices()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize controllers
        motionProcessor = MotionProcessor(settings)
        sensorController = SensorController(this, motionProcessor)
        hidController = BluetoothHidController(this)
        torchController = TorchPointerController(this)
        haptics = HapticFeedbackController(this)

        setupListeners()
        setupModeTabs()
        checkAndRequestPermissions()
        startWifiFallback()
        startStatusLedBlinking()

        // Default: Mode 1 (Presentation Remote) matching Sentinal boot behavior
        setOperatingMode(OperatingMode.PRESENTATION, isBoot = true)
    }

    override fun onDestroy() {
        super.onDestroy()
        sensorController.stop()
        hidController.stop()
        torchController.setTorch(false)
        wifiServer?.stop()
        timerHandler.removeCallbacksAndMessages(null)
        ledHandler.removeCallbacksAndMessages(null)
    }

    private fun checkAndRequestPermissions() {
        val permissions = mutableListOf<String>()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.BLUETOOTH_CONNECT)
            }
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_ADVERTISE) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.BLUETOOTH_ADVERTISE)
            }
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_SCAN) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.BLUETOOTH_SCAN)
            }
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            permissions.add(Manifest.permission.CAMERA)
        }

        if (permissions.isNotEmpty()) {
            permissionLauncher.launch(permissions.toTypedArray())
        } else {
            startHidServices()
        }
    }

    private fun startHidServices() {
        hidController.onConnectionStateChanged = { state, device ->
            updateConnectionUI(state, device)
        }
        hidController.onActionFeedback = {
            triggerActionLed()
        }
        hidController.start()

        sensorController.onMotionGenerated = { disp ->
            if (currentMode == OperatingMode.AIR_MOUSE) {
                hidController.sendMouseReport(disp.dx, disp.dy, disp.wheel)
                wifiServer?.broadcastMotion(disp.dx, disp.dy, disp.wheel, 0)
                runOnUiThread {
                    binding.tvMotionReadout.text = "dX: ${disp.dx}  dY: ${disp.dy}"
                }
            }
        }

        sensorController.onSensorTelemetry = { gx, gy, gz, filtX, filtY ->
            val now = SystemClock.uptimeMillis()
            if (now - lastTelemetryUpdate > 100) {
                lastTelemetryUpdate = now
                runOnUiThread {
                    binding.tvTelemetryLog.text = String.format(
                        Locale.US,
                        "[IMU 100Hz] Gx: %+.1f Gy: %+.1f Gz: %+.1f | FiltX: %+.2f FiltY: %+.2f",
                        gx, gy, gz, filtX, filtY
                    )
                }
            }
        }

        motionProcessor.onCalibrationComplete = { success ->
            runOnUiThread {
                if (success) {
                    Toast.makeText(this, "Zero-rate calibration complete!", Toast.LENGTH_SHORT).show()
                    haptics.successFeedback()
                } else {
                    Toast.makeText(this, "Calibration rejected: Phone moved too much!", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun startWifiFallback() {
        try {
            wifiServer = WifiRemoteServer(8765)
            wifiServer?.start()
        } catch (e: Exception) {
            // Port might be in use
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setupListeners() {
        // Mode Tabs
        binding.tabPresentation.setOnClickListener { setOperatingMode(OperatingMode.PRESENTATION) }
        binding.tabAirMouse.setOnClickListener { setOperatingMode(OperatingMode.AIR_MOUSE) }
        binding.tabReels.setOnClickListener { setOperatingMode(OperatingMode.REEL_SCROLLER) }

        // Mode 1: Presentation Remote Controls
        binding.btnNextSlide.setOnClickListener {
            haptics.clickFeedback()
            triggerActionLed()
            hidController.presentationNext()
            wifiServer?.broadcastKey("presentation_next")
            startSlideTimerIfNotRunning()
        }

        binding.btnPrevSlide.setOnClickListener {
            haptics.clickFeedback()
            triggerActionLed()
            hidController.presentationPrev()
            wifiServer?.broadcastKey("presentation_prev")
            startSlideTimerIfNotRunning()
        }

        binding.btnFullscreen.setOnClickListener {
            haptics.heavyClickFeedback()
            triggerActionLed()
            hidController.presentationFullscreen()
            wifiServer?.broadcastKey("presentation_f5")
        }

        binding.btnBlackScreen.setOnClickListener {
            haptics.clickFeedback()
            triggerActionLed()
            hidController.presentationBlackScreen()
            wifiServer?.broadcastKey("presentation_black")
        }

        // Torch Spotlight / Virtual Laser Pointer
        binding.btnTorchPointer.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    haptics.heavyClickFeedback()
                    torchController.setTorch(true)
                    binding.btnTorchPointer.text = "⚡ BEAM ACTIVE"
                    triggerActionLed()
                    true
                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    torchController.setTorch(false)
                    binding.btnTorchPointer.text = "🔦 TORCH BEAM"
                    true
                }
                else -> false
            }
        }

        // Timer Reset
        binding.btnResetTimer.setOnClickListener {
            timerStartTime = SystemClock.uptimeMillis()
            binding.tvSlideTimer.text = "00:00"
        }

        // Mode 2: Air Mouse Controls
        binding.btnLeftClick.setOnClickListener {
            haptics.clickFeedback()
            triggerActionLed()
            hidController.mouseClick(HidKeyCodes.MOUSE_LEFT)
            wifiServer?.broadcastMotion(0, 0, 0, HidKeyCodes.MOUSE_LEFT)
        }

        binding.btnRightClick.setOnClickListener {
            haptics.clickFeedback()
            triggerActionLed()
            hidController.mouseClick(HidKeyCodes.MOUSE_RIGHT)
            wifiServer?.broadcastMotion(0, 0, 0, HidKeyCodes.MOUSE_RIGHT)
        }

        binding.btnScrollUp.setOnClickListener {
            haptics.clickFeedback()
            triggerActionLed()
            hidController.scroll(3)
        }

        binding.btnScrollDown.setOnClickListener {
            haptics.clickFeedback()
            triggerActionLed()
            hidController.scroll((-3).toByte())
        }

        // Clutch / Freeze Pointer Button (Allows repositioning hand without cursor drift)
        binding.btnClutch.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    haptics.clickFeedback()
                    motionProcessor.setPaused(true)
                    binding.tvGyroState.text = "POINTER FROZEN (CLUTCH)"
                    binding.tvGyroState.setTextColor(ContextCompat.getColor(this, R.color.amber_accent))
                    true
                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    motionProcessor.setPaused(false)
                    binding.tvGyroState.text = "GYRO ACTIVE (100 Hz)"
                    binding.tvGyroState.setTextColor(ContextCompat.getColor(this, R.color.neon_green))
                    true
                }
                else -> false
            }
        }

        // Stationary Gyro Recalibration
        binding.btnCalibrate.setOnClickListener {
            haptics.heavyClickFeedback()
            Toast.makeText(this, "Keep phone still on flat surface for 2s...", Toast.LENGTH_SHORT).show()
            motionProcessor.startCalibration()
        }

        // Mode 3: Reel Controls
        binding.btnNextReel.setOnClickListener {
            haptics.clickFeedback()
            triggerActionLed()
            hidController.reelNext()
            wifiServer?.broadcastKey("reel_next")
        }

        binding.btnPrevReel.setOnClickListener {
            haptics.clickFeedback()
            triggerActionLed()
            hidController.reelPrev()
            wifiServer?.broadcastKey("reel_prev")
        }

        binding.btnPlayPauseReel.setOnClickListener {
            haptics.clickFeedback()
            triggerActionLed()
            hidController.reelPlayPause()
            wifiServer?.broadcastKey("reel_play_pause")
        }

        binding.btnVolUp.setOnClickListener {
            haptics.clickFeedback()
            triggerActionLed()
            hidController.sendConsumer(HidKeyCodes.CONSUMER_VOLUME_INCREMENT)
        }

        binding.btnVolDown.setOnClickListener {
            haptics.clickFeedback()
            triggerActionLed()
            hidController.sendConsumer(HidKeyCodes.CONSUMER_VOLUME_DECREMENT)
        }

        binding.btnLike.setOnClickListener {
            haptics.clickFeedback()
            triggerActionLed()
            // Double click mouse left or send key
            hidController.mouseClick(HidKeyCodes.MOUSE_LEFT)
            binding.root.postDelayed({
                hidController.mouseClick(HidKeyCodes.MOUSE_LEFT)
            }, 60)
        }

        // Bottom Controls: Cycle Mode button (mirrors Sentinal GPIO 7 button)
        binding.btnCycleMode.setOnClickListener {
            val nextModeId = (currentMode.id + 1) % 3
            setOperatingMode(OperatingMode.fromId(nextModeId))
        }

        binding.btnToggleTelemetry.setOnClickListener {
            val isVisible = binding.tvTelemetryLog.visibility == View.VISIBLE
            binding.tvTelemetryLog.visibility = if (isVisible) View.GONE else View.VISIBLE
        }

        // Settings Dialog
        binding.btnSettings.setOnClickListener {
            showSettingsDialog()
        }
    }

    private fun setupModeTabs() {
        binding.tabPresentation.setOnClickListener { setOperatingMode(OperatingMode.PRESENTATION) }
        binding.tabAirMouse.setOnClickListener { setOperatingMode(OperatingMode.AIR_MOUSE) }
        binding.tabReels.setOnClickListener { setOperatingMode(OperatingMode.REEL_SCROLLER) }
    }

    fun setOperatingMode(mode: OperatingMode, isBoot: Boolean = false) {
        currentMode = mode

        // Update Tab visual selection
        val tabs = listOf(binding.tabPresentation, binding.tabAirMouse, binding.tabReels)
        tabs.forEachIndexed { index, tab ->
            if (index == mode.id) {
                tab.setBackgroundResource(R.drawable.bg_tab_selected)
                tab.setTextColor(ContextCompat.getColor(this, R.color.cyan_accent))
            } else {
                tab.setBackgroundResource(R.drawable.bg_tab_unselected)
                tab.setTextColor(ContextCompat.getColor(this, R.color.text_secondary))
            }
        }

        // Switch Visible Panels
        binding.panelPresentation.visibility = if (mode == OperatingMode.PRESENTATION) View.VISIBLE else View.GONE
        binding.panelAirMouse.visibility = if (mode == OperatingMode.AIR_MOUSE) View.VISIBLE else View.GONE
        binding.panelReels.visibility = if (mode == OperatingMode.REEL_SCROLLER) View.VISIBLE else View.GONE

        // Configure Gyro Motion according to Sentinal spec:
        // Mode 1: Presentation -> Motion Paused (zero hand shake on slides)
        // Mode 2: Air Mouse -> Motion Active (wrist angular velocity pointer)
        // Mode 3: Reels -> Motion Paused (zero cursor displacement)
        when (mode) {
            OperatingMode.AIR_MOUSE -> {
                motionProcessor.setPaused(false)
                sensorController.start()
            }
            else -> {
                motionProcessor.setPaused(true)
                if (isBoot) {
                    // Start sensor controller so IMU warmup/telemetry works
                    sensorController.start()
                }
            }
        }

        // Trigger Mode LED burst blinks (1x, 2x, 3x) mirroring Sentinal hardware!
        blinkModeLed(mode.blinks)
        if (!isBoot) {
            haptics.modeChangeFeedback(mode.blinks)
        }
    }

    // =========================================================================
    // Physical Volume Rocker Interception
    // =========================================================================

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (!settings.volumeKeysEnabled) {
            return super.onKeyDown(keyCode, event)
        }

        return when (keyCode) {
            KeyEvent.KEYCODE_VOLUME_UP -> {
                when (currentMode) {
                    OperatingMode.PRESENTATION -> {
                        haptics.clickFeedback()
                        triggerActionLed()
                        hidController.presentationNext()
                    }
                    OperatingMode.AIR_MOUSE -> {
                        haptics.clickFeedback()
                        triggerActionLed()
                        hidController.mouseClick(HidKeyCodes.MOUSE_LEFT)
                    }
                    OperatingMode.REEL_SCROLLER -> {
                        haptics.clickFeedback()
                        triggerActionLed()
                        hidController.reelPrev()
                    }
                }
                true
            }
            KeyEvent.KEYCODE_VOLUME_DOWN -> {
                when (currentMode) {
                    OperatingMode.PRESENTATION -> {
                        haptics.clickFeedback()
                        triggerActionLed()
                        hidController.presentationPrev()
                    }
                    OperatingMode.AIR_MOUSE -> {
                        haptics.clickFeedback()
                        triggerActionLed()
                        hidController.mouseClick(HidKeyCodes.MOUSE_RIGHT)
                    }
                    OperatingMode.REEL_SCROLLER -> {
                        haptics.clickFeedback()
                        triggerActionLed()
                        hidController.reelNext()
                    }
                }
                true
            }
            else -> super.onKeyDown(keyCode, event)
        }
    }

    // =========================================================================
    // Hardware LED Simulation
    // =========================================================================

    private fun blinkModeLed(blinkCount: Int) {
        var count = 0
        val totalTicks = blinkCount * 2
        val runnable = object : Runnable {
            override fun run() {
                val isOn = count % 2 == 0
                setLedColor(binding.ledMode, if (isOn) R.color.led_orange_on else R.color.led_off)
                count++
                if (count < totalTicks) {
                    ledHandler.postDelayed(this, 120)
                } else {
                    setLedColor(binding.ledMode, R.color.led_off)
                }
            }
        }
        ledHandler.post(runnable)
    }

    private fun triggerActionLed() {
        setLedColor(binding.ledAction, R.color.neon_pink)
        ledHandler.postDelayed({
            setLedColor(binding.ledAction, R.color.led_off)
        }, 80)
    }

    private fun startStatusLedBlinking() {
        val blinkRunnable = object : Runnable {
            override fun run() {
                if (hidController.isConnected()) {
                    setLedColor(binding.ledStatus, R.color.led_blue_on)
                } else {
                    bleLedBlinkState = !bleLedBlinkState
                    setLedColor(
                        binding.ledStatus,
                        if (bleLedBlinkState) R.color.led_blue_on else R.color.led_off
                    )
                }
                ledHandler.postDelayed(this, 1000)
            }
        }
        ledHandler.post(blinkRunnable)
    }

    private fun setLedColor(view: View, colorRes: Int) {
        val drawable = view.background as? GradientDrawable ?: return
        drawable.setColor(ContextCompat.getColor(this, colorRes))
    }

    // =========================================================================
    // Connection State Handling
    // =========================================================================

    private fun updateConnectionUI(state: ConnectionState, device: BluetoothDevice?) {
        when (state) {
            ConnectionState.CONNECTED -> {
                binding.tvConnectionStatus.text = "CONNECTED"
                binding.tvConnectionStatus.setTextColor(ContextCompat.getColor(this, R.color.neon_green))
                binding.tvDeviceTarget.text = device?.name ?: "HOST PC / MAC"
                val dot = binding.dotConnection.background as? GradientDrawable
                dot?.setColor(ContextCompat.getColor(this, R.color.neon_green))
            }
            ConnectionState.CONNECTING -> {
                binding.tvConnectionStatus.text = "CONNECTING..."
                binding.tvConnectionStatus.setTextColor(ContextCompat.getColor(this, R.color.amber_accent))
                binding.tvDeviceTarget.text = device?.name ?: "PAIRING"
            }
            ConnectionState.ADVERTISING -> {
                binding.tvConnectionStatus.text = "ADVERTISING AS 'SENTINAL'"
                binding.tvConnectionStatus.setTextColor(ContextCompat.getColor(this, R.color.cyan_accent))
                binding.tvDeviceTarget.text = "PAIR IN PC BLUETOOTH"
                val dot = binding.dotConnection.background as? GradientDrawable
                dot?.setColor(ContextCompat.getColor(this, R.color.cyan_accent))
            }
            ConnectionState.DISCONNECTED, ConnectionState.REGISTERING -> {
                binding.tvConnectionStatus.text = "DISCONNECTED"
                binding.tvConnectionStatus.setTextColor(ContextCompat.getColor(this, R.color.text_muted))
                binding.tvDeviceTarget.text = "ENABLE BLUETOOTH"
                val dot = binding.dotConnection.background as? GradientDrawable
                dot?.setColor(ContextCompat.getColor(this, R.color.led_off))
            }
        }
    }

    // =========================================================================
    // Presentation Stopwatch Timer
    // =========================================================================

    private fun startSlideTimerIfNotRunning() {
        if (!timerRunning) {
            timerRunning = true
            timerStartTime = SystemClock.uptimeMillis()
            timerHandler.post(timerTickRunnable)
        }
    }

    private val timerTickRunnable = object : Runnable {
        override fun run() {
            if (timerRunning) {
                val elapsedSec = (SystemClock.uptimeMillis() - timerStartTime) / 1000
                val min = elapsedSec / 60
                val sec = elapsedSec % 60
                binding.tvSlideTimer.text = String.format(Locale.US, "%02d:%02d", min, sec)
                timerHandler.postDelayed(this, 1000)
            }
        }
    }

    // =========================================================================
    // Settings & Tuning Dialog
    // =========================================================================

    private fun showSettingsDialog() {
        val dialogBinding = DialogSettingsBinding.inflate(layoutInflater)
        val dialog = AlertDialog.Builder(this)
            .setView(dialogBinding.root)
            .create()

        // Init slider states
        dialogBinding.seekSensX.progress = (settings.sensitivityX * 100).toInt()
        dialogBinding.tvLabelSensX.text = String.format(Locale.US, "Sensitivity X (Yaw): %.2f", settings.sensitivityX)

        dialogBinding.seekSensY.progress = (settings.sensitivityY * 100).toInt()
        dialogBinding.tvLabelSensY.text = String.format(Locale.US, "Sensitivity Y (Pitch): %.2f", settings.sensitivityY)

        dialogBinding.seekDeadzone.progress = (settings.deadzoneDps * 10).toInt()
        dialogBinding.tvLabelDeadzone.text = String.format(Locale.US, "Gyro Deadzone: %.2f deg/s", settings.deadzoneDps)

        dialogBinding.seekAlpha.progress = (settings.filterAlpha * 100).toInt()
        dialogBinding.tvLabelAlpha.text = String.format(Locale.US, "Filter Smoothing Alpha: %.2f", settings.filterAlpha)

        dialogBinding.switchAccel.isChecked = settings.accelEnabled
        dialogBinding.switchInvertX.isChecked = settings.invertX
        dialogBinding.switchInvertY.isChecked = settings.invertY
        dialogBinding.switchVolumeKeys.isChecked = settings.volumeKeysEnabled
        dialogBinding.switchHaptics.isChecked = settings.hapticsEnabled

        dialogBinding.seekSensX.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(sb: SeekBar?, progress: Int, fromUser: Boolean) {
                val value = (progress.coerceAtLeast(5)) / 100f
                settings.sensitivityX = value
                dialogBinding.tvLabelSensX.text = String.format(Locale.US, "Sensitivity X (Yaw): %.2f", value)
            }
            override fun onStartTrackingTouch(sb: SeekBar?) {}
            override fun onStopTrackingTouch(sb: SeekBar?) {}
        })

        dialogBinding.seekSensY.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(sb: SeekBar?, progress: Int, fromUser: Boolean) {
                val value = (progress.coerceAtLeast(5)) / 100f
                settings.sensitivityY = value
                dialogBinding.tvLabelSensY.text = String.format(Locale.US, "Sensitivity Y (Pitch): %.2f", value)
            }
            override fun onStartTrackingTouch(sb: SeekBar?) {}
            override fun onStopTrackingTouch(sb: SeekBar?) {}
        })

        dialogBinding.seekDeadzone.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(sb: SeekBar?, progress: Int, fromUser: Boolean) {
                val value = progress / 10f
                settings.deadzoneDps = value
                dialogBinding.tvLabelDeadzone.text = String.format(Locale.US, "Gyro Deadzone: %.2f deg/s", value)
            }
            override fun onStartTrackingTouch(sb: SeekBar?) {}
            override fun onStopTrackingTouch(sb: SeekBar?) {}
        })

        dialogBinding.seekAlpha.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(sb: SeekBar?, progress: Int, fromUser: Boolean) {
                val value = (progress.coerceIn(10, 95)) / 100f
                settings.filterAlpha = value
                dialogBinding.tvLabelAlpha.text = String.format(Locale.US, "Filter Smoothing Alpha: %.2f", value)
            }
            override fun onStartTrackingTouch(sb: SeekBar?) {}
            override fun onStopTrackingTouch(sb: SeekBar?) {}
        })

        dialogBinding.switchAccel.setOnCheckedChangeListener { _, isChecked -> settings.accelEnabled = isChecked }
        dialogBinding.switchInvertX.setOnCheckedChangeListener { _, isChecked -> settings.invertX = isChecked }
        dialogBinding.switchInvertY.setOnCheckedChangeListener { _, isChecked -> settings.invertY = isChecked }
        dialogBinding.switchVolumeKeys.setOnCheckedChangeListener { _, isChecked -> settings.volumeKeysEnabled = isChecked }
        dialogBinding.switchHaptics.setOnCheckedChangeListener { _, isChecked -> settings.hapticsEnabled = isChecked }

        dialogBinding.btnCloseSettings.setOnClickListener {
            app.saveSettings()
            dialog.dismiss()
        }

        dialog.show()
    }
}
