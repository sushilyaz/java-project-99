import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
	java
	jacoco
	id("org.springframework.boot") version "3.1.4"
	id("io.spring.dependency-management") version "1.1.3"
	id("com.github.ben-manes.versions") version "0.49.0"
	id("io.freefair.lombok") version "8.4"
}

group = "hexlet.code"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	runtimeOnly("com.h2database:h2:2.2.224")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-devtools")
	runtimeOnly("org.postgresql:postgresql:42.6.0")

	implementation("org.mapstruct:mapstruct:1.5.5.Final")
	annotationProcessor("org.mapstruct:mapstruct-processor:1.5.5.Final")
	implementation("org.openapitools:jackson-databind-nullable:0.2.6")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
	testImplementation("org.springframework.security:spring-security-test")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation(platform("org.junit:junit-bom:5.10.0"))
	testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
	testImplementation("net.javacrumbs.json-unit:json-unit-assertj:3.2.2")
	implementation("org.instancio:instancio-junit:3.6.0")
	implementation("net.datafaker:datafaker:2.0.2")


}
tasks.test {
	useJUnitPlatform()
	testLogging {
		exceptionFormat = TestExceptionFormat.FULL
		events = mutableSetOf(TestLogEvent.FAILED, TestLogEvent.PASSED, TestLogEvent.SKIPPED)
		showStandardStreams = true
	}
}

tasks.jacocoTestReport {
	reports {
		xml.required = true
	}
}
buildscript {
	repositories {
		mavenCentral()
	}
}
