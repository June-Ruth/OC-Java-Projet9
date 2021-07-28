plugins {
    java
    jacoco
}

group = "com.openclassrooms"
version = "0.0.1-SNAPSHOT"

subprojects {
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
}



