#Mediscreen
##Abernathy Clinic

This Application was developed for Abernathy Clinic to manage patients and prevent risks of diabetes.

###1. Installation

####1.1. General Requirements
 - Java 11
 - Gradle 7.1.1
 - Spring Boot 2.5.3
 - Thymeleaf
 - MySQL 8.X
 - MongoDB ?.
 - JUnit 5.?
 - Docker ?.
 - WebClient (WebFlux) ?.

####1.2. Previous setup
 - Download project or import it with git
 - Open project with your IDE

####1.3. Docker
- Verify the environment variables in docker-compose (//TODO if times : env. file ?) to make them suitable with your own environment.
- Launch the docker compose at the root of the project
````bash
docker compose up
````
Docker images and containers will be built during the launch if they are not already existing.

####1.4. DataBase
- MongoDB and MySQL databases are configured to generate needed schemas automatically.
- (//TODO) Default values are injected during the launch of Docker if the spring active profile is demo.
- Keywords used to generate the diabetes assessment are referenced in keywords.txt based in resources classpath of the service.

###2. Access to endpoints

####2.1. Ports

By default, microservices run on following ports :
- Patient Microservice : 8081
- Note Microservice : 8082
- Report Microservice : 8083
- Web Application : 8080

####2.2. Endpoints

All the endpoints run on http://localhost:8080.

For more specific details, please refer to the technical documentation at the root of the project.