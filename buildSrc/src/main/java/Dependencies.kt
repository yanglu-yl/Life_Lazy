/**
 * Author: creat by LuLu at 2021/04/09
 * Describe: Manage dependence
 */
object Versions {

    val kotlin_stdlib = "kotlin_version"
    val core_ktx = "1.2.0"
    val appcompat = "1.1.0"
    val material = "1.1.0"
    val constraintlayout = "1.1.3"
    val navigation_fragment_ktx = "2.2.2"
    val navigation_ui_ktx = "2.2.2"
    val legacy_support_v4 = "1.0.0"
    val junit = "4.+"
    val androidx_junit = "1.1.1"
    val espresso_core = "3.2.0"

    val logger = "2.2.0"
    val liveeventbus_x = "1.7.3"
    val arouter = "latest.release"
    val arouter_compiler = "latest.release"
    val okhttp = "4.8.1"
    val logging_interceptor = "4.8.0"
    val retrofit = "2.9.0"
    val converter_gson = "2.9.0"
    val LoggingInterceptor = "3.1.0"
    val kotlinx_coroutines_core = "1.3.0"
    val kotlinx_coroutines_android = "1.3.0"

    //jetpack
    val lifecycle_viewmodel_ktx = "2.1.0-alpha03"
}

object Libs {

    //系统库
    val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib:$${Versions.kotlin_stdlib}"
    val core_ktx = "androidx.core:core-ktx:${Versions.core_ktx}"
    val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    val material = "com.google.android.material:material:${Versions.material}"
    val constraintlayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintlayout}"
    val navigation_fragment_ktx = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation_fragment_ktx}"
    val navigation_ui_ktx = "androidx.navigation:navigation-ui-ktx:${Versions.navigation_ui_ktx}"
    val legacy_support_v4 = "androidx.legacy:legacy-support-v4:${Versions.legacy_support_v4}"
    val junit = "junit:junit:${Versions.junit}"
    val androidx_junit = "androidx.test.ext:junit:${Versions.androidx_junit}"
    val espresso_core = "androidx.test.espresso:espresso-core:${Versions.espresso_core}"

    //三方库
    val logger = "com.orhanobut:logger:${Versions.logger}"
    val liveeventbus_x = "com.jeremyliao:live-event-bus-x:${Versions.liveeventbus_x}"
    val arouter = "com.alibaba:arouter-api:${Versions.arouter}"
    val arouter_compiler = "com.alibaba:arouter-compiler:${Versions.arouter_compiler}"
    //okhttp
    val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    val logging_interceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.logging_interceptor}"
    //retrofit
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val converter_gson = "com.squareup.retrofit2:converter-gson:${Versions.converter_gson}"

    //LoggingInterceptor(okhttp日志拦截器)
    val LoggingInterceptor = "com.github.ihsanbal:LoggingInterceptor:${Versions.LoggingInterceptor}"

    val kotlinx_coroutines_core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinx_coroutines_core}"
    val kotlinx_coroutines_android  = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlinx_coroutines_android}"

    val lifecycle_viewmodel_ktx =  "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle_viewmodel_ktx}"
}