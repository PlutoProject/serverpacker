plugins {
    id("java")
    id("application")
    id("com.github.johnrengelman.shadow") version "8.1.1"
    kotlin("jvm") version "1.9.0"
}

group = "link.plutomc.serverpacker"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.slf4j:slf4j-api:2.0.11")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation("ch.qos.logback:logback-classic:1.4.14")
    implementation("commons-cli:commons-cli:1.6.0")
    implementation("commons-io:commons-io:2.15.1")
    implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.12")
    implementation("com.google.code.gson:gson:2.10.1")
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

application {
    mainClass = "link.plutomc.serverpacker.ServerPackerKt"
}