package com.openclassrooms.mediscreen.webapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    /**
     * Calling Patient-Microservice.
     * @return patient-microservice
     */
    @Bean
    public WebClient getWebClientPatient() {
        return WebClient.create("http://172.20.0.2:8081");
    }
}
