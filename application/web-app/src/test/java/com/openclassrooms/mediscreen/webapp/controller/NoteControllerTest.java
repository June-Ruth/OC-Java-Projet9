package com.openclassrooms.mediscreen.webapp.controller;

import com.openclassrooms.mediscreen.webapp.model.Note;
import com.openclassrooms.mediscreen.webapp.service.NoteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(NoteController.class)
public class NoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NoteService noteService;

    private Note note1;
    private Note note2;
    private List<Note> allNoteOfPatient1;

    @BeforeEach
    void beforeEach() {
        note1 = new Note(1, LocalDate.now().minusDays(2), null, "content1");
        note2 = new Note(1, LocalDate.now(), null, "content2");
        allNoteOfPatient1 = new ArrayList<>();
        allNoteOfPatient1.add(note1);
        allNoteOfPatient1.add(note2);
    }

    // ADD NOTE FORM TESTS //

    @Test
    void addNoteFormTest() throws Exception {
        mockMvc.perform(get("/patients/1/notes/add"))
                .andExpect(status().isOk())
                .andExpect(handler().methodName("addNoteForm"))
                .andExpect(view().name("patients/notes/add"));
    }

    // VALIDATE NEW NOTE TESTS //

    @Test
    void validateNewNoteTest() throws Exception {
        when(noteService.addNote(any(Note.class))).thenReturn(note1);
        mockMvc.perform(post("/patients/1/notes/validate")
                .contentType("text/html;charset=UTF-8")
                .sessionAttr("note", note1)
                .param("content", note1.getContent()))
                .andExpect(status().is3xxRedirection())
                .andExpect(handler().methodName("validateNewNote")).andExpect(model().hasNoErrors())
                .andExpect(view().name("redirect:/patients/profile/" + note1.getPatientId()));
    }

    // SHOW UPDATE NOTE FORM TESTS //

    //TODO

    // UPDATE NOTE TESTS //

    //TODO

    // DELETE NOTE TESTS //

    @Test
    void deleteNoteTest() throws Exception {
        doNothing().when(noteService).deleteNote(any(BigInteger.class));
        mockMvc.perform(get("/patients/1/notes/delete/1")
                        .sessionAttr("note", note1))
                .andExpect(status().is3xxRedirection())
                .andExpect(handler().methodName("deleteNote"))
                .andExpect(view().name("redirect:/patients/profile/" + note1.getPatientId()));
    }

}
