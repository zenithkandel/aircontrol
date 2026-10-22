# ProGuard configuration for AirControl
-keep class com.aircontrol.model.** { *; }
-keepclassmembers class * {
    @android.webkit.JavascriptInterface <methods>;
}
