package com.openclassrooms.mediscreen.webapp.service;

import com.openclassrooms.mediscreen.webapp.model.Note;
import com.openclassrooms.mediscreen.webapp.model.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {
    /**
     * @see Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ReportServiceImpl.class);
    /**
     * @see WebClient
     */
    private final WebClient webClientReport;
    /**
     * @see NoteService
     */
    private final NoteService noteService;

    /**
     * Public constructor.
     * @param webClientReport1 to call report microservice
     * @param noteService1 .
     */
    public ReportServiceImpl(@Qualifier("getWebClientReport") final WebClient webClientReport1,
                             final NoteService noteService1) {
        webClientReport = webClientReport1;
        noteService = noteService1;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String getDiabetesAssessmentByPatient(final Patient patient) {
        LOGGER.info("Get diabetes assessment for patient " + patient.getFamily());
        int age = LocalDate.now().compareTo(patient.getDateOfBirth());
        char sex = patient.getSex();

        List<Note> patientNotesComplete = noteService.getAllNoteOfOnePatient(patient.getId());
        List<String> patientNotesContentOnly = new ArrayList<>();
        for (Note note : patientNotesComplete) {
            patientNotesContentOnly.add(note.getContent());
        }

        String assessmentLevel = webClientReport
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/assessment")
                        .queryParam("patientSex", sex)
                        .queryParam("age", age)
                        .queryParam("allNotesContent", patientNotesContentOnly)
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return "Patient : " + patient.getFamily() + " " + patient.getGiven()
                + " (age : " + age
                + ") and diabetes assessment is : " + assessmentLevel;
    }
}
