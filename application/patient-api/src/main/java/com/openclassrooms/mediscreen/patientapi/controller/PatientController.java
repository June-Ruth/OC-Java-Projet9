package com.openclassrooms.mediscreen.patientapi.controller;


import com.openclassrooms.mediscreen.patientapi.model.Patient;
import com.openclassrooms.mediscreen.patientapi.service.PatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PatientController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PatientController.class);

    private PatientService patientService;

    public PatientController(final PatientService patientService1) {
        patientService = patientService1;
    }

    /**
     * Get all patients in database.
     * @return list of all patient
     */
    @GetMapping("/patients")
    public List<Patient> getAllPatients() {
        LOGGER.info("Getting all patients.");
        return patientService.findAllPatients();
    }

    /**
     * Get a patient by its id.
     * Throw ElementNotFoundException if id doesn't exist.
     * @param id of user
     * @return patient found
     */
    @GetMapping("/patients/{id}")
    public Patient getPatient(@PathVariable final Integer id) {
        LOGGER.info("Getting patient with id : " + id);
        return patientService.findPatientById(id);
    }

    /**
     * Save new patient.
     * @param newPatient to save
     * @return patient saved
     */
    @PostMapping("/patients")
    public Patient savePatient(@RequestBody final Patient newPatient) {
        LOGGER.info("Saving patient with family name " + newPatient.getFamily());
        return patientService.savePatient(newPatient);
    }

    /**
     * Update patient.
     * @param id
     * @param updatedPatient
     * @return patient updated
     */
    @PutMapping("/patients/{id}")
    public Patient updatePatient(@PathVariable final Integer id,
                                 @RequestBody final Patient updatedPatient) {
        LOGGER.info("Updating patient with id " + id);
        return patientService.savePatient(updatedPatient);
    }

    /**
     * Delete patient by its id
     * @param id
     */
    @DeleteMapping("/patients/{id}")
    public void deletePatient(@PathVariable final Integer id) {
        LOGGER.info("Deleting patient with id : " + id);
        patientService.deletePatient(id);
    }
}
