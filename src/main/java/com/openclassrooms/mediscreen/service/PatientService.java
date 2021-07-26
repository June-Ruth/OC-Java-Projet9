package com.openclassrooms.mediscreen.service;

import com.openclassrooms.mediscreen.model.Patient;

import java.util.List;

public interface PatientService {

    /**
     * Find all patients in database.
     * @return List of all patients
     */
    List<Patient> findAllPatients();
}
