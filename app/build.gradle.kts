plugins {
  id ("com.android.application")
  id ("org.jetbrains.kotlin.android")
  id ("dagger.hilt.android.plugin")
  id ("kotlin-kapt")
}

android {
  compileSdk = libs.versions.compileSdk.get().toInt()

  defaultConfig {
    applicationId ="com.example.myarchitectureproject"
    minSdk = libs.versions.minSdk.get().toInt()
    targetSdk = libs.versions.targetSdk.get().toInt()
    versionCode = 1
    versionName = "1.0"
    vectorDrawables.useSupportLibrary = true
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
  }
  buildFeatures {
    compose = true
  }
  composeOptions {
    kotlinCompilerExtensionVersion = "1.3.2"
  }
  packagingOptions {
    // Multiple dependency bring these files in. Exclude them to enable
    // our test APK to build (has no effect on our AARs)
    excludes += "/META-INF/AL2.0"
    excludes += "/META-INF/LGPL2.1"
  }
}

// Added as suggested in Hilt documentation at https://dagger.dev/hilt/gradle-setup
kapt {
  correctErrorTypes = true
}

hilt {
  enableAggregatingTask = true
}

dependencies {

  // Hilt
  implementation(libs.hilt.android)
  kapt(libs.hilt.compiler)

  //Compose
  implementation(libs.androidx.compose.ui)

  // implementation "androidx.core:core-ktx:1.7.0"
  // implementation "androidx.lifecycle:lifecycle-runtime-ktx:2.3.1"
  // implementation "androidx.activity:activity-compose:1.3.1"
  // implementation "androidx.compose.ui:ui:$compose_ui_version"
  // implementation "androidx.compose.ui:ui-tooling-preview:$compose_ui_version"
  // implementation "androidx.compose.material:material:1.3.1"
  // testImplementation "junit:junit:4.13.2"
  // androidTestImplementation "androidx.test.ext:junit:1.1.3"
  // androidTestImplementation "androidx.test.espresso:espresso-core:3.4.0"
  // androidTestImplementation "androidx.compose.ui:ui-test-junit4:$compose_ui_version"
  // debugImplementation "androidx.compose.ui:ui-tooling:$compose_ui_version"
  // debugImplementation "androidx.compose.ui:ui-test-manifest:$compose_ui_version"
}