package com.openclassrooms.mediscreen.webapp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.reactive.function.client.WebClient;

@PropertySource("classpath:application.properties")
@Configuration
public class WebClientConfig {
    /**
     * Host of patient microservice.
     */
    @Value("${patient_host:localhost}")
    private String patientHost;
    /**
     * Host of note microservice.
     */
    @Value("${note_host:localhost}")
    private String noteHost;
    /**
     * Host of report microservice.
     */
    @Value("${REPORT_HOST:localhost}")
    private String reportHost;

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

    /**
     * Calling report-microservice.
     * @return report-microservice
     */
    @Bean
    public WebClient getWebClientReport() {
        return WebClient.create("http://" + reportHost + ":8083");
    }
}
