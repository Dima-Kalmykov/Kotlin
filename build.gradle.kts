import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.30"
}

group = "me.dmitry"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.junit.jupiter:junit-jupiter:5.4.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.1.0")
    testImplementation(kotlin("test-junit"))
    testImplementation("io.mockk:mockk:1.10.6")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}