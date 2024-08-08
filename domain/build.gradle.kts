plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
    kotlin("kapt")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

// Added as suggested in Hilt documentation at https://dagger.dev/hilt/gradle-setup
kapt {
    correctErrorTypes = true
}

dependencies {
    // Hilt. In non android module cannot be added default Hilt android dependency
    implementation(libs.hilt.core)
    kapt(libs.hilt.compiler)

    // Coroutines
    implementation(libs.kotlinx.coroutines.android)
}
