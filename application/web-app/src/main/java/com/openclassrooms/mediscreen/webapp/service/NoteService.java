package com.openclassrooms.mediscreen.webapp.service;

import com.openclassrooms.mediscreen.webapp.model.Note;

import java.math.BigInteger;
import java.util.List;

public interface NoteService {

    List<Note> getAllNoteOfOnePatient(Integer patientId);

    Note addNote(Note note);

    void deleteNote(BigInteger id);
}
