plugins {
	java
	id("org.springframework.boot") version "3.1.0"
	id("io.spring.dependency-management") version "1.1.0"
}

group = "com.example"
version = "0.0.2-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.telegram:telegrambots:6.5.0")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-data-rest")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	runtimeOnly("org.postgresql:postgresql")
	testImplementation("org.springframework.boot:spring-boot-starter-test")

	compileOnly ("org.projectlombok:lombok:1.18.28")
	annotationProcessor ("org.projectlombok:lombok:1.18.28")

	testCompileOnly ("org.projectlombok:lombok:1.18.28")
	testAnnotationProcessor ("org.projectlombok:lombok:1.18.28")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
