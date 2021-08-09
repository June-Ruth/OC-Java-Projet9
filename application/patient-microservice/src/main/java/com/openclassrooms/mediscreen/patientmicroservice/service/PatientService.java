package com.openclassrooms.mediscreen.patientmicroservice.service;

import com.openclassrooms.mediscreen.patientmicroservice.model.Patient;

import java.util.List;

public interface PatientService {

    /**
     * Find all patients in database.
     * @return List of all patients
     */
    List<Patient> findAllPatients();

    /**
     * Find a patient by its id.
     * Throw ElementNotFoundException if it doesn't exist.
     * @param id of patient
     * @return patient found
     */
    Patient findPatientById(Integer id);

    /**
     * Save new patient or update existing patient.
     * @param newPatient to save
     * @return patient saved
     */
    Patient savePatient(Patient newPatient);

    /**
     * Delete patient by its id.
     * @param id of patient to delete
     */
    void deletePatient(Integer id);
}
