plugins {
    id("org.springframework.boot") version(DependenciesVersions.springBoot)
    id("io.spring.dependency-management") version(DependenciesVersions.springDependencyManagement)
    java
    jacoco
}

version = "0.0.1-SNAPSHOT"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-webflux")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}
