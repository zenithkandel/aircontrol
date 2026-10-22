@echo off
echo ========================================================
echo Installing AirControl (Sentinal for Android) via ADB...
echo ========================================================
"%LOCALAPPDATA%\Android\Sdk\platform-tools\adb.exe" install -r app\build\outputs\apk\debug\app-debug.apk
if %ERRORLEVEL% equ 0 (
    echo.
    echo [SUCCESS] AirControl installed successfully!
    echo Launching app on device...
    "%LOCALAPPDATA%\Android\Sdk\platform-tools\adb.exe" shell am start -n com.sentinal.aircontrol/.ui.MainActivity
) else (
    echo.
    echo [NOTE] Make sure your Android device is connected with USB Debugging enabled.
    echo Or manually copy 'app\build\outputs\apk\debug\app-debug.apk' to your phone to install.
)
pause
