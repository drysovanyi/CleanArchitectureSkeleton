rootProject.name = "CleanArchitectureSkeleton"

include (":app")
include (":data")
include(":macro-benchmark")
include(":micro-benchmark")
include(":domain")

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
