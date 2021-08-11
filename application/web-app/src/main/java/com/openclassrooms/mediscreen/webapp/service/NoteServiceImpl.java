package com.openclassrooms.mediscreen.webapp.service;

import com.openclassrooms.mediscreen.webapp.model.Note;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.math.BigInteger;
import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {

    /**
     * @see Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(NoteServiceImpl.class);

    private final WebClient webClientNote;

    public NoteServiceImpl(@Qualifier("getWebClientNote") final WebClient webClientNote1) {
        webClientNote = webClientNote1;
    }

    @Override
    public List<Note> getAllNoteOfOnePatient(final Integer patientId) {
        LOGGER.info("Getting all notes for patient with id : " + patientId);
        return webClientNote
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/notes")
                        .queryParam("patientId", patientId)
                        .build())
                .retrieve()
                .bodyToFlux(Note.class)
                .collectList()
                .block();
    }

    @Override
    public Note addNote(final Note note) {
        LOGGER.info("Adding new note");
        return webClientNote
                .post()
                .uri("/notes")
                .body(Mono.just(note), Note.class)
                .exchangeToMono(clientResponse -> {
                    if (clientResponse.statusCode().equals(HttpStatus.OK)) {
                        return clientResponse.bodyToMono(Note.class);
                    } else {
                        return clientResponse.createException().flatMap(Mono::error);
                    }
                })
                .block();
    }

    @Override
    public void deleteNote(final BigInteger id) {
        LOGGER.info("Deleting note with id : " + id);
        webClientNote
                .delete()
                .uri("/notes/" + id)
                .retrieve()
                .bodyToMono(Void.class).block();
    }
}
