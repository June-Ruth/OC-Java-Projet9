package com.openclassrooms.mediscreen.webapp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.reactive.function.client.WebClient;

@PropertySource("classpath:application.properties")
@Configuration
public class WebClientConfig {

    @Value("${patient_host:localhost}")
    private String patientHost;

    @Value("${note_host:localhost}")
    private String noteHost;

    /**
     * Calling Patient-Microservice.
     * @return patient-microservice
     */
    @Bean
    public WebClient getWebClientPatient() {
        return WebClient.create("http://" + patientHost + ":8081");
    }
    /**
     * Calling Note-Microservice.
     * @return note-microservice
     */
    @Bean
    public WebClient getWebClientNote() {
        return WebClient.create("http://" + noteHost + ":8082");
    }
}
