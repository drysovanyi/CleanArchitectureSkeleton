buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(libs.android.gradlePlugin)
        classpath(libs.kotlin.gradlePlugin)
        classpath("org.jlleitschuh.gradle:ktlint-gradle:10.3.0")
    }
}

subprojects {
  apply(plugin = "org.jlleitschuh.gradle.ktlint")
}

plugins {
    id("com.google.dagger.hilt.android") version "2.44.2" apply false
    id("androidx.benchmark") version "1.1.1" apply false
    id("com.android.test") version "7.4.0" apply false
    id("org.jetbrains.kotlin.android") version "1.8.0" apply false
    id("com.android.library") version "7.4.0" apply false
    id("org.jetbrains.kotlin.jvm") version "1.8.0" apply false
    id("org.jlleitschuh.gradle.ktlint") version "10.3.0" apply false
}
