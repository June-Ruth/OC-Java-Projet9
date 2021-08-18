package com.openclassrooms.mediscreen.webapp.controller;

import com.openclassrooms.mediscreen.webapp.exception.ElementNotFoundException;
import com.openclassrooms.mediscreen.webapp.model.Patient;
import com.openclassrooms.mediscreen.webapp.service.NoteService;
import com.openclassrooms.mediscreen.webapp.service.PatientService;
import com.openclassrooms.mediscreen.webapp.service.ReportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PatientController.class)
public class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatientService patientService;

    @MockBean
    private NoteService noteService;

    @MockBean
    private ReportService reportService;

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

    // GET ALL PATIENTS TESTS //

    @Test
    void getAllPatientsTest() throws Exception {
        when(patientService.findAllPatients()).thenReturn(allPatients);
        mockMvc.perform(get("/patients/list"))
                .andExpect(status().isOk())
                .andExpect(handler().methodName("getAllPatients"))
                .andExpect(model().attribute("patientList", allPatients))
                .andExpect(view().name("patients/list"));
    }

    // GET PATIENT TESTS //

    @Test
    void getExistingPatientTest() throws Exception {
        when(patientService.findPatientById(anyInt())).thenReturn(patient1);
        when(noteService.getAllNoteOfOnePatient(anyInt())).thenReturn(new ArrayList<>());
        when(reportService.getDiabetesAssessmentByPatient(any(Patient.class))).thenReturn("assessment");
        mockMvc.perform(get("/patients/profile/1"))
                .andExpect(status().isOk())
                .andExpect(handler().methodName("getPatient"))
                .andExpect(model().attribute("patient", patient1))
                .andExpect(view().name("patients/profile"));
    }

    @Test
    void getNonExistentPatientTest() throws Exception {
        when(patientService.findPatientById(anyInt())).thenThrow(ElementNotFoundException.class);
        mockMvc.perform(get("/patients/profile/1"))
                .andExpect(status().isNotFound())
                .andExpect(handler().methodName("getPatient"));
    }

    // ADD PATIENT FORM TESTS //

    @Test
    void addPatientFormTest() throws Exception {
        mockMvc.perform(get("/patients/add"))
                .andExpect(status().isOk())
                .andExpect(handler().methodName("addPatientForm"))
                .andExpect(view().name("patients/add"));

    }

    // VALIDATE NEW PATIENT TESTS //

    @Test
    void validateNewPatientWithValidArgumentsTest() throws Exception {
        when(patientService.savePatient(any(Patient.class))).thenReturn(patient1);
        mockMvc.perform(post("/patients/validate")
                        .contentType("text/html;charset=UTF-8")
                        .sessionAttr("patient", patient1)
                        .param("family", patient1.getFamily())
                        .param("given", patient1.getGiven())
                        .param("dateOfBirth", patient1.getDateOfBirth().toString())
                        .param("sex", "F")
                        .param("address", patient1.getAddress())
                        .param("phone", patient1.getPhone()))
                .andExpect(status().is3xxRedirection())
                .andExpect(handler().methodName("validateNewPatient"))
                .andExpect(model().hasNoErrors())
                .andExpect(view().name("redirect:/patients/list"));
    }

    @Test
    void validateNewPatientWithInvalidArgumentsTest() throws Exception {
        mockMvc.perform(post("/patients/validate")
                        .contentType("text/html;charset=UTF-8")
                        .sessionAttr("patient", patient1)
                        .param("family", "")
                        .param("given", patient1.getGiven())
                        .param("dateOfBirth", patient1.getDateOfBirth().toString())
                        .param("sex", "F")
                        .param("address", patient1.getAddress())
                        .param("phone", patient1.getPhone()))
                .andExpect(status().isOk())
                .andExpect(handler().methodName("validateNewPatient"))
                .andExpect(model().hasErrors())
                .andExpect(view().name("patients/add"));
    }

    // SHOW UPDATE FORM TESTS //

    @Test
    void showUpdateFormForExistingPatientTest() throws Exception {
        when(patientService.findPatientById(anyInt())).thenReturn(patient1);
        mockMvc.perform(get("/patients/update/{id}", 1)
                        .sessionAttr("patient", patient1)
                        .param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(handler().methodName("showUpdateForm"))
                .andExpect(request().attribute("patient", patient1))
                .andExpect(view().name("patients/update"));
    }

    @Test
    void showUpdateFormForNonExistentPatientTest() throws Exception {
        when(patientService.findPatientById(anyInt())).thenThrow(ElementNotFoundException.class);
        mockMvc.perform(get("/patients/update/{id}", 1)
                        .sessionAttr("patient", patient1)
                        .param("id", "1"))
                .andExpect(status().isNotFound())
                .andExpect(handler().methodName("showUpdateForm"));
    }

    // UPDATE PATIENT TESTS //

    @Test
    void updateExistingPatientAndValidArgumentsTest() throws Exception {
        when(patientService.findPatientById(anyInt())).thenReturn(patient1);
        when(patientService.savePatient(any(Patient.class))).thenReturn(patient1);
        mockMvc.perform(post("/patients/update/1")
                        .contentType("text/html;charset=UTF-8")
                        .sessionAttr("patient", patient1)
                        .param("family", patient1.getFamily())
                        .param("given", patient1.getGiven())
                        .param("dateOfBirth", patient1.getDateOfBirth().toString())
                        .param("sex", "F")
                        .param("address", patient1.getAddress())
                        .param("phone", patient1.getPhone()))
                .andExpect(status().is3xxRedirection())
                .andExpect(handler().methodName("updatePatient"))
                .andExpect(model().hasNoErrors())
                .andExpect(view().name("redirect:/patients/profile/{id}"));
    }

    @Test
    void updateExistingPatientAndInvalidArgumentsTest() throws Exception {
        mockMvc.perform(post("/patients/update/1")
                        .contentType("text/html;charset=UTF-8")
                        .sessionAttr("patient", patient1)
                        .param("family", "")
                        .param("given", patient1.getGiven())
                        .param("dateOfBirth", patient1.getDateOfBirth().toString())
                        .param("sex", "F")
                        .param("address", patient1.getAddress())
                        .param("phone", patient1.getPhone()))
                .andExpect(status().isOk())
                .andExpect(handler().methodName("updatePatient"))
                .andExpect(model().hasErrors())
                .andExpect(view().name("patients/update"));

    }

    @Test
    void updateNonExistentPatientAndValidArgumentsTest() throws Exception {
        when(patientService.findPatientById(anyInt())).thenThrow(ElementNotFoundException.class);
        mockMvc.perform(post("/patients/update/1")
                        .contentType("text/html;charset=UTF-8")
                        .sessionAttr("patient", patient1)
                        .param("family", patient1.getFamily())
                        .param("given", patient1.getGiven())
                        .param("dateOfBirth", patient1.getDateOfBirth().toString())
                        .param("sex", "F")
                        .param("address", patient1.getAddress())
                        .param("phone", patient1.getPhone()))
                .andExpect(status().isNotFound())
                .andExpect(handler().methodName("updatePatient"));
    }

    // DELETE PATIENT TESTS //

    @Test
    void deletePatientTest() throws Exception {
        doNothing().when(patientService).deletePatient(anyInt());
        mockMvc.perform(get("/patients/delete/1")
                .sessionAttr("patient", patient1))
                .andExpect(status().is3xxRedirection())
                .andExpect(handler().methodName("deletePatient"))
                .andExpect(view().name("redirect:/patients/list"));
    }
}
