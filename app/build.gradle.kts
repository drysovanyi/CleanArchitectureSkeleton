@file:Suppress("UnstableApiUsage")

val ktlint by configurations.creating

plugins {
  id("com.android.application")
  id("org.jetbrains.kotlin.android")
  id("com.google.dagger.hilt.android")
  kotlin("kapt")
}

android {
  compileSdk = libs.versions.compileSdk.get().toInt()

  defaultConfig {
    applicationId ="com.dmytrorysovanyi.cleanarchitectureskeleton"
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
    create("benchmark") {
      signingConfig = signingConfigs.getByName("debug")
      matchingFallbacks += listOf("release")
      isDebuggable = false
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
  buildFeatures {
    compose = true
  }
  composeOptions {
    kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
  }
  packagingOptions {
    // Multiple dependency bring these files in. Exclude them to enable
    // our test APK to build (has no effect on our AARs)
    resources {
      excludes += "/META-INF/AL2.0"
      excludes += "/META-INF/LGPL2.1"
    }
  }
}

// Added as suggested in Hilt documentation at https://dagger.dev/hilt/gradle-setup
kapt {
  correctErrorTypes = true
}


dependencies {
  implementation(project(":data"))

  // Base
  implementation(libs.androidx.appcompat)
  implementation(libs.androidx.activity.ktx)
  implementation(libs.androidx.core.ktx)
  implementation(libs.kotlin.stdlib)

  // Compose
  val composeBom = platform(libs.androidx.compose.bom)
  implementation(composeBom)
  implementation(libs.androidx.activity.compose)
  implementation(libs.androidx.compose.foundation)
  implementation(libs.androidx.compose.material)
  implementation(libs.androidx.compose.ui.tooling.preview)

  // Splash Screen
  implementation(libs.android.splash.screen)

  // ViewModel
  implementation(libs.androidx.lifecycle.viewmodel.ktx)
  implementation(libs.androidx.lifecycle.viewmodel.savedstate)
  implementation(libs.androidx.lifecycle.viewModelCompose)

  // Coroutines
  implementation(libs.kotlinx.coroutines.android)

  // Lifecycle
  implementation(libs.androidx.lifecycle.runtime)
  implementation(libs.androidx.lifecycle.runtime.compose)

  // Hilt
  implementation(libs.hilt.android)
  kapt(libs.hilt.compiler)

  // Tests
  androidTestImplementation(composeBom)
  androidTestImplementation(libs.junit)
  androidTestImplementation(libs.androidx.test.core)
  androidTestImplementation(libs.androidx.test.runner)
  androidTestImplementation(libs.androidx.test.espresso.core)
  androidTestImplementation(libs.androidx.test.rules)
  androidTestImplementation(libs.androidx.test.ext.junit)
  androidTestImplementation(libs.kotlinx.coroutines.test)
  androidTestImplementation(libs.androidx.compose.ui.test)
  androidTestImplementation(libs.androidx.compose.ui.test.junit4)

  androidTestImplementation(libs.hilt.android.testing)
  kaptAndroidTest(libs.hilt.android.testing)
  testImplementation (libs.hilt.android.testing)
  kaptTest(libs.hilt.android.testing)

  debugImplementation(libs.androidx.compose.ui.tooling)
  debugImplementation(libs.androidx.compose.ui.test.manifest)

  // Robolectric dependencies
  testImplementation(libs.androidx.compose.ui.test.junit4)
  testImplementation(libs.robolectric)

  // Ktlint
  ktlint(libs.ktlint) {
    attributes {
      attribute(Bundling.BUNDLING_ATTRIBUTE, objects.named(Bundling.EXTERNAL))
    }
  }
}

val outputDir = "${project.buildDir}/reports/ktlint/"
val inputFiles = project.fileTree(mapOf("dir" to "src", "include" to "**/*.kt"))

val ktlintCheck by tasks.creating(JavaExec::class) {
  inputs.files(inputFiles)
  outputs.dir(outputDir)

  description = "Check Kotlin code style."
  classpath = ktlint
  mainClass.set("com.pinterest.ktlint.Main")
  // see https://pinterest.github.io/ktlint/install/cli/#command-line-usage for more information
  args = listOf("src/**/*.kt")
}

val ktlintFormat by tasks.creating(JavaExec::class) {
  inputs.files(inputFiles)
  outputs.dir(outputDir)

  description = "Fix Kotlin code style deviations."
  classpath = ktlint
  mainClass.set("com.pinterest.ktlint.Main")
  // see https://pinterest.github.io/ktlint/install/cli/#command-line-usage for more information
  args = listOf("-F", "src/**/*.kt")
}