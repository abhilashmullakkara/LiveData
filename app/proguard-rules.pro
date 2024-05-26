# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
-keepclassmembers class * { public <init>(...); }
 -keepattributes Signature
  -keepclassmembers class com.abhilash.livedata.ui.theme.room** {
       *;
     }
     # Keep all classes in the com.appnext package
     -keep class com.appnext.** { *; }

     # Do not warn about com.appnext classes
     -dontwarn com.appnext.**
