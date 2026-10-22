package com.sentinal.aircontrol.hid

/**
 * Universal Composite HID Report Descriptor (Mouse + Keyboard + Consumer Controls)
 * Identical byte-for-byte to Sentinal v1.0.0 firmware.
 * Fully compatible with Windows 10/11, macOS, Linux, Android, and Apple iOS.
 */
object HidDescriptors {

    const val REPORT_ID_MOUSE: Byte = 1
    const val REPORT_ID_KEYBOARD: Byte = 2
    const val REPORT_ID_CONSUMER: Byte = 3

    val REPORT_MAP: ByteArray = byteArrayOf(
        // Report ID 1: Mouse (3 Buttons + Relative X/Y + Wheel)
        0x05.toByte(), 0x01.toByte(),                    // USAGE_PAGE (Generic Desktop)
        0x09.toByte(), 0x02.toByte(),                    // USAGE (Mouse)
        0xa1.toByte(), 0x01.toByte(),                    // COLLECTION (Application)
        0x85.toByte(), 0x01.toByte(),                    //   REPORT_ID (1)
        0x09.toByte(), 0x01.toByte(),                    //   USAGE (Pointer)
        0xa1.toByte(), 0x00.toByte(),                    //   COLLECTION (Physical)
        0x05.toByte(), 0x09.toByte(),                    //     USAGE_PAGE (Button)
        0x19.toByte(), 0x01.toByte(),                    //     USAGE_MINIMUM (Button 1)
        0x29.toByte(), 0x03.toByte(),                    //     USAGE_MAXIMUM (Button 3)
        0x15.toByte(), 0x00.toByte(),                    //     LOGICAL_MINIMUM (0)
        0x25.toByte(), 0x01.toByte(),                    //     LOGICAL_MAXIMUM (1)
        0x75.toByte(), 0x01.toByte(),                    //     REPORT_SIZE (1)
        0x95.toByte(), 0x03.toByte(),                    //     REPORT_COUNT (3)
        0x81.toByte(), 0x02.toByte(),                    //     INPUT (Data,Var,Abs)
        0x75.toByte(), 0x05.toByte(),                    //     REPORT_SIZE (5)
        0x95.toByte(), 0x01.toByte(),                    //     REPORT_COUNT (1)
        0x81.toByte(), 0x03.toByte(),                    //     INPUT (Cnst,Var,Abs) - Padding
        0x05.toByte(), 0x01.toByte(),                    //     USAGE_PAGE (Generic Desktop)
        0x09.toByte(), 0x30.toByte(),                    //     USAGE (X)
        0x09.toByte(), 0x31.toByte(),                    //     USAGE (Y)
        0x09.toByte(), 0x38.toByte(),                    //     USAGE (Wheel)
        0x15.toByte(), 0x81.toByte(),                    //     LOGICAL_MINIMUM (-127)
        0x25.toByte(), 0x7f.toByte(),                    //     LOGICAL_MAXIMUM (127)
        0x75.toByte(), 0x08.toByte(),                    //     REPORT_SIZE (8)
        0x95.toByte(), 0x03.toByte(),                    //     REPORT_COUNT (3)
        0x81.toByte(), 0x06.toByte(),                    //     INPUT (Data,Var,Rel)
        0xc0.toByte(),                                  //   END_COLLECTION
        0xc0.toByte(),                                  // END_COLLECTION

        // Report ID 2: Keyboard (Standard 6-Key Rollover + Modifiers + Output LEDs)
        0x05.toByte(), 0x01.toByte(),                    // USAGE_PAGE (Generic Desktop)
        0x09.toByte(), 0x06.toByte(),                    // USAGE (Keyboard)
        0xa1.toByte(), 0x01.toByte(),                    // COLLECTION (Application)
        0x85.toByte(), 0x02.toByte(),                    //   REPORT_ID (2)
        0x05.toByte(), 0x07.toByte(),                    //   USAGE_PAGE (Keyboard/Keypad)
        0x19.toByte(), 0xe0.toByte(),                    //   USAGE_MINIMUM (Keyboard Left Control)
        0x29.toByte(), 0xe7.toByte(),                    //   USAGE_MAXIMUM (Keyboard Right GUI)
        0x15.toByte(), 0x00.toByte(),                    //   LOGICAL_MINIMUM (0)
        0x25.toByte(), 0x01.toByte(),                    //   LOGICAL_MAXIMUM (1)
        0x75.toByte(), 0x01.toByte(),                    //   REPORT_SIZE (1)
        0x95.toByte(), 0x08.toByte(),                    //   REPORT_COUNT (8)
        0x81.toByte(), 0x02.toByte(),                    //   INPUT (Data,Var,Abs) - Modifier Byte
        0x95.toByte(), 0x01.toByte(),                    //   REPORT_COUNT (1)
        0x75.toByte(), 0x08.toByte(),                    //   REPORT_SIZE (8)
        0x81.toByte(), 0x01.toByte(),                    //   INPUT (Cnst,Ary,Abs) - Reserved Byte (0x01)
        0x95.toByte(), 0x05.toByte(),                    //   REPORT_COUNT (5)
        0x75.toByte(), 0x01.toByte(),                    //   REPORT_SIZE (1)
        0x05.toByte(), 0x08.toByte(),                    //   USAGE_PAGE (LEDs)
        0x19.toByte(), 0x01.toByte(),                    //   USAGE_MINIMUM (Num Lock)
        0x29.toByte(), 0x05.toByte(),                    //   USAGE_MAXIMUM (Kana)
        0x91.toByte(), 0x02.toByte(),                    //   OUTPUT (Data,Var,Abs) - LED Output Report
        0x95.toByte(), 0x01.toByte(),                    //   REPORT_COUNT (1)
        0x75.toByte(), 0x03.toByte(),                    //   REPORT_SIZE (3)
        0x91.toByte(), 0x01.toByte(),                    //   OUTPUT (Cnst,Ary,Abs) - LED Padding (0x01)
        0x95.toByte(), 0x06.toByte(),                    //   REPORT_COUNT (6)
        0x75.toByte(), 0x08.toByte(),                    //   REPORT_SIZE (8)
        0x15.toByte(), 0x00.toByte(),                    //   LOGICAL_MINIMUM (0)
        0x25.toByte(), 0x65.toByte(),                    //   LOGICAL_MAXIMUM (101 keys)
        0x05.toByte(), 0x07.toByte(),                    //   USAGE_PAGE (Keyboard/Keypad)
        0x19.toByte(), 0x00.toByte(),                    //   USAGE_MINIMUM (0)
        0x29.toByte(), 0x65.toByte(),                    //   USAGE_MAXIMUM (101)
        0x81.toByte(), 0x00.toByte(),                    //   INPUT (Data,Ary,Abs) - 6 Key Array
        0xc0.toByte(),                                  // END_COLLECTION

        // Report ID 3: Consumer Devices (Media / Volume / Play / Pause)
        0x05.toByte(), 0x0c.toByte(),                    // USAGE_PAGE (Consumer Devices)
        0x09.toByte(), 0x01.toByte(),                    // USAGE (Consumer Control)
        0xa1.toByte(), 0x01.toByte(),                    // COLLECTION (Application)
        0x85.toByte(), 0x03.toByte(),                    //   REPORT_ID (3)
        0x15.toByte(), 0x00.toByte(),                    //   LOGICAL_MINIMUM (0)
        0x26.toByte(), 0xff.toByte(), 0x03.toByte(),    //   LOGICAL_MAXIMUM (1023)
        0x19.toByte(), 0x00.toByte(),                    //   USAGE_MINIMUM (0)
        0x2a.toByte(), 0xff.toByte(), 0x03.toByte(),    //   USAGE_MAXIMUM (1023)
        0x75.toByte(), 0x10.toByte(),                    //   REPORT_SIZE (16)
        0x95.toByte(), 0x01.toByte(),                    //   REPORT_COUNT (1)
        0x81.toByte(), 0x00.toByte(),                    //   INPUT (Data,Ary,Abs)
        0xc0.toByte()                                   // END_COLLECTION
    )
}
