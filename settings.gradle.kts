pluginManagement {
    includeBuild("buildlogic")
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "FeatureDelivery"
include(":app")
include(":core:data")
include(":core:domain")
include(":feature:onboarding")
include(":feature:devicea")
include(":feature:deviceb")
include(":core:designsystem")
