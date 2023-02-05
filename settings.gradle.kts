rootProject.name = "CleanArchitectureSkeleton"

include(":app")
include(":data")
include(":domain")
include(":common")
include(":macro-benchmark")
include(":micro-benchmark")

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
