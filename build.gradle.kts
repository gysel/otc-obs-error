plugins {
    kotlin("jvm") version "2.3.10"
}

group = "com.unblu.tests"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("software.amazon.awssdk:s3:2.42.34")
    runtimeOnly("org.slf4j:slf4j-simple:2.0.17")
}

kotlin {
    jvmToolchain(21)
}

tasks.test {
    useJUnitPlatform()
}

tasks.register<JavaExec>("run") {
    group = "application"
    description = "Run Main.kt"
    classpath = sourceSets["main"].runtimeClasspath
    mainClass = "com.unblu.tests.MainKt"
}