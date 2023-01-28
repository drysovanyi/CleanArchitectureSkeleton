
buildscript {
  repositories {
    google()
    mavenCentral()

    if (!libs.versions.compose.snapshot.get().endsWith("SNAPSHOT")) {
      maven { url = uri("https://androidx.dev/snapshots/builds/${libs.versions.compose.snapshot.get()}/artifacts/repository/") }
    }
  }
  dependencies {
    classpath(libs.android.gradlePlugin)
    classpath(libs.kotlin.gradlePlugin)
  }
}

plugins {
//   id ("com.android.application") version "7.4.0" apply false
//   id ("com.android.library") version "7.4.0" apply false
//   id ("org.jetbrains.kotlin.android") version "1.7.20" apply false
//   id ("org.jetbrains.kotlin.jvm") version "1.7.20" apply false
  id ("com.google.dagger.hilt.android") version "2.44.2" apply false
}