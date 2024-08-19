plugins {
	kotlin("jvm") version "1.9.24"
	kotlin("plugin.spring") version "1.9.24"
	id("org.springframework.boot") version "3.3.2"
	id("io.spring.dependency-management") version "1.1.6"
	id("org.openapi.generator") version "7.7.0"
}

group = "no.webstep"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

springBoot {
	mainClass = "no.webstep.openapi_example.OpenApiExampleApplicationKt"
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("io.swagger.core.v3:swagger-annotations:2.2.22")
	implementation("io.swagger.core.v3:swagger-models:2.2.22")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.0.2")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.compileKotlin {
	dependsOn(":openApiGenerate")
}

sourceSets.main {
	kotlin.srcDirs("src/main/kotlin", "$rootDir/build/generated")
}

openApiGenerate {
	generatorName.set("kotlin-spring")
	inputSpec.set("$rootDir/src/main/resources/specification.yaml")
	outputDir.set("$rootDir/build/generated")
	apiPackage.set("no.webstep.openapi_example.api")
	invokerPackage.set("no.webstep.openapi_example.invoker")
	modelPackage.set("no.webstep.openapi_example.model")
	configOptions.put("dateLibrary", "java8")
	configOptions.put("delegatePattern", "true")
	configOptions.put("useSpringBoot3", "true")
	configOptions.put("sourceFolder", "")
}
