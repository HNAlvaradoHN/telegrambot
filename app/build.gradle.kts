plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.hnalvarado.telegramshare"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.hnalvarado.telegramshare"
        minSdk = 25
        targetSdk = 35
        versionCode = 1
        versionName = "0.1.0-dev"
    }
}
