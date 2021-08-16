package com.openclassrooms.mediscreen.reportmicroservice.controller;

import com.openclassrooms.mediscreen.reportmicroservice.model.Risk;
import com.openclassrooms.mediscreen.reportmicroservice.service.ReportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReportController.class)
public class ReportControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReportService reportService;

    private List<String> allNotesContent;

    @BeforeEach
    void beforeEach() {
        allNotesContent =new ArrayList<>();
        allNotesContent.add("test");
        allNotesContent.add("test2");
    }

    // GET DIABETES ASSESSMENT LEVEL //

    @Test
    void getDiabetesAssessmentLevel() throws Exception {
        when(reportService.getDiabetesAssessmentLevel(anyChar(), anyInt(), anyList())).thenReturn(Risk.BORDERLINE);
        mockMvc.perform(get("/assessment")
                        .param("patientSex", "F")
                        .param("age", "35")
                        .param("allNotesContent", allNotesContent.get(0))
                        .param("allNotesContent", allNotesContent.get(1)))
                .andExpect(status().isOk());
    }

}
