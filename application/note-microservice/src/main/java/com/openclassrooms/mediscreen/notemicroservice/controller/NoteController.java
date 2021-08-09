package com.openclassrooms.mediscreen.notemicroservice.controller;

import com.openclassrooms.mediscreen.notemicroservice.model.Note;
import com.openclassrooms.mediscreen.notemicroservice.service.NoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;
import java.util.List;

@RestController
public class NoteController {
    /**
     * @see Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(NoteController.class);
    /**
     * @see NoteService
     */
    private NoteService noteService;

    /**
     * Public constructor.
     * @param noteService1 .
     */
    public NoteController(final NoteService noteService1) {
        noteService = noteService1;
    }

    /**
     * Get all the historical notes of a patient.
     * @param patientId of patient concerned
     * @return list of all notes of the patient
     */
    @GetMapping("/notes")
    public List<Note> getAllNotesOfOnePatient(@RequestParam final Integer patientId) {
        LOGGER.info("Getting all notes for patient with id : " + patientId);
        return noteService.findAllNotesByPatientId(patientId);
    }

    /**
     * Update an existing note.
     * @param noteId concerned
     * @param updatedNote with updated modifications
     * @return updated note
     */
    @PutMapping("/notes/{noteId}")
    public Note updateNote(@PathVariable final BigInteger noteId,
                           @RequestBody @Valid final Note updatedNote) {
        LOGGER.info("Updating note with id : " + noteId);
        updatedNote.setId(noteService.findNoteById(noteId).getId());
        return noteService.saveNote(updatedNote);
    }

    /**
     * Delete a note by its id.
     * @param noteId concerned
     */
    @DeleteMapping("/notes/{noteId}")
    public void deleteNote(@PathVariable final BigInteger noteId) {
        LOGGER.info("Deleting note with id : " + noteId);
        noteService.deleteNoteById(noteId);
    }

    /**
     * Add a new note to a patient.
     * @param newNote with all information, including patient id
     * @return the new note
     */
    @PostMapping("/notes")
    public Note addNote(@RequestBody @Valid final Note newNote) {
        LOGGER.info("Adding new note to patient with id : " + newNote.getPatientId());
        return noteService.saveNote(newNote);
    }
}
