plugins {
    kotlin("multiplatform") version "1.7.20-Beta"
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
        val commonMain by getting {
            dependencies {
                implementation("io.ktor:ktor-server-core:2.1.0")
                implementation("io.ktor:ktor-server-cio:2.1.0")
            }
        }
        val commonTest by getting
    }
}
