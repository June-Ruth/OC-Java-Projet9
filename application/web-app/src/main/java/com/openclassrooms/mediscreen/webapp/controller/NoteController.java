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
     * @param patientId concerned by the note
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

    /**
     * Show form to update a patient.
     * @param patientId of patient concerned
     * @param id of the note concerned
     * @param model .
     * @return the form to update fulfill with actual patient information
     */
    @GetMapping("/patients/{patientId}/notes/update/{id}")
    public String showUpdateNoteForm(@PathVariable final Integer patientId,
                                     @PathVariable final BigInteger id,
                                     final Model model) {
        LOGGER.info("Show form to update note");
        model.addAttribute("note", noteService.findNoteById(id));
        return "patients/notes/update";
    }

    /**
     * Validate update form of a note.
     * @param patientId of patient concerned
     * @param id of updated note
     * @param updatedNote with new information
     * @param result .
     * @param model .
     * @return the patient information page
     */
    @PostMapping("/patients/{patientId}/notes/update/{id}")
    public String updateNote(@PathVariable final Integer patientId,
                                @PathVariable final BigInteger id,
                                final Note updatedNote,
                                final BindingResult result,
                                final Model model) {
        LOGGER.info("Updating patient with id : " + id);
        if (!result.hasErrors()) {
            Note note = noteService.findNoteById(id);
            note.setContent(updatedNote.getContent());
            note.setCreationDate(updatedNote.getCreationDate());
            note.setLastModificationDate(LocalDate.now());
            noteService.updateNote(note);
            return "redirect:/patients/profile/" + patientId;
        }
        updatedNote.setId(id);
        return "patients/notes/update";
    }

    /**
     * Delete a note.
     * @param patientId concerned
     * @param id of note to delete
     * @param model .
     * @return note list
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
