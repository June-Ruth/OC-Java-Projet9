package com.openclassrooms.mediscreen.patientapi.service;

import com.openclassrooms.mediscreen.patientapi.model.Patient;

import java.util.List;

public interface PatientService {

    /**
     * Find all patients in database.
     * @return List of all patients
     */
    List<Patient> findAllPatients();

    Patient findPatientById(Integer id);

    Patient savePatient(Patient newPatient);

    void deletePatient(Integer id);
}
