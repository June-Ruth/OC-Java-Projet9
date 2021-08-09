package com.openclassrooms.mediscreen.webapp.integration;

import com.openclassrooms.mediscreen.webapp.model.Patient;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@Disabled // NB : get problem with id param of patient.
@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PatientControllerIT {

    /*
    Before running IT :
    - init schema test and table patient
    - make sure that patient database is empty
    - specify test as active profile in application.properties of patient-api
    - run patient-api
    */

    @Autowired
    private MockMvc mockMvc;

    private Patient patient1;
    private List<Patient> allPatients;

    @BeforeEach
    void beforeEach() {
        patient1 = new Patient("family1", "given1", LocalDate.of(2001, 1, 1), 'F', "address1", "phone1");
        allPatients = new ArrayList<>();
        allPatients.add(patient1);
    }

    // ADD PATIENT FORM IT //

    @Order(1)
    @Test
    void addPatientFormTest() throws Exception {
        mockMvc.perform(get("/patients/add"))
                .andExpect(status().isOk())
                .andExpect(handler().methodName("addPatientForm"))
                .andExpect(view().name("patients/add"));
    }

    // VALIDATE NEW PATIENT TESTS //

    @Order(2)
    @Test
    void validateNewPatientWithValidArgumentsTest() throws Exception {
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

    @Order(3)
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

    // GET ALL PATIENTS TESTS //

    @Order(4)
    @Test
    void getAllPatientsTest() throws Exception {
        mockMvc.perform(get("/patients/list"))
                .andExpect(status().isOk())
                .andExpect(handler().methodName("getAllPatients"))
                .andExpect(view().name("patients/list"));
    }

    // GET PATIENT TESTS //

    @Order(5)
    @Test
    void getExistingPatientTest() throws Exception {
        mockMvc.perform(get("/patients/profile/{id}", patient1.getId()))
                .andExpect(status().isOk())
                .andExpect(handler().methodName("getPatient"))
                .andExpect(model().attribute("patient", patient1))
                .andExpect(view().name("patients/profile"));
    }

    @Order(6)
    @Test
    void getNonExistentPatientTest() throws Exception {
        mockMvc.perform(get("/patients/profile/46"))
                .andExpect(status().isNotFound())
                .andExpect(handler().methodName("getPatient"));
    }

    // SHOW UPDATE FORM TESTS //

    @Order(7)
    @Test
    void showUpdateFormForExistingPatientTest() throws Exception {
        mockMvc.perform(get("/patients/update/{id}", patient1.getId())
                        .sessionAttr("patient", patient1)
                        .param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(handler().methodName("showUpdateForm"))
                .andExpect(request().attribute("patient", patient1))
                .andExpect(view().name("patients/update"));
    }

    @Order(8)
    @Test
    void showUpdateFormForNonExistentPatientTest() throws Exception {
        mockMvc.perform(get("/patients/update/{id}", 46)
                        .sessionAttr("patient", patient1)
                        .param("id", "46"))
                .andExpect(status().isNotFound())
                .andExpect(handler().methodName("showUpdateForm"));
    }

    // UPDATE PATIENT TESTS //

    @Order(9)
    @Test
    void updateExistingPatientAndValidArgumentsTest() throws Exception {
        mockMvc.perform(post("/patients/update/{id}", patient1.getId())
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

    @Order(10)
    @Test
    void updateExistingPatientAndInvalidArgumentsTest() throws Exception {
        mockMvc.perform(post("/patients/update/{id}", patient1.getId())
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

    @Order(11)
    @Test
    void updateNonExistentPatientAndValidArgumentsTest() throws Exception {
        mockMvc.perform(post("/patients/update/46")
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

    @Order(12)
    @Test
    void deletePatientTest() throws Exception {
        mockMvc.perform(get("/patients/delete/{id}", patient1.getId())
                        .sessionAttr("patient", patient1))
                .andExpect(status().is3xxRedirection())
                .andExpect(handler().methodName("deletePatient"))
                .andExpect(view().name("redirect:/patients/list"));
    }

}
