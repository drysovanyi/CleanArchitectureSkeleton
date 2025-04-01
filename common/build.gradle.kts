plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ksp)
}

dependencies {
    // Hilt. In non android module cannot be added default Hilt android dependency
    implementation(libs.hilt.core)
    ksp(libs.hilt.compiler)
    // Coroutines
    implementation(libs.kotlinx.coroutines.android)
}
