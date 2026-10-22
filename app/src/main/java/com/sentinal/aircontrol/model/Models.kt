package com.sentinal.aircontrol.model

/**
 * Operating Modes mirroring Sentinal v1.0.0 hardware.
 */
enum class OperatingMode(val id: Int, val title: String, val subtitle: String, val blinks: Int) {
    PRESENTATION(0, "PRESENTATION REMOTE", "PowerPoint / Keynote / Canva / Slides", 1),
    AIR_MOUSE(1, "6-AXIS AIR MOUSE", "Desktop PC / Mac / Smart TV Cursor", 2),
    REEL_SCROLLER(2, "REEL / SHORT SCROLLER", "Instagram / TikTok / YouTube Shorts", 3);

    companion object {
        fun fromId(id: Int): OperatingMode = entries.firstOrNull { it.id == id } ?: PRESENTATION
    }
}

enum class ConnectionState {
    DISCONNECTED,
    REGISTERING,
    ADVERTISING,
    CONNECTING,
    CONNECTED
}

enum class ConnectionMode {
    BLUETOOTH_HID,
    WIFI_NETWORK
}

data class MotionDisplacement(
    val dx: Byte = 0,
    val dy: Byte = 0,
    val wheel: Byte = 0,
    val hasMovement: Boolean = false
)

data class SettingsState(
    var sensitivityX: Float = 0.22f,
    var sensitivityY: Float = 0.22f,
    var deadzoneDps: Float = 1.25f,
    var filterAlpha: Float = 0.45f,
    var accelEnabled: Boolean = true,
    var accelThreshold: Float = 22.0f,
    var accelGain: Float = 0.018f,
    var accelMaxScale: Float = 3.5f,
    var invertX: Boolean = true,
    var invertY: Boolean = true,
    var volumeKeysEnabled: Boolean = true,
    var hapticsEnabled: Boolean = true,
    var torchPointerEnabled: Boolean = true
)

/**
 * Standard USB HID Keyboard and Consumer Control Keycodes.
 */
object HidKeyCodes {
    // Standard HID Keyboard Codes (Report ID 2)
    const val KEY_NONE: Byte = 0x00
    const val KEY_A: Byte = 0x04
    const val KEY_B: Byte = 0x05 // 'b' for Black screen in presentations
    const val KEY_RETURN: Byte = 0x28
    const val KEY_ESCAPE: Byte = 0x29
    const val KEY_BACKSPACE: Byte = 0x2A
    const val KEY_TAB: Byte = 0x2B
    const val KEY_SPACE: Byte = 0x2C
    const val KEY_F5: Byte = 0x3E // Start Slideshow
    const val KEY_PAGE_UP: Byte = 0x4B // Prev Slide
    const val KEY_PAGE_DOWN: Byte = 0x4E // Next Slide
    const val KEY_RIGHT_ARROW: Byte = 0x4F
    const val KEY_LEFT_ARROW: Byte = 0x50
    const val KEY_DOWN_ARROW: Byte = 0x51 // Next Video
    const val KEY_UP_ARROW: Byte = 0x52 // Prev Video

    // Modifier masks
    const val MOD_NONE: Byte = 0x00
    const val MOD_LCTRL: Byte = 0x01
    const val MOD_LSHIFT: Byte = 0x02
    const val MOD_LALT: Byte = 0x04
    const val MOD_LGUI: Byte = 0x08

    // Mouse Buttons (Report ID 1)
    const val MOUSE_NONE: Byte = 0x00
    const val MOUSE_LEFT: Byte = 0x01
    const val MOUSE_RIGHT: Byte = 0x02
    const val MOUSE_MIDDLE: Byte = 0x04

    // Consumer Device Usages (Report ID 3)
    const val CONSUMER_PLAY_PAUSE: Short = 0x00CD
    const val CONSUMER_VOLUME_INCREMENT: Short = 0x00E9
    const val CONSUMER_VOLUME_DECREMENT: Short = 0x00EA
    const val CONSUMER_MUTE: Short = 0x00E2
}
