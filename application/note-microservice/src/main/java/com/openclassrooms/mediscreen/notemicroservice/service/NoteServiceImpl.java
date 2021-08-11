package com.openclassrooms.mediscreen.notemicroservice.service;

import com.openclassrooms.mediscreen.notemicroservice.exception.ElementNotFoundException;
import com.openclassrooms.mediscreen.notemicroservice.model.Note;
import com.openclassrooms.mediscreen.notemicroservice.repository.NoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {
    /**
     * @see Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(NoteServiceImpl.class);
    /**
     * @see NoteRepository
     */
    private final NoteRepository noteRepository;

    /**
     * Public constructor.
     * @param noteRepository1 .
     */
    public NoteServiceImpl(final NoteRepository noteRepository1) {
        noteRepository = noteRepository1;
    }

    /**
     * @inheritDoc
     */
    @Override
    public Note findNoteById(BigInteger id) {
        LOGGER.info("Finding note with id : " + id);
        return noteRepository.findById(id).orElseThrow(() -> new ElementNotFoundException("No note find for id : " + id));
    }

    /**
     * @inheritDoc
     */
    @Override
    public List<Note> findAllNotesByPatientId(Integer patientId) {
        LOGGER.info("Finding all notes of patient with id : " + patientId);
        return noteRepository.findByPatientId(patientId);
    }

    /**
     * @inheritDoc
     */
    @Override
    public Note saveNote(Note note) {
        LOGGER.info("Saving note for patient with id : " + note.getPatientId());
        return noteRepository.save(note);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void deleteNoteById(BigInteger id) {
        LOGGER.info("Deleting note with id : " + id);
        noteRepository.deleteById(id);
    }
}
