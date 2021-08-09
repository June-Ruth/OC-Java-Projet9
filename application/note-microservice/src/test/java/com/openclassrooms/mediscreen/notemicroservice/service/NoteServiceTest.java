package com.openclassrooms.mediscreen.notemicroservice.service;

import com.openclassrooms.mediscreen.notemicroservice.exception.ElementNotFoundException;
import com.openclassrooms.mediscreen.notemicroservice.model.Note;
import com.openclassrooms.mediscreen.notemicroservice.repository.NoteRepository;
import com.openclassrooms.mediscreen.notemicroservice.repository.NoteServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class NoteServiceTest {

    @Mock
    private static NoteRepository noteRepository;

    private static NoteService noteService;

    private Note note1;
    private Note note2;
    private List<Note> allNotesOfOnePatient;

    @BeforeEach
    void beforeEach() {
        noteService = new NoteServiceImpl(noteRepository);
        note1 = new Note(1, LocalDate.now(), null, "content1");
        note2 = new Note(1, LocalDate.now().minusDays(2), null, "content2");
        note1.setId(BigInteger.ONE);
        allNotesOfOnePatient = new ArrayList<>();
        allNotesOfOnePatient.add(note1);
        allNotesOfOnePatient.add(note2);
    }

    // FIND NOTE BY ID TEST //

    @Test
    void findExistingNoteByIdTest() {
        when(noteRepository.findById(any(BigInteger.class))).thenReturn(Optional.of(note1));
        noteService.findNoteById(BigInteger.valueOf(1));
        verify(noteRepository, times(1)).findById(BigInteger.ONE);
    }

    @Test
    void findNonExistentNoteByIdTest() {
        when(noteRepository.findById(any(BigInteger.class))).thenReturn(Optional.empty());
        assertThrows(ElementNotFoundException.class, () -> noteService.findNoteById(BigInteger.ONE));
    }

    // FIND ALL NOTES BY PATIENT ID TEST //

    @Test
    void findAllNotesByPatientId() {
        when(noteRepository.findByPatientId(anyInt())).thenReturn(allNotesOfOnePatient);
        noteService.findAllNotesByPatientId(1);
        verify(noteRepository, times(1)).findByPatientId(1);
    }

    // SAVE NOTE TEST //

    @Test
    void saveNoteTest() {
        when(noteRepository.save(any(Note.class))).thenReturn(note1);
        noteService.saveNote(note1);
        verify(noteRepository, times(1)).save(note1);
    }

    // DELETE NOTE BY ID TEST //

    @Test
    void deleteNoteTest() {
        doNothing().when(noteRepository).deleteById(any(BigInteger.class));
        noteService.deleteNoteById(BigInteger.ONE);
        verify(noteRepository, times(1)).deleteById(BigInteger.ONE);
    }
}
