package com.sentinal.aircontrol.ui

import android.Manifest
import android.annotation.SuppressLint
import android.bluetooth.BluetoothDevice
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.SeekBar
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
        setupKeyboardMode()
        setupSpringPressEffects()
        checkAndRequestPermissions()
        startWifiFallback()

        // Default: Mode 1 (Slides)
        setOperatingMode(OperatingMode.PRESENTATION, isBoot = true)
    }

    override fun onDestroy() {
        super.onDestroy()
        sensorController.stop()
        hidController.stop()
        torchController.setTorch(false)
        wifiServer?.stop()
        timerHandler.removeCallbacksAndMessages(null)
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
            haptics.tickFeedback()
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
                    binding.tvMotionReadout.text = String.format(
                        Locale.US,
                        "dX: %+.1f  dY: %+.1f",
                        filtX, filtY
                    )
                }
            }
        }

        motionProcessor.onCalibrationComplete = { success ->
            runOnUiThread {
                if (success) {
                    Toast.makeText(this, "Calibration complete", Toast.LENGTH_SHORT).show()
                    haptics.successFeedback()
                } else {
                    Toast.makeText(this, "Keep phone still during calibration", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun startWifiFallback() {
        try {
            wifiServer = WifiRemoteServer(8765)
            wifiServer?.start()
        } catch (e: Exception) {
            // Port in use
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setupListeners() {
        // Mode 1: Presentation Remote Controls
        binding.btnNextSlide.setOnClickListener {
            haptics.clickFeedback()
            hidController.presentationNext()
            wifiServer?.broadcastKey("presentation_next")
            startSlideTimerIfNotRunning()
        }

        binding.btnPrevSlide.setOnClickListener {
            haptics.clickFeedback()
            hidController.presentationPrev()
            wifiServer?.broadcastKey("presentation_prev")
            startSlideTimerIfNotRunning()
        }

        binding.btnFullscreen.setOnClickListener {
            haptics.heavyClickFeedback()
            hidController.presentationFullscreen()
            wifiServer?.broadcastKey("presentation_f5")
        }

        binding.btnBlackScreen.setOnClickListener {
            haptics.clickFeedback()
            hidController.presentationBlackScreen()
            wifiServer?.broadcastKey("presentation_black")
        }

        // Torch Spotlight Beam
        binding.btnTorchPointer.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    haptics.heavyClickFeedback()
                    torchController.setTorch(true)
                    binding.btnTorchPointer.text = "⚡ Beam Active"
                    true
                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    torchController.setTorch(false)
                    binding.btnTorchPointer.text = "🔦 Spotlight"
                    true
                }
                else -> false
            }
        }

        // Timer Reset
        binding.btnResetTimer.setOnClickListener {
            haptics.tickFeedback()
            timerStartTime = SystemClock.uptimeMillis()
            binding.tvSlideTimer.text = "00:00"
        }

        // Mode 2: Air Mouse Controls
        binding.btnLeftClick.setOnClickListener {
            haptics.clickFeedback()
            hidController.mouseClick(HidKeyCodes.MOUSE_LEFT)
            wifiServer?.broadcastMotion(0, 0, 0, HidKeyCodes.MOUSE_LEFT)
        }

        binding.btnRightClick.setOnClickListener {
            haptics.clickFeedback()
            hidController.mouseClick(HidKeyCodes.MOUSE_RIGHT)
            wifiServer?.broadcastMotion(0, 0, 0, HidKeyCodes.MOUSE_RIGHT)
        }

        binding.btnScrollUp.setOnClickListener {
            haptics.tickFeedback()
            hidController.scroll(3)
        }

        binding.btnScrollDown.setOnClickListener {
            haptics.tickFeedback()
            hidController.scroll((-3).toByte())
        }

        // Clutch / Freeze Pointer Button
        binding.btnClutch.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    haptics.clickFeedback()
                    motionProcessor.setPaused(true)
                    binding.tvGyroState.text = "Pointer Frozen (Clutch)"
                    binding.tvGyroState.setTextColor(ContextCompat.getColor(this, R.color.apple_amber))
                    true
                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    motionProcessor.setPaused(false)
                    binding.tvGyroState.text = "Pointer Active (100 Hz)"
                    binding.tvGyroState.setTextColor(ContextCompat.getColor(this, R.color.apple_green))
                    true
                }
                else -> false
            }
        }

        // Stationary Gyro Recalibration
        binding.btnCalibrate.setOnClickListener {
            haptics.heavyClickFeedback()
            Toast.makeText(this, "Rest phone on flat surface for 2s...", Toast.LENGTH_SHORT).show()
            motionProcessor.startCalibration()
        }

        // Mode 4: Media Controls
        binding.btnNextReel.setOnClickListener {
            haptics.clickFeedback()
            hidController.reelNext()
            wifiServer?.broadcastKey("reel_next")
        }

        binding.btnPrevReel.setOnClickListener {
            haptics.clickFeedback()
            hidController.reelPrev()
            wifiServer?.broadcastKey("reel_prev")
        }

        binding.btnPlayPauseReel.setOnClickListener {
            haptics.clickFeedback()
            hidController.reelPlayPause()
            wifiServer?.broadcastKey("reel_play_pause")
        }

        binding.btnVolUp.setOnClickListener {
            haptics.clickFeedback()
            hidController.sendConsumer(HidKeyCodes.CONSUMER_VOLUME_INCREMENT)
        }

        binding.btnVolDown.setOnClickListener {
            haptics.clickFeedback()
            hidController.sendConsumer(HidKeyCodes.CONSUMER_VOLUME_DECREMENT)
        }

        binding.btnLike.setOnClickListener {
            haptics.heavyClickFeedback()
            hidController.mouseClick(HidKeyCodes.MOUSE_LEFT)
            binding.root.postDelayed({
                hidController.mouseClick(HidKeyCodes.MOUSE_LEFT)
            }, 60)
        }

        // Status Pill tap opens settings and device connection
        binding.statusPillLayout.setOnClickListener {
            haptics.tickFeedback()
            showSettingsDialog()
        }

        // Settings Dialog
        binding.btnSettings.setOnClickListener {
            haptics.clickFeedback()
            showSettingsDialog()
        }
    }

    private fun setupModeTabs() {
        binding.tabPresentation.setOnClickListener { setOperatingMode(OperatingMode.PRESENTATION) }
        binding.tabAirMouse.setOnClickListener { setOperatingMode(OperatingMode.AIR_MOUSE) }
        binding.tabKeyboard.setOnClickListener { setOperatingMode(OperatingMode.KEYBOARD) }
        binding.tabReels.setOnClickListener { setOperatingMode(OperatingMode.REEL_SCROLLER) }
    }

    /**
     * Wireless Keyboard Mode setup: Live typing + Quick Keys + Shortcuts
     */
    private fun setupKeyboardMode() {
        // Send Button
        binding.btnSendText.setOnClickListener {
            val text = binding.etKeyboardInput.text.toString()
            if (text.isNotEmpty()) {
                haptics.clickFeedback()
                hidController.sendText(text) {
                    binding.etKeyboardInput.setText("")
                }
            }
        }

        // IME Action Send on soft keyboard
        binding.etKeyboardInput.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEND || actionId == EditorInfo.IME_ACTION_DONE) {
                val text = binding.etKeyboardInput.text.toString()
                if (text.isNotEmpty()) {
                    haptics.clickFeedback()
                    hidController.sendText(text) {
                        binding.etKeyboardInput.setText("")
                    }
                }
                true
            } else {
                false
            }
        }

        // Quick Key Row
        binding.btnKeyEsc.setOnClickListener {
            haptics.tickFeedback()
            hidController.keyEscape()
        }

        binding.btnKeyTab.setOnClickListener {
            haptics.tickFeedback()
            hidController.keyTab()
        }

        binding.btnKeySpace.setOnClickListener {
            haptics.tickFeedback()
            hidController.keySpace()
        }

        binding.btnKeyBackspace.setOnClickListener {
            haptics.tickFeedback()
            hidController.keyBackspace()
        }

        binding.btnKeyReturn.setOnClickListener {
            haptics.clickFeedback()
            hidController.keyReturn()
        }

        // Arrow Keypad
        binding.btnArrowUp.setOnClickListener {
            haptics.tickFeedback()
            hidController.arrowUp()
        }

        binding.btnArrowDown.setOnClickListener {
            haptics.tickFeedback()
            hidController.arrowDown()
        }

        binding.btnArrowLeft.setOnClickListener {
            haptics.tickFeedback()
            hidController.arrowLeft()
        }

        binding.btnArrowRight.setOnClickListener {
            haptics.tickFeedback()
            hidController.arrowRight()
        }

        // Shortcuts Row
        binding.btnShortcutUndo.setOnClickListener {
            haptics.clickFeedback()
            hidController.undo()
        }

        binding.btnShortcutCopy.setOnClickListener {
            haptics.clickFeedback()
            hidController.copy()
        }

        binding.btnShortcutPaste.setOnClickListener {
            haptics.clickFeedback()
            hidController.paste()
        }

        binding.btnShortcutSelectAll.setOnClickListener {
            haptics.clickFeedback()
            hidController.selectAll()
        }

        binding.btnShortcutSearch.setOnClickListener {
            haptics.heavyClickFeedback()
            hidController.searchSpotlight()
        }

        binding.btnKeyPgUp.setOnClickListener {
            haptics.tickFeedback()
            hidController.keyPageUp()
        }

        binding.btnKeyPgDn.setOnClickListener {
            haptics.tickFeedback()
            hidController.keyPageDown()
        }

        binding.btnKbMouseLeft.setOnClickListener {
            haptics.clickFeedback()
            hidController.mouseClick(HidKeyCodes.MOUSE_LEFT)
        }

        binding.btnKbMouseRight.setOnClickListener {
            haptics.clickFeedback()
            hidController.mouseClick(HidKeyCodes.MOUSE_RIGHT)
        }

        // Integrated Trackpad Touch Canvas
        var trackStartX = 0f
        var trackStartY = 0f
        var lastTrackX = 0f
        var lastTrackY = 0f
        var hasMoved = false

        binding.trackpadCanvas.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    trackStartX = event.x
                    trackStartY = event.y
                    lastTrackX = event.x
                    lastTrackY = event.y
                    hasMoved = false
                    haptics.tickFeedback()
                    true
                }
                MotionEvent.ACTION_MOVE -> {
                    val dx = (event.x - lastTrackX) * 1.25f
                    val dy = (event.y - lastTrackY) * 1.25f
                    if (Math.hypot((event.x - trackStartX).toDouble(), (event.y - trackStartY).toDouble()) > 8) {
                        hasMoved = true
                    }
                    if (Math.abs(dx) >= 0.5f || Math.abs(dy) >= 0.5f) {
                        hidController.mouseMove(dx.toInt().coerceIn(-127, 127).toByte(), dy.toInt().coerceIn(-127, 127).toByte())
                        lastTrackX = event.x
                        lastTrackY = event.y
                    }
                    true
                }
                MotionEvent.ACTION_UP -> {
                    if (!hasMoved) {
                        haptics.clickFeedback()
                        hidController.mouseClick(HidKeyCodes.MOUSE_LEFT)
                    }
                    true
                }
                else -> false
            }
        }
    }

    /**
     * Apple Fluid Interaction: Instant scale down to 0.97 on press with spring return.
     */
    @SuppressLint("ClickableViewAccessibility")
    private fun setupSpringPressEffects() {
        val interactiveButtons = listOf(
            binding.btnNextSlide, binding.btnPrevSlide, binding.btnFullscreen, binding.btnBlackScreen,
            binding.btnLeftClick, binding.btnRightClick, binding.btnScrollUp, binding.btnScrollDown,
            binding.btnCalibrate, binding.btnNextReel, binding.btnPrevReel, binding.btnPlayPauseReel,
            binding.btnVolUp, binding.btnVolDown, binding.btnLike, binding.btnSettings,
            binding.btnKeyEsc, binding.btnKeyTab, binding.btnKeySpace, binding.btnKeyBackspace,
            binding.btnKeyReturn, binding.btnArrowUp, binding.btnArrowDown, binding.btnArrowLeft,
            binding.btnArrowRight, binding.btnKeyPgUp, binding.btnKeyPgDn, binding.btnKbMouseLeft,
            binding.btnKbMouseRight, binding.btnShortcutUndo, binding.btnShortcutCopy,
            binding.btnShortcutPaste, binding.btnShortcutSelectAll, binding.btnShortcutSearch,
            binding.btnSendText
        )

        interactiveButtons.forEach { view ->
            view.setOnTouchListener { v, event ->
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        v.animate().scaleX(0.97f).scaleY(0.97f).setDuration(60).start()
                    }
                    MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                        v.animate().scaleX(1.0f).scaleY(1.0f).setDuration(120).start()
                    }
                }
                false
            }
        }
    }

    fun setOperatingMode(mode: OperatingMode, isBoot: Boolean = false) {
        currentMode = mode

        // Update Apple Segmented Control selection
        val tabs = listOf(binding.tabPresentation, binding.tabAirMouse, binding.tabKeyboard, binding.tabReels)
        tabs.forEachIndexed { index, tab ->
            if (index == mode.id) {
                tab.setBackgroundResource(R.drawable.bg_apple_tab_active)
                tab.setTextColor(ContextCompat.getColor(this, R.color.apple_text_primary))
            } else {
                tab.setBackgroundResource(R.drawable.bg_apple_tab_inactive)
                tab.setTextColor(ContextCompat.getColor(this, R.color.apple_text_secondary))
            }
        }

        // Switch Visible Panels
        binding.panelPresentation.visibility = if (mode == OperatingMode.PRESENTATION) View.VISIBLE else View.GONE
        binding.panelAirMouse.visibility = if (mode == OperatingMode.AIR_MOUSE) View.VISIBLE else View.GONE
        binding.panelKeyboard.visibility = if (mode == OperatingMode.KEYBOARD) View.VISIBLE else View.GONE
        binding.panelReels.visibility = if (mode == OperatingMode.REEL_SCROLLER) View.VISIBLE else View.GONE

        // Motion pipeline configuration:
        // Only active in Air Mouse mode
        when (mode) {
            OperatingMode.AIR_MOUSE -> {
                motionProcessor.setPaused(false)
                sensorController.start()
            }
            else -> {
                motionProcessor.setPaused(true)
                if (isBoot) {
                    sensorController.start()
                }
            }
        }

        // Close soft keyboard if switching away from Keyboard mode
        if (mode != OperatingMode.KEYBOARD) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(binding.root.windowToken, 0)
        }

        if (!isBoot) {
            haptics.modeChangeFeedback(mode.id)
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
                        hidController.presentationNext()
                    }
                    OperatingMode.AIR_MOUSE -> {
                        haptics.clickFeedback()
                        hidController.mouseClick(HidKeyCodes.MOUSE_LEFT)
                    }
                    OperatingMode.KEYBOARD -> {
                        haptics.tickFeedback()
                        hidController.arrowUp()
                    }
                    OperatingMode.REEL_SCROLLER -> {
                        haptics.clickFeedback()
                        hidController.reelPrev()
                    }
                }
                true
            }
            KeyEvent.KEYCODE_VOLUME_DOWN -> {
                when (currentMode) {
                    OperatingMode.PRESENTATION -> {
                        haptics.clickFeedback()
                        hidController.presentationPrev()
                    }
                    OperatingMode.AIR_MOUSE -> {
                        haptics.clickFeedback()
                        hidController.mouseClick(HidKeyCodes.MOUSE_RIGHT)
                    }
                    OperatingMode.KEYBOARD -> {
                        haptics.tickFeedback()
                        hidController.arrowDown()
                    }
                    OperatingMode.REEL_SCROLLER -> {
                        haptics.clickFeedback()
                        hidController.reelNext()
                    }
                }
                true
            }
            else -> super.onKeyDown(keyCode, event)
        }
    }

    // =========================================================================
    // Connection State Handling (Apple Dynamic Pill)
    // =========================================================================

    private fun updateConnectionUI(state: ConnectionState, device: BluetoothDevice?) {
        val dot = binding.dotConnection.background as? GradientDrawable
        when (state) {
            ConnectionState.CONNECTED -> {
                binding.tvConnectionStatus.text = "CONNECTED"
                binding.tvConnectionStatus.setTextColor(ContextCompat.getColor(this, R.color.apple_green))
                binding.tvDeviceTarget.text = device?.name ?: "Host Computer"
                dot?.setColor(ContextCompat.getColor(this, R.color.apple_green))
            }
            ConnectionState.CONNECTING -> {
                binding.tvConnectionStatus.text = "CONNECTING..."
                binding.tvConnectionStatus.setTextColor(ContextCompat.getColor(this, R.color.apple_amber))
                binding.tvDeviceTarget.text = device?.name ?: "Pairing"
                dot?.setColor(ContextCompat.getColor(this, R.color.apple_amber))
            }
            ConnectionState.ADVERTISING -> {
                binding.tvConnectionStatus.text = "PAIRING: AIRCONTROL"
                binding.tvConnectionStatus.setTextColor(ContextCompat.getColor(this, R.color.apple_blue))
                binding.tvDeviceTarget.text = "Ready to pair"
                dot?.setColor(ContextCompat.getColor(this, R.color.apple_blue))
            }
            ConnectionState.DISCONNECTED, ConnectionState.REGISTERING -> {
                binding.tvConnectionStatus.text = "DISCONNECTED"
                binding.tvConnectionStatus.setTextColor(ContextCompat.getColor(this, R.color.apple_text_tertiary))
                binding.tvDeviceTarget.text = "Enable Bluetooth"
                dot?.setColor(ContextCompat.getColor(this, R.color.apple_well))
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
                if (fromUser) haptics.tickFeedback()
            }
            override fun onStartTrackingTouch(sb: SeekBar?) {}
            override fun onStopTrackingTouch(sb: SeekBar?) {}
        })

        dialogBinding.seekSensY.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(sb: SeekBar?, progress: Int, fromUser: Boolean) {
                val value = (progress.coerceAtLeast(5)) / 100f
                settings.sensitivityY = value
                dialogBinding.tvLabelSensY.text = String.format(Locale.US, "Sensitivity Y (Pitch): %.2f", value)
                if (fromUser) haptics.tickFeedback()
            }
            override fun onStartTrackingTouch(sb: SeekBar?) {}
            override fun onStopTrackingTouch(sb: SeekBar?) {}
        })

        dialogBinding.seekDeadzone.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(sb: SeekBar?, progress: Int, fromUser: Boolean) {
                val value = progress / 10f
                settings.deadzoneDps = value
                dialogBinding.tvLabelDeadzone.text = String.format(Locale.US, "Gyro Deadzone: %.2f deg/s", value)
                if (fromUser) haptics.tickFeedback()
            }
            override fun onStartTrackingTouch(sb: SeekBar?) {}
            override fun onStopTrackingTouch(sb: SeekBar?) {}
        })

        dialogBinding.seekAlpha.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(sb: SeekBar?, progress: Int, fromUser: Boolean) {
                val value = (progress.coerceIn(10, 95)) / 100f
                settings.filterAlpha = value
                dialogBinding.tvLabelAlpha.text = String.format(Locale.US, "Filter Smoothing Alpha: %.2f", value)
                if (fromUser) haptics.tickFeedback()
            }
            override fun onStartTrackingTouch(sb: SeekBar?) {}
            override fun onStopTrackingTouch(sb: SeekBar?) {}
        })

        dialogBinding.switchAccel.setOnCheckedChangeListener { _, isChecked ->
            haptics.tickFeedback()
            settings.accelEnabled = isChecked
        }
        dialogBinding.switchInvertX.setOnCheckedChangeListener { _, isChecked ->
            haptics.tickFeedback()
            settings.invertX = isChecked
        }
        dialogBinding.switchInvertY.setOnCheckedChangeListener { _, isChecked ->
            haptics.tickFeedback()
            settings.invertY = isChecked
        }
        dialogBinding.switchVolumeKeys.setOnCheckedChangeListener { _, isChecked ->
            haptics.tickFeedback()
            settings.volumeKeysEnabled = isChecked
        }
        dialogBinding.switchHaptics.setOnCheckedChangeListener { _, isChecked ->
            haptics.tickFeedback()
            settings.hapticsEnabled = isChecked
        }

        dialogBinding.btnCloseSettings.setOnClickListener {
            haptics.clickFeedback()
            app.saveSettings()
            dialog.dismiss()
        }

        dialog.show()
    }
}
