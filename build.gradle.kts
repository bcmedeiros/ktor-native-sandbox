plugins {
    kotlin("multiplatform") version "1.7.20-RC"
    kotlin("plugin.serialization") version "1.7.20-RC"
}

group = "dev.bcmedeiros"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

kotlin {
    val hostOs = System.getProperty("os.name")
    val isMingwX64 = hostOs.startsWith("Windows")
    val nativeTarget = when {
        hostOs == "Mac OS X" -> macosX64("native")
        hostOs == "Linux" -> linuxX64("native")
        isMingwX64 -> mingwX64("native")
        else -> throw GradleException("Host OS is not supported in Kotlin/Native.")
    }

    val platforms = listOf(
        macosArm64 {

        },
        macosX64 {

        },
        linuxX64 {

        },
    )

    platforms.forEach { p ->
        p.binaries {
            executable {
                entryPoint = "main"
            }
        }
    }

    sourceSets {
        val ktorVersion = "2.1.0"
        val commonMain by getting {
            dependencies {
                implementation("io.ktor:ktor-server-core:$ktorVersion")
                implementation("io.ktor:ktor-server-cio:$ktorVersion")
                implementation("io.ktor:ktor-server-content-negotiation:$ktorVersion")
                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
            }
        }
        val commonTest by getting
    }
}
