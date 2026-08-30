pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven { setUrl("https://jitpack.io") }
        maven("https://jogamp.org/deployment/maven")
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven { url = uri("https://jitpack.io") }
        maven("https://central.sonatype.com/repository/maven-snapshots/")
        maven("https://jogamp.org/deployment/maven")
        maven(url = "https://raw.githubusercontent.com/bravepipeproject/maven-repo/master/repository")
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

val coreDir = File(rootDir, "core")
val serviceDir = File(rootDir, "core/service")
val mediaDir = File(rootDir, "core/media")

rootProject.name = "SparkyMusic"
include(
    ":androidApp",
    ":composeApp",
    ":common",
    ":data",
    ":domain",
    ":ktorExt",
    ":kotlinYtmusicScraper",
    ":spotify",
    ":aiService",
    ":autoEqService",
    ":lyricsService",
    ":media3",
    ":media3-ui",
    ":cast",
    ":lastfm",
    ":kizzy",
)

// core modules
project(":common").projectDir = File(coreDir, "common")
project(":data").projectDir = File(coreDir, "data")
project(":domain").projectDir = File(coreDir, "domain")
project(":lastfm").projectDir = File(coreDir, "lastfm")

// top-level modules
project(":cast").projectDir = File(rootDir, "cast")

// service modules
project(":ktorExt").projectDir = File(serviceDir, "ktorExt")
project(":aiService").projectDir = File(serviceDir, "aiService")
project(":autoEqService").projectDir = File(serviceDir, "autoEqService")
project(":lyricsService").projectDir = File(serviceDir, "lyricsService")
project(":kotlinYtmusicScraper").projectDir = File(serviceDir, "kotlinYtmusicScraper")
project(":spotify").projectDir = File(serviceDir, "spotify")
project(":kizzy").projectDir = File(serviceDir, "kizzy")

// media modules
project(":media3").projectDir = File(mediaDir, "media3")
project(":media3-ui").projectDir = File(mediaDir, "media3-ui")

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
