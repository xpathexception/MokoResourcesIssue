pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

rootProject.name = "MokoResourcesIssue"

include(":frontend:app")
include(":frontend:app-web")
include(":frontend:modules:app")
include(":frontend:modules:features:sample")
