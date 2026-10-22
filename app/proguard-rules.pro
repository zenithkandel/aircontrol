# ProGuard configuration for AirControl
-keep class com.sentinal.aircontrol.model.** { *; }
-keepclassmembers class * {
    @android.webkit.JavascriptInterface <methods>;
}
