package com.sentinal.aircontrol.model

/**
 * AirControl Operating Modes.
 */
enum class OperatingMode(val id: Int, val title: String, val subtitle: String, val blinks: Int) {
    PRESENTATION(0, "SLIDES", "Keynote / PowerPoint", 1),
    AIR_MOUSE(1, "AIR MOUSE", "Motion Pointer", 2),
    KEYBOARD(2, "KEYBOARD", "Wireless Text & Shortcuts", 3),
    REEL_SCROLLER(3, "MEDIA", "Social & Reels", 4);

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
    const val KEY_B: Byte = 0x05
    const val KEY_C: Byte = 0x06
    const val KEY_D: Byte = 0x07
    const val KEY_E: Byte = 0x08
    const val KEY_F: Byte = 0x09
    const val KEY_G: Byte = 0x0A
    const val KEY_H: Byte = 0x0B
    const val KEY_I: Byte = 0x0C
    const val KEY_J: Byte = 0x0D
    const val KEY_K: Byte = 0x0E
    const val KEY_L: Byte = 0x0F
    const val KEY_M: Byte = 0x10
    const val KEY_N: Byte = 0x11
    const val KEY_O: Byte = 0x12
    const val KEY_P: Byte = 0x13
    const val KEY_Q: Byte = 0x14
    const val KEY_R: Byte = 0x15
    const val KEY_S: Byte = 0x16
    const val KEY_T: Byte = 0x17
    const val KEY_U: Byte = 0x18
    const val KEY_V: Byte = 0x19
    const val KEY_W: Byte = 0x1A
    const val KEY_X: Byte = 0x1B
    const val KEY_Y: Byte = 0x1C
    const val KEY_Z: Byte = 0x1D

    const val KEY_1: Byte = 0x1E
    const val KEY_2: Byte = 0x1F
    const val KEY_3: Byte = 0x20
    const val KEY_4: Byte = 0x21
    const val KEY_5: Byte = 0x22
    const val KEY_6: Byte = 0x23
    const val KEY_7: Byte = 0x24
    const val KEY_8: Byte = 0x25
    const val KEY_9: Byte = 0x26
    const val KEY_0: Byte = 0x27

    const val KEY_RETURN: Byte = 0x28
    const val KEY_ESCAPE: Byte = 0x29
    const val KEY_BACKSPACE: Byte = 0x2A
    const val KEY_TAB: Byte = 0x2B
    const val KEY_SPACE: Byte = 0x2C
    const val KEY_MINUS: Byte = 0x2D
    const val KEY_EQUAL: Byte = 0x2E
    const val KEY_LEFTBRACE: Byte = 0x2F
    const val KEY_RIGHTBRACE: Byte = 0x30
    const val KEY_BACKSLASH: Byte = 0x31
    const val KEY_SEMICOLON: Byte = 0x33
    const val KEY_APOSTROPHE: Byte = 0x34
    const val KEY_GRAVE: Byte = 0x35
    const val KEY_COMMA: Byte = 0x36
    const val KEY_DOT: Byte = 0x37
    const val KEY_SLASH: Byte = 0x38

    const val KEY_F1: Byte = 0x3A
    const val KEY_F2: Byte = 0x3B
    const val KEY_F3: Byte = 0x3C
    const val KEY_F4: Byte = 0x3D
    const val KEY_F5: Byte = 0x3E
    const val KEY_PAGE_UP: Byte = 0x4B
    const val KEY_PAGE_DOWN: Byte = 0x4E
    const val KEY_RIGHT_ARROW: Byte = 0x4F
    const val KEY_LEFT_ARROW: Byte = 0x50
    const val KEY_DOWN_ARROW: Byte = 0x51
    const val KEY_UP_ARROW: Byte = 0x52

    // Modifier masks
    const val MOD_NONE: Byte = 0x00
    const val MOD_LCTRL: Byte = 0x01
    const val MOD_LSHIFT: Byte = 0x02
    const val MOD_LALT: Byte = 0x04
    const val MOD_LGUI: Byte = 0x08 // Windows key / Command key

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

    /**
     * Maps an ASCII character to its HID (keyCode, modifier) pair.
     */
    fun charToHid(c: Char): Pair<Byte, Byte>? {
        return when (c) {
            in 'a'..'z' -> Pair((KEY_A + (c - 'a')).toByte(), MOD_NONE)
            in 'A'..'Z' -> Pair((KEY_A + (c - 'A')).toByte(), MOD_LSHIFT)
            in '1'..'9' -> Pair((KEY_1 + (c - '1')).toByte(), MOD_NONE)
            '0' -> Pair(KEY_0, MOD_NONE)
            ' ' -> Pair(KEY_SPACE, MOD_NONE)
            '\n' -> Pair(KEY_RETURN, MOD_NONE)
            '\t' -> Pair(KEY_TAB, MOD_NONE)
            '\b' -> Pair(KEY_BACKSPACE, MOD_NONE)
            '!' -> Pair(KEY_1, MOD_LSHIFT)
            '@' -> Pair(KEY_2, MOD_LSHIFT)
            '#' -> Pair(KEY_3, MOD_LSHIFT)
            '$' -> Pair(KEY_4, MOD_LSHIFT)
            '%' -> Pair(KEY_5, MOD_LSHIFT)
            '^' -> Pair(KEY_6, MOD_LSHIFT)
            '&' -> Pair(KEY_7, MOD_LSHIFT)
            '*' -> Pair(KEY_8, MOD_LSHIFT)
            '(' -> Pair(KEY_9, MOD_LSHIFT)
            ')' -> Pair(KEY_0, MOD_LSHIFT)
            '-' -> Pair(KEY_MINUS, MOD_NONE)
            '_' -> Pair(KEY_MINUS, MOD_LSHIFT)
            '=' -> Pair(KEY_EQUAL, MOD_NONE)
            '+' -> Pair(KEY_EQUAL, MOD_LSHIFT)
            '[' -> Pair(KEY_LEFTBRACE, MOD_NONE)
            '{' -> Pair(KEY_LEFTBRACE, MOD_LSHIFT)
            ']' -> Pair(KEY_RIGHTBRACE, MOD_NONE)
            '}' -> Pair(KEY_RIGHTBRACE, MOD_LSHIFT)
            '\\' -> Pair(KEY_BACKSLASH, MOD_NONE)
            '|' -> Pair(KEY_BACKSLASH, MOD_LSHIFT)
            ';' -> Pair(KEY_SEMICOLON, MOD_NONE)
            ':' -> Pair(KEY_SEMICOLON, MOD_LSHIFT)
            '\'' -> Pair(KEY_APOSTROPHE, MOD_NONE)
            '"' -> Pair(KEY_APOSTROPHE, MOD_LSHIFT)
            '`' -> Pair(KEY_GRAVE, MOD_NONE)
            '~' -> Pair(KEY_GRAVE, MOD_LSHIFT)
            ',' -> Pair(KEY_COMMA, MOD_NONE)
            '<' -> Pair(KEY_COMMA, MOD_LSHIFT)
            '.' -> Pair(KEY_DOT, MOD_NONE)
            '>' -> Pair(KEY_DOT, MOD_LSHIFT)
            '/' -> Pair(KEY_SLASH, MOD_NONE)
            '?' -> Pair(KEY_SLASH, MOD_LSHIFT)
            else -> null
        }
    }
}
