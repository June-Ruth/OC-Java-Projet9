package com.openclassrooms.mediscreen.patientmicroservice.controller;

import com.openclassrooms.mediscreen.patientmicroservice.model.Patient;
import com.openclassrooms.mediscreen.patientmicroservice.service.PatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PatientController {
    /**
     * @see Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(PatientController.class);
    /**
     * @see PatientService
     */
    private PatientService patientService;

    /**
     * Public constructor.
     * @param patientService1 .
     */
    public PatientController(final PatientService patientService1) {
        patientService = patientService1;
    }

    /**
     * Get a patient by its id.
     * Throw ElementNotFoundException if it doesn't exist.
     * @param id of user
     * @return patient found
     */
    @GetMapping("/patients/{id}")
    public Patient getPatient(@PathVariable final Integer id) {
        LOGGER.info("Getting patient with id : " + id);
        return patientService.findPatientById(id);
    }

    /**
     * Get all patients with full name corresponding.
     * If no family or given is given, then all patients referenced in database are return.
     * @param family .
     * @param given .
     * @return all patient corresponding
     */
    @GetMapping("/patients")
    public List<Patient> getAllPatientsByFullName(@RequestParam(required = false) final String family,
                                                  @RequestParam(required = false) final String given) {
        LOGGER.info("Getting all patients with family : " + family + " and given : " + given);
        if (family == null && given == null) {
            return patientService.findAllPatients();
        }
        return patientService.findAllPatientsByFullName(family, given);
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
        updatedPatient.setId(id);
        return patientService.savePatient(updatedPatient);
    }

    /**
     * Delete patient by its id.
     * @param id
     */
    @DeleteMapping("/patients/{id}")
    public void deletePatient(@PathVariable final Integer id) {
        LOGGER.info("Deleting patient with id : " + id);
        patientService.deletePatient(id);
    }
}
