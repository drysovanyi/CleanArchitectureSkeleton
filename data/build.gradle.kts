@file:Suppress("UnstableApiUsage") // TODO

plugins {
  id ("com.android.library")
  id ("org.jetbrains.kotlin.android")
  id ("kotlin-kapt")
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
    allWarningsAsErrors = true
  }
}

dependencies {


  // Tests
  testImplementation(libs.junit)
  androidTestImplementation(libs.junit)
  androidTestImplementation(libs.androidx.test.ext.junit)


//  // Retrofit
//  implementation "com.squareup.retrofit2:retrofit:$retrofit_version"
//  implementation "com.squareup.retrofit2:converter-gson:$retrofit_version"
//
//  // Room
//  implementation "androidx.room:room-runtime:$room_version"
//  implementation "androidx.room:room-ktx:$room_version"
//  kapt "androidx.room:room-compiler:$room_version"
//
//  testImplementation "junit:junit:$junit_version"
//  androidTestImplementation "androidx.test.ext:junit:$androidx_junit_version"
}