package com.openclassrooms.mediscreen.notemicroservice.service;

import com.openclassrooms.mediscreen.notemicroservice.model.Note;

import java.math.BigInteger;
import java.util.List;

public interface NoteService {
    /**
     * Find a note by its id.
     * @param id of note concerned
     * @return note found - if doesn't exist, throw ElementNotFoundException
     */
    Note findNoteById(BigInteger id);

    /**
     * Find all notes of one patient.
     * @param patientId of patient concerned
     * @return list of all notes of the patient
     */
    List<Note> findAllNotesByPatientId(Integer patientId);

    /**
     * Save a new note or update an existing one (depending on its id)
     * @param note to save
     * @return saved note
     */
    Note saveNote(Note note);

    /**
     * Delete a note by its id.
     * @param id of note concerned
     */
    void deleteNoteById(BigInteger id);
}
