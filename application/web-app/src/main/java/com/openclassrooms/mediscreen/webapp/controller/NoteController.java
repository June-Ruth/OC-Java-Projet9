package com.openclassrooms.mediscreen.webapp.controller;

import com.openclassrooms.mediscreen.webapp.model.Note;
import com.openclassrooms.mediscreen.webapp.service.NoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigInteger;
import java.time.LocalDate;

@Controller
public class NoteController {
    /**
     * @see Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(PatientController.class);

    /**
     * @see NoteService
     */
    private final NoteService noteService;

    /**
     * Public constructor.
     * @param noteService1 .
     */
    public NoteController(final NoteService noteService1) {
        noteService = noteService1;
    }

    /**
     * Show the form to complete to add a new note.
     * @param patientId .
     * @param note .
     * @return form to add note
     */
    @GetMapping("/patients/{patientId}/notes/add")
    public String addNoteForm(@PathVariable final Integer patientId,
                              final Note note) {
        LOGGER.info("Show form to add note");
        note.setPatientId(patientId);
        return "patients/notes/add";
    }

    /**
     * Validate the form to add a new note.
     * @param patientId .
     * @param note with its information
     * @param result .
     * @param model .
     * @return patient profile page
     */
    @PostMapping("/patients/{patientId}/notes/validate")
    public String validateNewNote(@PathVariable final Integer patientId,
                                  final Note note,
                                  final BindingResult result,
                                  final Model model) {
        LOGGER.info("Saving new note");
        note.setCreationDate(LocalDate.now());
        if (!result.hasErrors()) {
            noteService.addNote(note);
            return "redirect:/patients/profile/" + patientId;
        }
        return "patients/notes/add";
    }

/*    *//**
     * Show form to update a patient.
     * @param id of patient searched
     * @param model .
     * @return the form to update fullfil with actual patient information
     *//*
    @GetMapping("/patients/update/{id}")
    public String showUpdateNoteForm(@PathVariable final Integer id,
                                 final Model model) {
        LOGGER.info("Show form for update");
        model.addAttribute("patient", patientService.findPatientById(id));
        return "patients/update";
    }

    *//**
     * Validate update form of a patient.
     * @param id of updated patient
     * @param updatedPatient with nw information
     * @param result .
     * @param model .
     * @return the patient information page
     *//*
    @PostMapping("/patients/update/{id}")
    public String updatePatient(@PathVariable final Integer id,
                                @Valid final Patient updatedPatient,
                                final BindingResult result,
                                final Model model) {
        LOGGER.info("Updating patient with id : " + id);
        if (!result.hasErrors()) {
            Patient patient = patientService.findPatientById(id);
            patient.setFamily(updatedPatient.getFamily());
            patient.setGiven(updatedPatient.getGiven());
            patient.setDateOfBirth(updatedPatient.getDateOfBirth());
            patient.setSex(updatedPatient.getSex());
            patient.setAddress(updatedPatient.getAddress());
            patient.setPhone(updatedPatient.getPhone());
            patientService.updatePatient(patient);
            return "redirect:/patients/profile/{patientId}";
        }
        updatedPatient.setId(id);
        return "patients/update";
    }*/

    /**
     * Delete a patient.
     * @param id of patient to delete
     * @param model .
     * @return patient list
     */
    @GetMapping("/patients/{patientId}/notes/delete/{id}")
    public String deleteNote(@PathVariable final Integer patientId,
                             @PathVariable final BigInteger id,
                             final Model model) {
        LOGGER.info("Deleting note with id : " + id);
        noteService.deleteNote(id);
        return "redirect:/patients/profile/" + patientId;
    }


}
