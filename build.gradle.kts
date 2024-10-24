plugins {
	java
	id("org.springframework.boot") version "3.3.4"
	id("io.spring.dependency-management") version "1.1.6"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")

	testImplementation(platform("org.junit:junit-bom:5.10.0"))
	testImplementation("org.junit.jupiter:junit-jupiter")
	compileOnly ("org.projectlombok:lombok:1.18.34")
	annotationProcessor ("org.projectlombok:lombok:1.18.34")

	testCompileOnly ("org.projectlombok:lombok:1.18.34")
	testAnnotationProcessor ("org.projectlombok:lombok:1.18.34")

	implementation("org.postgresql:postgresql:42.7.4")

	implementation (platform ("org.hibernate.orm:hibernate-platform:6.3.0.Final"))
	implementation ("org.hibernate.orm:hibernate-core")
	implementation ("jakarta.transaction:jakarta.transaction-api")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
