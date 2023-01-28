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
  id("com.google.dagger.hilt.android") version "2.44.2" apply false
}

// apply("${project.rootDir}/buildscripts/toml-updater-config.gradle") // TODO
