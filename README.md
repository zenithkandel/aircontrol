# AirControl — Universal Wireless Spatial Remote & Trackpad for Android

[![Platform](https://img.shields.io/badge/Platform-Android%209.0%2B%20%28API%2028%2B%29-brightgreen.svg)]()
[![Language](https://img.shields.io/badge/Language-Kotlin%20%2F%20Android%20SDK-purple.svg)]()
[![Design](https://img.shields.io/badge/Design-Apple%20Human%20Interface-007AFF.svg)]()
[![Author](https://img.shields.io/badge/Author-Zenith%20Kandel-black.svg)](https://zenithkandel.com.np)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)]()

**AirControl** is a high-precision, wireless spatial controller and multi-touch trackpad application for Android. Built upon native **Bluetooth Low Energy (BLE) / Classic HID Peripheral architecture** (`android.bluetooth.BluetoothHidDevice`), AirControl transforms your smartphone into a driverless, universal hardware remote control compatible with **Windows 10/11, macOS, Linux, iPadOS, Android TV, and ChromeOS**.

No client software, background daemons, or device drivers are required on the host computer. Pair via Bluetooth and control immediately.

---

## 🌟 Key Capabilities

- **Native Bluetooth HID Peripheral:** Registers as a composite hardware device (Mouse + Keyboard + Consumer Media Control) directly in host Bluetooth menus.
- **Glass-Feel Multi-Touch Trackpad:** Apple-inspired virtual trackpad surface with 1-finger tracking, physical velocity acceleration, tap-to-click, and smooth two-finger kinetic scrolling.
- **Presentation Remote Mode:** Designed for PowerPoint, Keynote, Google Slides, and Canva with slide advance, back, fullscreen toggle (F5), blank screen (B), and a live stopwatch.
- **Short-Form Media Controller:** Seamless scrolling and playback controls for TikTok, YouTube Shorts, and Instagram Reels with swipe cues, play/pause toggle, and audio adjustment.
- **Flashlight Spotlight Beam:** Utilizes the rear camera flash LED to provide an optical spotlight beam during live presentations.
- **Taptic Audio-Haptic Response:** Calibrated micro-vibrations for tactile clicks, mode changes, and dial snaps.
- **Hardware Volume Rocker Integration:** Physical volume buttons trigger slides, media, or mouse clicks without looking at the screen.
- **Wi-Fi Companion Fallback:** Built-in high-speed WebSocket server (`port 8765`) and Python companion receiver for devices without Bluetooth.

---

## 🕹️ Operational Modes

### 1. Presentation Remote (`SLIDES`)
- **Next Slide:** Large primary advance pad (Sends `KEY_PAGE_DOWN`).
- **Previous Slide:** Secondary advance pad (Sends `KEY_PAGE_UP`).
- **Fullscreen Show:** F5 toggle.
- **Black Screen:** Instant blanking (`KEY_B`) to focus audience attention.
- **Presentation Timer:** On-screen stopwatch with elapsed minutes and seconds.
- **Spotlight Beam:** Hold button to project device camera flash onto presentation screens.

### 2. Multi-Touch Air Mouse & Trackpad (`TOUCHPAD`)
- **Single Finger Navigation:** Smooth cursor motion with non-linear velocity scaling.
- **Tap to Click:** Primary left click with instant haptic confirmation.
- **Two-Finger Scroll:** Natural kinetic scrolling for long web pages and documents.
- **Dedicated Click Pads:** Physical Left Click and Right Click context buttons.
- **Clutch Freeze:** Hold freeze pad to reposition hand without moving the host cursor.
- **6-Axis Motion Pipeline:** Optional 100 Hz angular velocity gyroscope tracking with bias subtraction and low-pass filtering.

### 3. Keyboard & Text Input (`KEYBOARD`)
- **Direct Keystroke Transmission:** Full HID keystroke emulation for rapid text entry and system shortcuts.
- **Standard Controls:** Return, Backspace, Tab, Space, Esc, and arrow keys.

### 4. Media & Social Reels (`MEDIA`)
- **Next / Previous Reel:** Seamless up/down navigation.
- **Play / Pause:** Spacebar media toggle.
- **Volume & Likes:** Quick media controls for TikTok, Instagram Reels, and YouTube Shorts.

---

## ⚙️ Hardware Volume Button Remapping

Control presentations and media completely hands-free:
- **In Presentation Mode:** Volume Up = Next Slide, Volume Down = Previous Slide.
- **In Trackpad Mode:** Volume Up = Left Click, Volume Down = Right Click.
- **In Media Mode:** Volume Up = Previous Reel, Volume Down = Next Reel.

---

## 📡 Connectivity Options

### 1. Direct Bluetooth HID (Default & Recommended)
1. Turn on Bluetooth on your Android device and open **AirControl**.
2. Tap the **Bluetooth Status Pill** in the app header or select **Pair Host**.
3. On your PC, Mac, iPad, or TV, open **Bluetooth Settings** and select **AirControl**.
4. Once paired, control works instantly across all operating systems without any software installation.

### 2. Wi-Fi Local Companion (Fallback)
If your computer lacks Bluetooth or host security blocks Bluetooth HID devices:
1. Run the Python companion receiver on your computer:
   ```bash
   pip install websockets pyautogui
   python companion/aircontrol_companion.py
   ```
2. Both devices connect over the same local Wi-Fi network on port `8765` with low-latency WebSocket communication.

---

## 📁 Repository Structure

```
aircontrol/
├── app/
│   ├── src/main/
│   │   ├── AndroidManifest.xml
│   │   ├── java/com/aircontrol/
│   │   │   ├── AirControlApp.kt
│   │   │   ├── hardware/
│   │   │   │   ├── HapticFeedbackController.kt
│   │   │   │   └── TorchPointerController.kt
│   │   │   ├── hid/
│   │   │   │   ├── BluetoothHidController.kt
│   │   │   │   └── HidDescriptors.kt
│   │   │   ├── model/
│   │   │   │   └── Models.kt
│   │   │   ├── motion/
│   │   │   │   ├── MotionProcessor.kt
│   │   │   │   └── SensorController.kt
│   │   │   ├── ui/
│   │   │   │   └── MainActivity.kt
│   │   │   └── wifi/
│   │   │       └── WifiRemoteServer.kt
│   │   └── res/
│   │       ├── drawable/
│   │       ├── layout/
│   │       │   ├── activity_main.xml
│   │       │   ├── dialog_device_picker.xml
│   │       │   ├── dialog_settings.xml
│   │       │   └── item_paired_device.xml
│   │       └── values/
│   │           ├── colors.xml
│   │           ├── strings.xml
│   │           └── styles.xml
│   ├── build.gradle.kts
│   └── proguard-rules.pro
├── companion/
│   └── aircontrol_companion.py
├── build.gradle.kts
├── settings.gradle.kts
├── gradlew
├── gradlew.bat
└── install.bat
```

---

## 🛠️ Building & Deploying

### Prerequisites
- Android Studio Ladybug / Koala or Android SDK Command-Line Tools
- Java JDK 17+
- Android Device running Android 9.0+ (API 28+) with Bluetooth support

### Build via Command Line
```bash
# Build Debug APK
.\gradlew.bat assembleDebug

# Deploy to connected device via ADB
.\install.bat
```

### Manual ADB Install
```bash
adb install -r app/build/outputs/apk/debug/app-debug.apk
adb shell am start -n com.aircontrol/.ui.MainActivity
```

---

## 👨‍💻 Author & Attribution

**AirControl** is designed and crafted by:
- **Zenith Kandel** — [zenithkandel.com.np](https://zenithkandel.com.np)

---

## 📄 License

This project is licensed under the MIT License. See [LICENSE](LICENSE) for details.
