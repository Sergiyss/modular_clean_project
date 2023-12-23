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
//важно, придерживаться такого стиля!!!
rootProject.name = "modularcleandesign"

//https://proandroiddev.com/using-type-safe-project-dependencies-on-gradle-493ab7337aa
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(":app")
include(":core")
include(":core:database")
include(":core:network")
include(":core:ui")
