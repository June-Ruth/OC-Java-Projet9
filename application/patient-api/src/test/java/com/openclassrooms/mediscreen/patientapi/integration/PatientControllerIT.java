package com.openclassrooms.mediscreen.patientapi.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.mediscreen.patientapi.model.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class PatientControllerIT {

    /*
    Before running IT, make sure that schema test was init.
     */

    @Autowired
    private MockMvc mockMvc;

    private Patient patient1;

    @BeforeEach
    void beforeEach(@Autowired DataSource dataSource) {
        try (Connection conn = dataSource.getConnection()) {
            ScriptUtils.executeSqlScript(conn, new ClassPathResource("data-test.sql"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        patient1 = new Patient("family1", "given1", LocalDate.of(2001, 1, 1), 'F', "address1", "phone1");
    }

    // FIND ALL PATIENTS //

    @Test
    void getAllPatientsTest() throws Exception {
        mockMvc.perform(get("/patients"))
                .andExpect(status().isOk());
    }

    // FIND PATIENT BY ID //

    @Test
    void getExistingPatientByIdTest() throws Exception {
        mockMvc.perform(get("/patients/1"))
                .andExpect(status().isOk());
    }

    @Test
    void getNonExistentPatientByIdTest() throws Exception {
        mockMvc.perform(get("/patients/46"))
                .andExpect(status().isNotFound());
    }

    // SAVE PATIENT //

    @Test
    void savePatientTest() throws Exception {
        mockMvc.perform(post("/patients")
                        .content(new ObjectMapper().writeValueAsString(patient1))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    //UPDATE PATIENT //

    @Test
    void updateExistingPatientTest() throws Exception {
        mockMvc.perform(put("/patients/1")
                        .content(new ObjectMapper().writeValueAsString(patient1))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    // DELETE BID LIST TEST //

    @Test
    void deletePatientTest() throws Exception {
        mockMvc.perform(delete("/patients/1"))
                .andExpect(status().isOk());
    }
}
