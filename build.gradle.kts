plugins {
    kotlin("multiplatform") version "1.9.10"
    kotlin("plugin.serialization") version "1.9.10"
}

group = "dev.bcmedeiros"
version = "1.0-SNAPSHOT"

kotlin {
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
        val ktorVersion = "2.3.5"
        val commonMain by getting {
            dependencies {
                implementation("io.ktor:ktor-server-core:$ktorVersion")
                implementation("io.ktor:ktor-server-cio:$ktorVersion")
                implementation("io.ktor:ktor-server-content-negotiation:$ktorVersion")
                implementation("io.ktor:ktor-server-sessions:$ktorVersion")
                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
            }
        }
        val commonTest by getting
    }
}
