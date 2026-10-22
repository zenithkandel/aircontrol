package com.aircontrol.ui

import android.Manifest
import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.drawable.GradientDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import android.provider.Settings
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import com.aircontrol.AirControlApp
import com.aircontrol.R
import com.aircontrol.databinding.ActivityMainBinding
import com.aircontrol.databinding.DialogDevicePickerBinding
import com.aircontrol.databinding.DialogSettingsBinding
import com.aircontrol.databinding.ItemPairedDeviceBinding
import com.aircontrol.hardware.HapticFeedbackController
import com.aircontrol.hardware.TorchPointerController
import com.aircontrol.hid.BluetoothHidController
import com.aircontrol.model.ConnectionState
import com.aircontrol.model.HidKeyCodes
import com.aircontrol.model.OperatingMode
import com.aircontrol.wifi.WifiRemoteServer
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val app by lazy { AirControlApp.instance }
    private val settings by lazy { app.settingsState }

    // Core Hardware & Engine Controllers
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

    private val permissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { results ->
        val btGranted = results[Manifest.permission.BLUETOOTH_CONNECT] ?: true
        if (btGranted) {
            startHidServices()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        // Apply persisted Light Mode / Dark Mode preference
        applyThemeMode(settings.isDarkMode)

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize controllers
        hidController = BluetoothHidController(this)
        torchController = TorchPointerController(this)
        haptics = HapticFeedbackController(this)

        setupListeners()
        setupModeTabs()
        setupTouchpadCanvas()
        setupKeyboardMode()
        setupSpringPressEffects()
        checkAndRequestPermissions()
        startWifiFallback()

        // Default: Mode 1 (Slides)
        setOperatingMode(OperatingMode.PRESENTATION, isBoot = true)
    }

    override fun onDestroy() {
        super.onDestroy()
        hidController.stop()
        torchController.setTorch(false)
        wifiServer?.stop()
        timerHandler.removeCallbacksAndMessages(null)
    }

    override fun onBackPressed() {
        if (binding.fullscreenTouchpadOverlay.visibility == View.VISIBLE) {
            exitFullscreenTouchpad()
        } else {
            super.onBackPressed()
        }
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
    }

    private fun startWifiFallback() {
        try {
            wifiServer = WifiRemoteServer(8765)
            wifiServer?.start()
        } catch (e: Exception) {
            // Port in use or Wi-Fi inactive
        }
    }

    // =========================================================================
    // Theme Switching: Light Mode & Dark Mode
    // =========================================================================

    private fun applyThemeMode(isDark: Boolean) {
        val target = if (isDark) {
            AppCompatDelegate.MODE_NIGHT_YES
        } else {
            AppCompatDelegate.MODE_NIGHT_NO
        }
        if (AppCompatDelegate.getDefaultNightMode() != target) {
            AppCompatDelegate.setDefaultNightMode(target)
        }
    }

    private fun toggleTheme() {
        haptics.clickFeedback()
        settings.isDarkMode = !settings.isDarkMode
        app.saveSettings()
        applyThemeMode(settings.isDarkMode)
    }

    // =========================================================================
    // Setup Primary Listeners & Controllers
    // =========================================================================

    @SuppressLint("ClickableViewAccessibility")
    private fun setupListeners() {
        // Theme Quick Toggle Button
        binding.btnThemeToggle.setOnClickListener {
            toggleTheme()
        }

        // Settings Dialog Button
        binding.btnSettings.setOnClickListener {
            haptics.clickFeedback()
            showSettingsDialog()
        }

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

        // Spotlight Beam (Camera Torch)
        binding.btnTorchPointer.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    haptics.heavyClickFeedback()
                    torchController.setTorch(true)
                    binding.btnTorchPointer.text = "Beam Active"
                    true
                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    torchController.setTorch(false)
                    binding.btnTorchPointer.text = "Spotlight"
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

        // Mode 2: Touchpad Dedicated Buttons
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

        binding.btnTaskView.setOnClickListener {
            haptics.heavyClickFeedback()
            hidController.taskView()
            Toast.makeText(this, "Task View", Toast.LENGTH_SHORT).show()
        }

        binding.btnEnterFullscreen.setOnClickListener {
            enterFullscreenTouchpad()
        }

        binding.btnExitFullscreen.setOnClickListener {
            exitFullscreenTouchpad()
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

        // Brightness Controllers
        binding.btnBrightnessUp.setOnClickListener {
            haptics.clickFeedback()
            hidController.brightnessUp()
        }

        binding.btnBrightnessDown.setOnClickListener {
            haptics.clickFeedback()
            hidController.brightnessDown()
        }

        // Status Pill Device Picker & Disconnect Quick Actions
        binding.btnDisconnect.setOnClickListener {
            haptics.clickFeedback()
            if (hidController.isConnected()) {
                hidController.disconnectDevice()
                Toast.makeText(this, "Disconnected from host", Toast.LENGTH_SHORT).show()
            } else {
                showDevicePickerDialog()
            }
        }

        binding.btnSelectDevice.setOnClickListener {
            haptics.clickFeedback()
            showDevicePickerDialog()
        }

        binding.statusPillLayout.setOnClickListener {
            haptics.clickFeedback()
            showDevicePickerDialog()
        }
    }

    private fun setupModeTabs() {
        binding.tabPresentation.setOnClickListener { setOperatingMode(OperatingMode.PRESENTATION) }
        binding.tabAirMouse.setOnClickListener { setOperatingMode(OperatingMode.AIR_MOUSE) }
        binding.tabKeyboard.setOnClickListener { setOperatingMode(OperatingMode.KEYBOARD) }
        binding.tabReels.setOnClickListener { setOperatingMode(OperatingMode.REEL_SCROLLER) }
    }

    // =========================================================================
    // Multi-Finger Apple Glass Touchpad Implementation
    // =========================================================================

    @SuppressLint("ClickableViewAccessibility")
    private fun setupTouchpadCanvas() {
        // Main Mode 2 Touchpad
        binding.mainTouchpadCanvas.setOnTouchListener(createTouchpadTouchListener(binding.tvTouchpadHint))

        // Fullscreen Trackpad Canvas
        binding.fullscreenTouchpadCanvas.setOnTouchListener(createTouchpadTouchListener(binding.tvFullscreenGestureHint))

        // Mini Trackpad in Keyboard tab
        binding.trackpadCanvas.setOnTouchListener(createTouchpadTouchListener(null))
    }

    private fun enterFullscreenTouchpad() {
        haptics.heavyClickFeedback()
        binding.fullscreenTouchpadOverlay.visibility = View.VISIBLE
    }

    private fun exitFullscreenTouchpad() {
        haptics.clickFeedback()
        binding.fullscreenTouchpadOverlay.visibility = View.GONE
    }

    /**
     * Precision Multi-Finger Touchpad Processor:
     * - 1 Finger Drag: Cursor translation with acceleration
     * - 1 Finger Tap: Left Click
     * - 2 Finger Drag: Fluid vertical scroll wheel
     * - 2 Finger Tap: Right Click (Context Menu)
     * - 3 Finger Swipe Up: Task View (Win+Tab)
     * - 3 Finger Swipe Down: Show Desktop (Win+D)
     * - 3 Finger Swipe Left / Right: Virtual Desktop Switch (Win+Ctrl+Arrow)
     */
    private fun createTouchpadTouchListener(hintView: TextView?): View.OnTouchListener {
        var startX = 0f
        var startY = 0f
        var lastX = 0f
        var lastY = 0f
        var lastScrollY = 0f
        var downTime = 0L
        var maxPointerCount = 1
        var totalDistance = 0f
        var threeFingerStartX = 0f
        var threeFingerStartY = 0f
        var threeFingerTriggered = false

        return View.OnTouchListener { _, event ->
            val pointerCount = event.pointerCount
            if (pointerCount > maxPointerCount) {
                maxPointerCount = pointerCount
            }

            when (event.actionMasked) {
                MotionEvent.ACTION_DOWN -> {
                    startX = event.x
                    startY = event.y
                    lastX = event.x
                    lastY = event.y
                    downTime = SystemClock.uptimeMillis()
                    maxPointerCount = 1
                    totalDistance = 0f
                    threeFingerTriggered = false
                    haptics.tickFeedback()
                    true
                }

                MotionEvent.ACTION_POINTER_DOWN -> {
                    if (pointerCount == 2) {
                        lastScrollY = (event.getY(0) + event.getY(1)) / 2f
                    } else if (pointerCount >= 3) {
                        threeFingerStartX = (event.getX(0) + event.getX(1) + event.getX(2)) / 3f
                        threeFingerStartY = (event.getY(0) + event.getY(1) + event.getY(2)) / 3f
                        threeFingerTriggered = false
                    }
                    true
                }

                MotionEvent.ACTION_MOVE -> {
                    if (pointerCount == 1 && maxPointerCount == 1) {
                        // 1 Finger: Smooth cursor displacement
                        val rawDx = event.x - lastX
                        val rawDy = event.y - lastY
                        totalDistance += Math.hypot(rawDx.toDouble(), rawDy.toDouble()).toFloat()

                        var speedMultiplier = settings.touchpadSensitivity
                        if (settings.accelEnabled) {
                            val vel = Math.hypot(rawDx.toDouble(), rawDy.toDouble()).toFloat()
                            if (vel > 12f) speedMultiplier *= 1.3f
                            if (vel > 28f) speedMultiplier *= 1.55f
                        }

                        val dx = (rawDx * speedMultiplier).toInt().coerceIn(-127, 127).toByte()
                        val dy = (rawDy * speedMultiplier).toInt().coerceIn(-127, 127).toByte()

                        if (dx.toInt() != 0 || dy.toInt() != 0) {
                            hidController.mouseMove(dx, dy)
                            wifiServer?.broadcastMotion(dx, dy, 0, 0)
                            lastX = event.x
                            lastY = event.y
                        }
                    } else if (pointerCount == 2) {
                        // 2 Fingers: Vertical scroll wheel
                        val currentY = (event.getY(0) + event.getY(1)) / 2f
                        val deltaY = currentY - lastScrollY
                        val scrollThreshold = 18f / settings.touchpadScrollSpeed.coerceAtLeast(0.3f)

                        if (Math.abs(deltaY) >= scrollThreshold) {
                            val wheelDelta = if (deltaY > 0) (-3).toByte() else 3.toByte()
                            hidController.scroll(wheelDelta)
                            wifiServer?.broadcastMotion(0, 0, wheelDelta, 0)
                            haptics.tickFeedback()
                            lastScrollY = currentY
                            hintView?.text = "Scrolling"
                        }
                    } else if (pointerCount >= 3 && !threeFingerTriggered) {
                        // 3 Fingers: Swipe gestures
                        val current3X = (event.getX(0) + event.getX(1) + event.getX(2)) / 3f
                        val current3Y = (event.getY(0) + event.getY(1) + event.getY(2)) / 3f
                        val delta3X = current3X - threeFingerStartX
                        val delta3Y = current3Y - threeFingerStartY

                        val swipeThreshold = 70f
                        if (Math.abs(delta3X) > swipeThreshold || Math.abs(delta3Y) > swipeThreshold) {
                            threeFingerTriggered = true
                            haptics.heavyClickFeedback()
                            if (Math.abs(delta3Y) > Math.abs(delta3X)) {
                                if (delta3Y < 0) {
                                    // Swipe UP: Task View
                                    hidController.taskView()
                                    hintView?.text = "Task View"
                                    Toast.makeText(this, "Task View", Toast.LENGTH_SHORT).show()
                                } else {
                                    // Swipe DOWN: Show Desktop
                                    hidController.showDesktop()
                                    hintView?.text = "Show Desktop"
                                    Toast.makeText(this, "Show Desktop", Toast.LENGTH_SHORT).show()
                                }
                            } else {
                                if (delta3X > 0) {
                                    // Swipe RIGHT: Next Desktop
                                    hidController.switchDesktopNext()
                                    hintView?.text = "Next Desktop"
                                    Toast.makeText(this, "Next Desktop", Toast.LENGTH_SHORT).show()
                                } else {
                                    // Swipe LEFT: Previous Desktop
                                    hidController.switchDesktopPrev()
                                    hintView?.text = "Previous Desktop"
                                    Toast.makeText(this, "Previous Desktop", Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                    }
                    true
                }

                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    val duration = SystemClock.uptimeMillis() - downTime
                    if (duration < 280) {
                        if (maxPointerCount == 1 && totalDistance < 14f) {
                            // 1-Finger Tap: Left Click
                            haptics.clickFeedback()
                            hidController.mouseClick(HidKeyCodes.MOUSE_LEFT)
                            wifiServer?.broadcastMotion(0, 0, 0, HidKeyCodes.MOUSE_LEFT)
                            hintView?.text = "Left Click"
                        } else if (maxPointerCount == 2) {
                            // 2-Finger Tap: Right Click
                            haptics.heavyClickFeedback()
                            hidController.mouseClick(HidKeyCodes.MOUSE_RIGHT)
                            wifiServer?.broadcastMotion(0, 0, 0, HidKeyCodes.MOUSE_RIGHT)
                            hintView?.text = "Right Click"
                        }
                    }
                    hintView?.postDelayed({
                        hintView.text = "1-Finger Cursor • 2-Finger Scroll • 3-Finger Swipe"
                    }, 1200)
                    true
                }

                else -> false
            }
        }
    }

    /**
     * Wireless Keyboard Mode setup: Live typing + Quick Keys + Shortcuts
     */
    private fun setupKeyboardMode() {
        binding.btnSendText.setOnClickListener {
            val text = binding.etKeyboardInput.text.toString()
            if (text.isNotEmpty()) {
                haptics.clickFeedback()
                hidController.sendText(text) {
                    binding.etKeyboardInput.setText("")
                }
            }
        }

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
    }

    /**
     * Apple Fluid Interaction: Instant scale down to 0.97 on press with spring return.
     */
    @SuppressLint("ClickableViewAccessibility")
    private fun setupSpringPressEffects() {
        val interactiveButtons = listOf(
            binding.btnNextSlide, binding.btnPrevSlide, binding.btnFullscreen, binding.btnBlackScreen,
            binding.btnLeftClick, binding.btnRightClick, binding.btnTaskView, binding.btnEnterFullscreen,
            binding.btnExitFullscreen, binding.btnNextReel, binding.btnPrevReel, binding.btnPlayPauseReel,
            binding.btnVolUp, binding.btnVolDown, binding.btnBrightnessUp, binding.btnBrightnessDown,
            binding.btnSettings, binding.btnThemeToggle, binding.btnKeyEsc, binding.btnKeyTab,
            binding.btnKeySpace, binding.btnKeyBackspace, binding.btnKeyReturn, binding.btnArrowUp,
            binding.btnArrowDown, binding.btnArrowLeft, binding.btnArrowRight, binding.btnKeyPgUp,
            binding.btnKeyPgDn, binding.btnKbMouseLeft, binding.btnKbMouseRight, binding.btnShortcutUndo,
            binding.btnShortcutCopy, binding.btnShortcutPaste, binding.btnShortcutSelectAll,
            binding.btnShortcutSearch, binding.btnSendText, binding.btnDisconnect
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

        // Hide soft keyboard if switching away from Keyboard mode
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
    // Connection State Handling (Apple Dynamic Status Pill)
    // =========================================================================

    private fun updateConnectionUI(state: ConnectionState, device: BluetoothDevice?) {
        val dot = binding.dotConnection.background as? GradientDrawable
        when (state) {
            ConnectionState.CONNECTED -> {
                binding.tvConnectionStatus.text = "CONNECTED"
                binding.tvConnectionStatus.setTextColor(ContextCompat.getColor(this, R.color.apple_green))
                binding.tvDeviceTarget.text = " • ${device?.name ?: "Host"}"
                dot?.setColor(ContextCompat.getColor(this, R.color.apple_green))
                binding.btnDisconnect.text = "Disconnect"
                binding.btnDisconnect.setTextColor(ContextCompat.getColor(this, R.color.apple_red))
                binding.btnDisconnect.visibility = View.VISIBLE
            }
            ConnectionState.CONNECTING -> {
                binding.tvConnectionStatus.text = "CONNECTING..."
                binding.tvConnectionStatus.setTextColor(ContextCompat.getColor(this, R.color.apple_amber))
                binding.tvDeviceTarget.text = " • ${device?.name ?: "Host"}"
                dot?.setColor(ContextCompat.getColor(this, R.color.apple_amber))
                binding.btnDisconnect.text = "Cancel"
                binding.btnDisconnect.setTextColor(ContextCompat.getColor(this, R.color.apple_amber))
                binding.btnDisconnect.visibility = View.VISIBLE
            }
            ConnectionState.ADVERTISING -> {
                binding.tvConnectionStatus.text = "PAIRING"
                binding.tvConnectionStatus.setTextColor(ContextCompat.getColor(this, R.color.apple_blue))
                binding.tvDeviceTarget.text = " • Select Device"
                dot?.setColor(ContextCompat.getColor(this, R.color.apple_blue))
                binding.btnDisconnect.text = "Connect"
                binding.btnDisconnect.setTextColor(ContextCompat.getColor(this, R.color.apple_blue))
                binding.btnDisconnect.visibility = View.VISIBLE
            }
            ConnectionState.DISCONNECTED, ConnectionState.REGISTERING -> {
                binding.tvConnectionStatus.text = "READY"
                binding.tvConnectionStatus.setTextColor(ContextCompat.getColor(this, R.color.apple_text_tertiary))
                binding.tvDeviceTarget.text = " • Select Device"
                dot?.setColor(ContextCompat.getColor(this, R.color.apple_well))
                binding.btnDisconnect.text = "Connect"
                binding.btnDisconnect.setTextColor(ContextCompat.getColor(this, R.color.apple_blue))
                binding.btnDisconnect.visibility = View.VISIBLE
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

        dialogBinding.switchDarkMode.isChecked = settings.isDarkMode
        dialogBinding.switchHaptics.isChecked = settings.hapticsEnabled
        dialogBinding.switchAccel.isChecked = settings.accelEnabled
        dialogBinding.switchVolumeKeys.isChecked = settings.volumeKeysEnabled

        dialogBinding.seekTouchpadSens.progress = (settings.touchpadSensitivity * 10).toInt().coerceIn(5, 30)
        dialogBinding.tvLabelTouchpadSens.text = String.format(Locale.US, "Tracking Speed: %.1fx", settings.touchpadSensitivity)

        dialogBinding.seekTouchpadScroll.progress = (settings.touchpadScrollSpeed * 10).toInt().coerceIn(3, 25)
        dialogBinding.tvLabelScrollSpeed.text = String.format(Locale.US, "Two-Finger Scroll Speed: %.1fx", settings.touchpadScrollSpeed)

        dialogBinding.switchDarkMode.setOnCheckedChangeListener { _, isChecked ->
            haptics.tickFeedback()
            settings.isDarkMode = isChecked
            app.saveSettings()
            applyThemeMode(isChecked)
        }

        dialogBinding.switchHaptics.setOnCheckedChangeListener { _, isChecked ->
            settings.hapticsEnabled = isChecked
            if (isChecked) {
                haptics.clickFeedback()
            }
        }

        dialogBinding.seekTouchpadSens.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(sb: SeekBar?, progress: Int, fromUser: Boolean) {
                val value = progress.coerceAtLeast(5) / 10f
                settings.touchpadSensitivity = value
                dialogBinding.tvLabelTouchpadSens.text = String.format(Locale.US, "Tracking Speed: %.1fx", value)
                if (fromUser) haptics.tickFeedback()
            }
            override fun onStartTrackingTouch(sb: SeekBar?) {}
            override fun onStopTrackingTouch(sb: SeekBar?) {}
        })

        dialogBinding.seekTouchpadScroll.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(sb: SeekBar?, progress: Int, fromUser: Boolean) {
                val value = progress.coerceAtLeast(3) / 10f
                settings.touchpadScrollSpeed = value
                dialogBinding.tvLabelScrollSpeed.text = String.format(Locale.US, "Two-Finger Scroll Speed: %.1fx", value)
                if (fromUser) haptics.tickFeedback()
            }
            override fun onStartTrackingTouch(sb: SeekBar?) {}
            override fun onStopTrackingTouch(sb: SeekBar?) {}
        })

        dialogBinding.switchAccel.setOnCheckedChangeListener { _, isChecked ->
            haptics.tickFeedback()
            settings.accelEnabled = isChecked
        }

        dialogBinding.switchVolumeKeys.setOnCheckedChangeListener { _, isChecked ->
            haptics.tickFeedback()
            settings.volumeKeysEnabled = isChecked
        }

        val openWebsite = {
            haptics.clickFeedback()
            try {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://zenithkandel.com.np"))
                startActivity(intent)
            } catch (e: Exception) {
                Toast.makeText(this, "zenithkandel.com.np", Toast.LENGTH_SHORT).show()
            }
        }
        dialogBinding.cardAboutAuthor.setOnClickListener { openWebsite() }
        dialogBinding.tvAuthorMention.setOnClickListener { openWebsite() }

        dialogBinding.btnCloseSettings.setOnClickListener {
            haptics.clickFeedback()
            app.saveSettings()
            dialog.dismiss()
        }

        dialog.show()
    }

    // =========================================================================
    // Paired Bluetooth Host Device Picker
    // =========================================================================

    private fun showDevicePickerDialog() {
        val dialogBinding = DialogDevicePickerBinding.inflate(layoutInflater)
        val dialog = AlertDialog.Builder(this)
            .setView(dialogBinding.root)
            .create()

        val adapter = BluetoothAdapter.getDefaultAdapter()
        val bonded = adapter?.bondedDevices?.toList() ?: emptyList()
        val currentConnected = hidController.getConnectedDevice()

        dialogBinding.containerDeviceList.removeAllViews()

        if (bonded.isEmpty()) {
            dialogBinding.tvNoDevices.visibility = View.VISIBLE
            dialogBinding.containerDeviceList.addView(dialogBinding.tvNoDevices)
        } else {
            dialogBinding.tvNoDevices.visibility = View.GONE
            bonded.forEach { device ->
                val itemBinding = ItemPairedDeviceBinding.inflate(layoutInflater, dialogBinding.containerDeviceList, false)
                val isConnected = currentConnected != null && currentConnected.address == device.address

                itemBinding.tvDeviceName.text = device.name ?: "Unknown Device"
                itemBinding.tvDeviceMac.text = device.address

                if (isConnected) {
                    itemBinding.tvDeviceStatusBadge.text = "CONNECTED"
                    itemBinding.tvDeviceStatusBadge.setTextColor(ContextCompat.getColor(this, R.color.apple_green))
                    itemBinding.ivDeviceIcon.setImageResource(R.drawable.ic_laptop)
                    itemBinding.ivDeviceIcon.setColorFilter(ContextCompat.getColor(this, R.color.apple_green))
                    itemBinding.rowDeviceRoot.setBackgroundResource(R.drawable.bg_apple_hero_card_accent)
                } else {
                    itemBinding.tvDeviceStatusBadge.text = "Connect"
                    itemBinding.tvDeviceStatusBadge.setTextColor(ContextCompat.getColor(this, R.color.apple_blue))
                    itemBinding.ivDeviceIcon.setImageResource(R.drawable.ic_laptop)
                    itemBinding.ivDeviceIcon.setColorFilter(ContextCompat.getColor(this, R.color.apple_text_secondary))
                    itemBinding.rowDeviceRoot.setBackgroundResource(R.drawable.bg_apple_btn_secondary)
                }

                itemBinding.rowDeviceRoot.setOnClickListener {
                    haptics.clickFeedback()
                    if (isConnected) {
                        Toast.makeText(this, "Already connected to ${device.name ?: device.address}", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Connecting to ${device.name ?: device.address}...", Toast.LENGTH_SHORT).show()
                        hidController.connectDevice(device)
                    }
                    dialog.dismiss()
                }

                dialogBinding.containerDeviceList.addView(itemBinding.root)
            }
        }

        // Disconnect Button in Dialog
        if (currentConnected != null) {
            dialogBinding.btnDisconnectDialog.visibility = View.VISIBLE
            dialogBinding.btnDisconnectDialog.text = "Disconnect from ${currentConnected.name ?: "Device"}"
            dialogBinding.btnDisconnectDialog.setOnClickListener {
                haptics.heavyClickFeedback()
                hidController.disconnectDevice()
                Toast.makeText(this, "Disconnected from host", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
        } else {
            dialogBinding.btnDisconnectDialog.visibility = View.GONE
        }

        // Pair New Device in Android System Settings
        dialogBinding.btnPairNewDevice.setOnClickListener {
            haptics.clickFeedback()
            try {
                startActivity(Intent(Settings.ACTION_BLUETOOTH_SETTINGS))
            } catch (e: Exception) {
                Toast.makeText(this, "Please open Android Bluetooth Settings", Toast.LENGTH_SHORT).show()
            }
            dialog.dismiss()
        }

        dialogBinding.btnCloseDevicePicker.setOnClickListener {
            haptics.tickFeedback()
            dialog.dismiss()
        }

        dialog.show()
    }
}
