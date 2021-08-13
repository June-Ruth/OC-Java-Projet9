package com.openclassrooms.mediscreen.reportmicroservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ReportControllerTest.class)
public class ReportControllerTest {

    @Autowired
    private MockMvc mockMvc;

    /*
    @MockBean
    private ReportService reportService;

    @BeforeEach
     */


}
