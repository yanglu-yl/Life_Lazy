/**
 * Author: creat by LuLu at 2021/05/21
 * Describe: Manage compilation tool
 */
object AndroidConfig {
    val compileSdkVersion = 30
    val buildToolsVersion = "30.0.2"
    val defaultConfig = DefaultConfig()

    class DefaultConfig{
        val applicationId = "com.example.life_lazy"
        val minSdkVersion = 16
        val targetSdkVersion = 30
        val versionCode = 1
        val versionName = "1.0"

        val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}