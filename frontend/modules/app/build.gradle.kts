plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.cocoapods)
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.moko.resources)
}

kotlin {
    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    js(IR) {
        binaries.executable()
        browser {
            commonWebpackConfig {
                cssSupport {
                    enabled.set(true)
                }
            }
        }
    }
    cocoapods {
        project.version = "1.0"
        summary = "MyApp KMM"
        homepage = "https://google.com"
        ios.deploymentTarget = "10"
        podfile = project.file("../../app-ios/Podfile")

        name = "app"
        framework {
            baseName = "app"

            export(project(":frontend:modules:features:sample"))
            export(libs.moko.resources)
        }
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":frontend:modules:features:sample"))
                implementation(libs.moko.resources)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.moko.resources)
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)

            dependencies {
                api(project(":frontend:modules:features:sample"))
                api(libs.moko.resources)
            }

            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
        val jsMain by getting {
            dependencies {
                implementation(libs.moko.resources)
            }
        }
        val jsTest by getting
    }
}

android {
    namespace = "com.example.myapp"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
    buildFeatures {
        buildConfig = true
    }
}

multiplatformResources {
    multiplatformResourcesPackage = "com.example.myapp"
    multiplatformResourcesClassName = "myappR"
}
