@file:Suppress("UnstableApiUsage")

plugins {
  id ("com.android.library")
  id ("org.jetbrains.kotlin.android")
  id("com.google.dagger.hilt.android")
  kotlin("kapt")
}

android {
  compileSdk = libs.versions.compileSdk.get().toInt()

  defaultConfig {
    minSdk = libs.versions.minSdk.get().toInt()

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    // consumerProguardFiles = "consumer-rules.pro"
  }

  buildTypes {
    getByName("debug") {
      signingConfig = signingConfigs.getByName("debug")
    }

    getByName("release") {
      isMinifyEnabled = true
      signingConfig = signingConfigs.getByName("debug")
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"),
        "proguard-rules.pro")
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }
  kotlinOptions {
    jvmTarget = "1.8"
    // allWarningsAsErrors = true
  }
}

dependencies {
  implementation(project(":domain"))

  // Core
  implementation(libs.androidx.core.ktx)

  // Retrofit
  implementation(libs.retrofit)
  implementation(libs.retrofit.json.converter)

  // Hilt
  implementation(libs.hilt.android)
  kapt(libs.hilt.compiler)

  // Room
  implementation(libs.androidx.room.runtime)
  implementation(libs.androidx.room.ktx)
  kapt(libs.androidx.room.compiler)

  // Tests
  testImplementation(libs.androidx.room.testing)
  testImplementation(libs.junit)
  androidTestImplementation(libs.junit)
  androidTestImplementation(libs.androidx.test.ext.junit)
}