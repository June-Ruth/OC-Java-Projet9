package com.openclassrooms.mediscreen.webapp.service;

import com.openclassrooms.mediscreen.webapp.exception.ElementNotFoundException;
import com.openclassrooms.mediscreen.webapp.model.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    /**
     * @see Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(PatientServiceImpl.class);

    /**
     * @see WebClient
     */
    private final WebClient webClientPatient;

    /**
     * Public constructor.
     * @param webClientPatient1 to call patient microservice
     */
    public PatientServiceImpl(@Qualifier("getWebClientPatient") final WebClient webClientPatient1) {
        webClientPatient = webClientPatient1;
    }

    /**
     * @inheritDoc
     */
    @Override
    public List<Patient> findAllPatients() {
        LOGGER.info("Finding all patients");
        return webClientPatient
                .get()
                .uri("/patients")
                .retrieve()
                .bodyToFlux(Patient.class)
                .collectList()
                .block();
    }

    /**
     * @inheritDoc
     */
    @Override
    public List<Patient> findAllPatientsByFullName(final String family, final String given) {
        LOGGER.info("Finding all patients with family : " + family + " and given : " + given);
        return webClientPatient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/patients")
                        .queryParam("family", family)
                        .queryParam("given", given)
                        .build())
                .retrieve()
                .bodyToFlux(Patient.class)
                .collectList()
                .block();
    }

    /**
     * @inheritDoc
     */
    @Override
    public Patient findPatientById(final Integer id) {
        LOGGER.info("Finding patient with id : " + id);
        return webClientPatient
                .get()
                .uri("/patients/" + id)
                .exchangeToMono(clientResponse -> {
                    if (clientResponse.statusCode().equals(HttpStatus.OK)) {
                        return clientResponse.bodyToMono(Patient.class);
                    } else if (clientResponse.statusCode().equals(HttpStatus.NOT_FOUND)) {
                        throw new ElementNotFoundException("Patient with id " + id + " is not found.");
                    } else {
                        return clientResponse.createException().flatMap(Mono::error);
                    }
                })
                .block();
    }

    /**
     * @inheritDoc
     */
    @Override
    public Patient savePatient(final Patient patient) {
        LOGGER.info("Saving patient with family name " + patient.getFamily());
        return webClientPatient
                .post()
                .uri("/patients")
                .body(Mono.just(patient), Patient.class)
                .exchangeToMono(clientResponse -> {
                    if (clientResponse.statusCode().equals(HttpStatus.OK)) {
                        return clientResponse.bodyToMono(Patient.class);
                    } else {
                        return clientResponse.createException().flatMap(Mono::error);
                    }
                })
                .block();
    }

    /**
     * @inheritDoc
     */
    @Override
    public Patient updatePatient(final Patient updatedPatient) {
        LOGGER.info("Updating patient with id : " + updatedPatient.getId());
        return webClientPatient
                .put()
                .uri("/patients/" + updatedPatient.getId())
                .body(Mono.just(updatedPatient), Patient.class)
                .exchangeToMono(clientResponse -> {
                    if (clientResponse.statusCode().equals(HttpStatus.OK)) {
                        return clientResponse.bodyToMono(Patient.class);
                    } else {
                        return clientResponse.createException().flatMap(Mono::error);
                    }
                })
                .block();
    }

    /**
     * @inheritDoc
     */
    @Override
    public void deletePatient(final Integer id) {
        LOGGER.info("Deleting patient with id : " + id);
        webClientPatient
                .delete()
                .uri("/patients/" + id)
                .retrieve()
                .bodyToMono(Void.class).block();
    }
}
