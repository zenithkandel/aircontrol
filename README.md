# AirControl — Sentinal 3-in-1 Wireless Air Mouse & Controller for Android

[![Platform](https://img.shields.io/badge/Platform-Android%209.0%2B%20%28API%2028%2B%29-brightgreen.svg)]()
[![License](https://img.shields.io/badge/License-MIT-blue.svg)]()
[![Hardware Source](https://img.shields.io/badge/Origin-Sentinal%20ESP32--S3-orange.svg)](https://github.com/sakshyambastakoti/sentinal)

**AirControl** is the Android adaptation of [Sentinal](https://github.com/sakshyambastakoti/sentinal), the production-grade ESP32-S3 wireless air mouse and HID controller.

Using Android's native **Bluetooth HID Device profile** (`android.bluetooth.BluetoothHidDevice`), your smartphone transforms into a genuine **Composite Bluetooth Peripheral (Mouse + Keyboard + Consumer Control)** that connects seamlessly to **Windows 10/11, macOS, Linux, iPadOS, Android TV, and ChromeOS** — **without installing any desktop software**.

---

## 🌟 Features at a Glance

| Feature | Sentinal Hardware (ESP32-S3) | AirControl (Android App) |
| :--- | :--- | :--- |
| **Connectivity** | NimBLE BLE HID Composite Device | Native Android `BluetoothHidDevice` + Wi-Fi Fallback |
| **Motion Sensor** | MPU6050 6-Axis IMU (I2C) | Phone 6-Axis Gyroscope & Accelerometer (100 Hz Pipeline) |
| **Motion Pipeline** | Deadzone + EMA ($\alpha=0.45$) + Acceleration | **Exact 1:1 Port** of Sentinal's C++ `MotionProcessor` |
| **Mode 1: Presentation** | Next/Prev Slide, F5, Pause Motion | Next/Prev Slide, F5, Blank Screen, Slide Timer, Pause |
| **Mode 2: Air Mouse** | Left/Right Click, Gyro Aiming, Recalibrate | Left/Right Click, Center Scroll Strip, Clutch Freeze, Recalib |
| **Mode 3: Reel Scroller** | TikTok / Instagram Shorts Scroller | Down/Up Arrow, Play/Pause, Volume, Like, Assistive Scroll |
| **Laser Pointer** | 5V Diode Hardware Wire | **Torch Spotlight Beam** (Ultra-Bright Camera LED Flash) |
| **Tactile Buttons** | 3 GPIO Tactile Push Buttons | High-impact UI Pads + **Physical Phone Volume Rocker** |
| **Visual Indicators** | Tri-Color LED (Mode, Status, Action) | **Digital Tri-LED Simulation** (Exact blink & flash patterns) |
| **Feedback** | Single Action LED | **Rich Haptic Feedback** (Distinct vibration waveforms) |

---

## 🕹️ 3 Operating Modes

### Mode 1: Presentation Remote (1 LED Blink)
Designed for PowerPoint, Apple Keynote, Google Slides, and Canva.
- **Gyro Cursor Motion:** Paused automatically to eliminate shaky hands during presentations.
- **Next Slide:** Tap big Next button (Sends `KEY_PAGE_DOWN` / Left Click fallback).
- **Previous Slide:** Tap Previous button (Sends `KEY_PAGE_UP` / Wheel Up fallback).
- **Fullscreen Slideshow:** Tap `F5 FULLSCREEN` button.
- **Black Screen:** Tap `BLACK SCREEN` (`KEY_B`) to focus audience attention on the speaker.
- **Slide Stopwatch:** Built-in presentation timer with live elapsed minutes & seconds.
- **Torch Spotlight Pointer:** Hold to shine your smartphone's high-intensity camera flash at the projection screen or whiteboard!

### Mode 2: 6-Axis Gyro Air Mouse (2 LED Blinks)
Transforms your smartphone into an air mouse pointer driven by natural wrist rotation.
- **100 Hz Gyroscope Engine:** Direct port of Sentinal's angular velocity pipeline:
  - Micro-tremor continuous deadzone ramp ($1.25^\circ/\text{s}$)
  - First-order IIR smoothing filter ($\alpha = 0.45$)
  - Non-linear cursor acceleration curve ($22.0^\circ/\text{s}$ threshold, $3.5\times$ max scale)
  - Sub-pixel fractional accumulator for precision micro-aiming.
- **Left Click (Button A):** Large primary click pad.
- **Right Click (Button B):** Secondary context click pad.
- **Tactile Scroll Wheel:** Vertical strip for smooth document and web scrolling.
- **Hold to Freeze Pointer (Clutch):** Hold while adjusting wrist position so the cursor remains steady.
- **Stationary Recalibration:** 2-second zero-rate bias calibration with motion disturbance rejection.

### Mode 3: Social Reel / Short Video Scroller (3 LED Blinks)
Pure keyboard shortcut navigation for TikTok, Instagram Reels, and YouTube Shorts.
- **Next Reel:** Down arrow + scroll wheel down.
- **Previous Reel:** Up arrow + scroll wheel up.
- **Play / Pause:** Spacebar toggle.
- **Volume & Like:** Quick media keys.

---

## ⚡ Hardware Volume Rocker Support

You can control your computer or presentation **completely hands-free without looking at your phone**:
- **In Presentation Mode:** Volume Up = Next Slide, Volume Down = Prev Slide.
- **In Air Mouse Mode:** Volume Up = Left Click, Volume Down = Right Click.
- **In Reel Scroller Mode:** Volume Up = Previous Reel, Volume Down = Next Reel.

---

## 🔦 Flashlight / Torch Pointer (Laser Alternative)

Because standard smartphones lack a built-in laser diode, AirControl includes a **Torch Spotlight Beam**:
- Holding the Torch button triggers the phone's rear ultra-bright camera LED.
- Illuminates presentations, projector screens, or whiteboards from across the room.

---

## 📡 Connectivity Modes

### 1. Native Bluetooth HID (Recommended)
1. Turn on Bluetooth on your Android phone and launch **AirControl**.
2. The app registers as a standard Bluetooth HID Device named **`Sentinal`**.
3. On your PC, Mac, iPad, or TV, open **Bluetooth Settings** and click **Pair "Sentinal"**.
4. The Status LED turns solid blue. No PC software or drivers needed!

### 2. Wi-Fi Local Companion (Fallback)
If your computer lacks Bluetooth or your Android ROM restricts Bluetooth HID:
1. Run the Python companion on your computer:
   ```bash
   pip install websockets pyautogui
   python companion/sentinal_companion.py
   ```
2. The phone transmits motion and keypresses over your local Wi-Fi with ultra-low latency.

---

## 🛠️ Project Structure

```
aircontrol/
├── app/
│   ├── src/main/
│   │   ├── AndroidManifest.xml
│   │   ├── java/com/sentinal/aircontrol/
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
│   │       │   └── dialog_settings.xml
│   │       └── values/
│   └── build.gradle.kts
├── companion/
│   └── sentinal_companion.py
├── build.gradle.kts
├── settings.gradle.kts
└── gradlew.bat
```

---

## 🚀 Building & Installing

### Command Line
```bash
# Build debug APK
.\gradlew.bat assembleDebug

# Install directly to connected Android phone via ADB
adb install app/build/outputs/apk/debug/app-debug.apk
```

### Android Studio
Open the `aircontrol` folder in Android Studio and press **Run (Shift + F10)**.
