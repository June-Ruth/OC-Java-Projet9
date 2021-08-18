package com.openclassrooms.mediscreen.patientmicroservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.mediscreen.patientmicroservice.exception.ElementNotFoundException;
import com.openclassrooms.mediscreen.patientmicroservice.model.Patient;
import com.openclassrooms.mediscreen.patientmicroservice.service.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(PatientController.class)
public class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatientService patientService;

    private Patient patient1;
    private Patient patient2;
    private List<Patient> allPatients;

    @BeforeEach
    void beforeEach() {
        patient1 = new Patient("family1", "given1", LocalDate.of(2001, 1, 1), 'F', "address1", "phone1");
        patient2 = new Patient("family2", "given2", LocalDate.of(2002, 2, 2), 'M', "address2", "phone2");
        allPatients = new ArrayList<>();
        allPatients.add(patient1);
        allPatients.add(patient2);
    }

    // FIND ALL PATIENTS TEST //

    @Test
    void getAllPatientsTest() throws Exception {
        when(patientService.findAllPatients()).thenReturn(allPatients);
        mockMvc.perform(get("/patients"))
                .andExpect(status().isOk());
    }

    //FIND PATIENT BY FULL NAME TEST //

    @Test
    void getAllPatientsByFullNameTest() throws Exception {
        when(patientService.findAllPatientsByFullName(anyString(), anyString())).thenReturn(allPatients);
        mockMvc.perform(get("/patients")
                .param("family", patient1.getFamily())
                .param("given", patient1.getGiven()))
                .andExpect(status().isOk());
    }


    // FIND PATIENT BY ID TEST //

    @Test
    void getExistingPatientByIdTest() throws Exception {
        when(patientService.findPatientById(anyInt())).thenReturn(patient1);
        mockMvc.perform(get("/patients/1"))
                .andExpect(status().isOk());
    }

    @Test
    void getNonExistentPatientByIdTest() throws Exception {
        when(patientService.findPatientById(anyInt())).thenThrow(ElementNotFoundException.class);
        mockMvc.perform(get("/patients/1"))
                .andExpect(status().isNotFound());
    }

    // SAVE PATIENT TEST //

    @Test
    void savePatientTest() throws Exception {
        when(patientService.savePatient(any(Patient.class))).thenReturn(patient1);
        mockMvc.perform(post("/patients")
                .content(new ObjectMapper().writeValueAsString(patient1))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    //UPDATE PATIENT //

    @Test
    void updateExistingPatientTest() throws Exception {
        when(patientService.savePatient(any(Patient.class))).thenReturn(patient1);
        mockMvc.perform(put("/patients/1")
                        .content(new ObjectMapper().writeValueAsString(patient1))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    // DELETE BID LIST TEST //

    @Test
    void deletePatientTest() throws Exception {
        doNothing().when(patientService).deletePatient(anyInt());
        mockMvc.perform((delete("/patients/1")))
                .andExpect(status().isOk());
    }
}
