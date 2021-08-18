package com.openclassrooms.mediscreen.webapp.controller;

import com.openclassrooms.mediscreen.webapp.dto.PatientAssessmentDto;
import com.openclassrooms.mediscreen.webapp.model.Patient;
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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(ReportController.class)
public class ReportControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatientService patientService;

    @MockBean
    private ReportService reportService;

    private Patient patient;
    private PatientAssessmentDto patientDto;
    private List<Patient> patientList;
    private List<PatientAssessmentDto> patientDtoList;

    @BeforeEach
    void beforeEach() {
        patient = new Patient("family", "given", LocalDate.now(), 'F', "address", "phone");
        patientDto = new PatientAssessmentDto();
        patientDto.setId(1);
        patientDto.setFamily(patient.getFamily());
        patientDto.setGiven(patient.getGiven());
        patientDto.setAssessment("assessment");
        patientDtoList = new ArrayList<>();
        patientDtoList.add(patientDto);
        patientList = new ArrayList<>();
        patientList.add(patient);
    }

    @Test
    void getDiabetesAssessmentByPatientFullName() throws Exception {
        when(patientService.findAllPatientsByFullName(anyString(), anyString())).thenReturn(patientList);
        when(reportService.getDiabetesAssessmentByPatient(any(Patient.class))).thenReturn(patientDto.getAssessment());
        mockMvc.perform(get("/assess")
                .param("family", "family")
                .param("given", "given"))
                .andExpect(status().isOk())
                .andExpect(handler().methodName("getDiabetesAssessmentByPatientFullName"))
                .andExpect(view().name("assess"));
    }


}
