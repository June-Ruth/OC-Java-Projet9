plugins {
    id("org.springframework.boot") version("2.5.3")
    id("io.spring.dependency-management") version("1.0.11.RELEASE")
    java
    jacoco
}

group = "com.openclassrooms"
version = "0.0.1-SNAPSHOT"


afterEvaluate {
    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(11))
        }
    }

    jacoco {
        toolVersion = "0.8.7"
    }

    tasks.test {
        useJUnitPlatform()
        finalizedBy(tasks.jacocoTestReport)
    //exclude("**/*IT.class")
    }

    tasks.jacocoTestReport {
        dependsOn(tasks.test)
        reports {
            xml.required.set(true)
            csv.required.set(false)
            html.outputLocation.set(layout.buildDirectory.dir("jacocoHtml").get().asFile)
        }
    }
}

    repositories {
        mavenCentral()
    }

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    //implementation("org.springframework.boot:spring-boot-starter-data-mongodb") => for NoSQL DB for notes
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    runtimeOnly("mysql:mysql-connector-java")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}
