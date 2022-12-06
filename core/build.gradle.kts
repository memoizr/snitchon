import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.21"
    kotlin("kapt") version "1.7.21"
}

group = "me.snitchon"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = freeCompilerArgs + "-Xcontext-receivers"
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.7.22")
    testImplementation("com.google.code.gson:gson:2.8.5")
    testImplementation(kotlin("test"))
    testImplementation("com.dslplatform:dsl-json-java8:1.9.9")
    kapt("com.dslplatform:dsl-json-java8:1.9.9")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}