plugins {
    id("java")
    id("application")
    id("com.github.johnrengelman.shadow") version "8.1.1"
    kotlin("jvm") version "1.9.0"
}


group = "link.plutomc.serverpacker"

allprojects {

    apply {
        plugin("java")
        plugin("com.github.johnrengelman.shadow")
        plugin("org.jetbrains.kotlin.jvm")
    }

    this.version = "1.0.0-SNAPSHOT"

    repositories {
        mavenCentral()
    }

    dependencies {
        implementation(kotlin("stdlib"))
        implementation("org.slf4j:slf4j-api:2.0.11")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
        implementation("ch.qos.logback:logback-classic:1.4.14")
    }

    kotlin {
        jvmToolchain(8)
    }

    tasks.jar {
        manifest {
            attributes(
                "Implementation-Title" to "serverpacker", "Implementation-Version" to "${rootProject.version}"
            )
        }
    }
}

application {
    mainClass = "link.plutomc.serverpacker.EntryKt"
}