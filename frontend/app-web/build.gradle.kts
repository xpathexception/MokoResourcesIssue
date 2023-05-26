import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    alias(libs.plugins.kotlin.multiplatform)
}

kotlin {
    js(IR) {
        binaries.executable()
        browser {
            commonWebpackConfig {
                cssSupport {
                    enabled.set(true)
                }
            }
            runTask {
                outputFileName = "browser.js"
                devServer = KotlinWebpackConfig.DevServer(
                    static = mutableListOf("$buildDir/processedResources/Js/main"),
                    port = 8081
                )
            }
        }
    }

    sourceSets {
        val jsMain by getting {
            dependencies {
                implementation(project(":frontend:modules:app"))
                implementation(project(":frontend:modules:features:sample"))

                implementation(libs.moko.resources)
            }
        }
        val jsTest by getting
    }
}
