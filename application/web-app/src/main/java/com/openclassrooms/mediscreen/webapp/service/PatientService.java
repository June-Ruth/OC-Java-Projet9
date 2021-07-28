package com.openclassrooms.mediscreen.webapp.service;

import com.openclassrooms.mediscreen.webapp.model.Patient;

import java.util.List;

public interface PatientService {

    /**
     * Find all patients in database.
     * @return List of all patients
     */
    List<Patient> findAllPatients();

    Patient findPatientById(Integer id);

    Patient savePatient(Patient newPatient);

    Patient updatePatient(Patient updatedPatient);

    void deletePatient(Integer id);
}
