plugins {
    id("org.springframework.boot") version(DependenciesVersions.springBoot)
    id("io.spring.dependency-management") version(DependenciesVersions.springDependencyManagement)
    java
    jacoco
}

version = "0.0.1-SNAPSHOT"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")

    runtimeOnly("mysql:mysql-connector-java")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}
