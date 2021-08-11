package com.openclassrooms.mediscreen.webapp.service;

import com.openclassrooms.mediscreen.webapp.model.Note;

import java.math.BigInteger;
import java.util.List;

public interface NoteService {
    /**
     * Get all note of one specified patient.
     * @param patientId .
     * @return List of all notes for the patient
     */
    List<Note> getAllNoteOfOnePatient(Integer patientId);

    /**
     * Add one note to a patient.
     * @param note fulfilled
     * @return saved note
     */
    Note addNote(Note note);

    /**
     * Delete a specified note.
     * @param id of the note
     */
    void deleteNote(BigInteger id);

    /**
     * Find a note by its id.
     * @param id of the note
     * @return Note
     */
    Note findNoteById(BigInteger id);

    /**
     * Update an existing note.
     * @param note updated
     * @return updated note
     */
    Note updateNote(Note note);
}
