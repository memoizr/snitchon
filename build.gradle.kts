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
    maven("https://jitpack.io")
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.7.22")

    testImplementation(project(":core"))
    testImplementation(project(":gsonparser"))
    testImplementation(project(":tests"))
    testImplementation(project(":spring"))
    testImplementation(project(":spark"))
    testImplementation(project(":vertx"))

    testImplementation(kotlin("test"))
    testImplementation("com.dslplatform:dsl-json-java8:1.9.9")
    testImplementation("com.github.memoizr:assertk-core:-SNAPSHOT")
//    testImplementation(kotlin("test-junit"))

    api("ch.qos.logback:logback-classic:1.4.5")
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