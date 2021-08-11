package com.openclassrooms.mediscreen.notemicroservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.mediscreen.notemicroservice.exception.ElementNotFoundException;
import com.openclassrooms.mediscreen.notemicroservice.model.Note;
import com.openclassrooms.mediscreen.notemicroservice.service.NoteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(NoteController.class)
public class NoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NoteService noteService;

    private Note note1;
    private Note note2;
    private Note invalidNote;
    private List<Note> allNotesOfOnePatient;

    @BeforeEach
    void beforeEach() {
        note1 = new Note(1, LocalDate.now(), null, "content1");
        note2 = new Note(1, LocalDate.now().minusDays(2), null, "content2");
        note1.setId(BigInteger.valueOf(1));
        invalidNote = new Note();
        invalidNote.setPatientId(2);
        allNotesOfOnePatient = new ArrayList<>();
        allNotesOfOnePatient.add(note1);
        allNotesOfOnePatient.add(note2);
    }

    // GET ALL NOTES OF ONE PATIENT TESTS //

    @Test
    void getAllNotesOfOnePatientTest() throws Exception {
        when(noteService.findAllNotesByPatientId(anyInt())).thenReturn(allNotesOfOnePatient);
        mockMvc.perform(get("/notes?patientId=1"))
                .andExpect(status().isOk());
    }

    // GET NOTE TESTS //

    @Test
    void getExistingNoteTest() throws Exception {
        when(noteService.findNoteById(any(BigInteger.class))).thenReturn(note1);
        mockMvc.perform(get("/notes/1"))
                .andExpect(status().isOk());
    }

    @Test
    void getNonExistentNoteTest() throws Exception {
        when(noteService.findNoteById(any(BigInteger.class))).thenThrow(ElementNotFoundException.class);
        mockMvc.perform(get("/notes/1"))
                .andExpect(status().isNotFound());
    }

    // UPDATE NOTE TESTS //

    @Test
    void updateExistingNoteWithValidArgumentsTest() throws Exception {
        when(noteService.findNoteById(any(BigInteger.class))).thenReturn(note1);
        when(noteService.saveNote(any(Note.class))).thenReturn(note1);
        mockMvc.perform(put("/notes/1")
                    .content(new ObjectMapper().writeValueAsString(note1))
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void updateExistingNoteWithInvalidArgumentsTest() throws Exception {
        mockMvc.perform(put("/notes/1")
                        .content(new ObjectMapper().writeValueAsString(invalidNote))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateNonExistentNoteWithValidArgumentsTest() throws Exception {
        when(noteService.findNoteById(any(BigInteger.class))).thenThrow(ElementNotFoundException.class);
        when(noteService.saveNote(any(Note.class))).thenReturn(note1);
        mockMvc.perform(put("/notes/1")
                        .content(new ObjectMapper().writeValueAsString(note1))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    // DELETE NOTE TESTS //
    @Test
    void deleteExistingNoteTest() throws Exception {
        doNothing().when(noteService).deleteNoteById(any(BigInteger.class));
        mockMvc.perform(delete("/notes/1"))
                .andExpect(status().isOk());
    }

    // ADD NOTE TESTS //

    @Test
    void addNoteWithValidArgumentsTest() throws Exception {
        when(noteService.saveNote(any(Note.class))).thenReturn(note1);
        mockMvc.perform(post("/notes")
                        .content(new ObjectMapper().writeValueAsString(note1))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void addNoteWithInvalidArgumentsTest() throws Exception {
        mockMvc.perform(post("/notes")
                        .content(new ObjectMapper().writeValueAsString(invalidNote))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }


}
