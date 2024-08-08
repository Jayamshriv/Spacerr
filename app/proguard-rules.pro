-keep,allowobfuscation,allowshrinking class kotlin.coroutines.Continuation
-keep class org.joda.time.** { *; }
-keep class org.joda.** { *; }
-dontwarn org.joda.convert.**
 # R8 full mode strips generic signatures from return types if not kept.
 -if interface * { @retrofit2.http.* public *** *(...); }
 -keep,allowoptimization,allowshrinking,allowobfuscation class <3>

 # With R8 full mode generic signatures are stripped for classes that are not kept.
 -keep,allowobfuscation,allowshrinking class retrofit2.Response

-dontwarn com.oracle.svm.core.annotate.**


 -keep class retrofit.** { *; }
 -keepclassmembers class * {
     @retrofit2.http.* <methods>;
 }

-keep class okhttp3.** { *; }
-keep class com.google.gson.** { *; }
-keep class com.example.app.json.** { *; }
-keep @androidx.annotation.Keep public class *
-dontwarn org.bouncycastle.jsse.BCSSLParameters
-dontwarn org.bouncycastle.jsse.BCSSLSocket
-dontwarn org.bouncycastle.jsse.provider.BouncyCastleJsseProvider
-dontwarn org.conscrypt.Conscrypt$Version
-dontwarn org.conscrypt.Conscrypt
-dontwarn org.conscrypt.ConscryptHostnameVerifier
-dontwarn org.openjsse.javax.net.ssl.SSLParameters
-dontwarn org.openjsse.javax.net.ssl.SSLSocket
-dontwarn org.openjsse.net.ssl.OpenJSSE
-keepattributes Signature #
-dontwarn kotlin.reflect.jvm.internal.**
-keep class kotlin.reflect.jvm.internal.** { *; }
-keepnames class com.example.antrikshgyan.**
-keep class com.example.antrikshgyan.** { *; }
-keep,allowobfuscation,allowshrinking class retrofit2.Response
-keep,allowobfuscation,allowshrinking class kotlin.coroutines.Continuation
-keep,allowobfuscation,allowshrinking class com.google.gson.reflect.TypeToken