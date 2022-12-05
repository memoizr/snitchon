import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.21"
    kotlin("kapt") version "1.7.21"
    application
}

group = "me.snitchon"
version = "1.0-SNAPSHOT"

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = freeCompilerArgs + "-Xcontext-receivers"
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.7.22")
    testImplementation("com.google.code.gson:gson:2.8.5")
    testImplementation(kotlin("test"))
    testImplementation("com.dslplatform:dsl-json-java8:1.9.9")
    kapt("com.dslplatform:dsl-json-java8:1.9.9")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}