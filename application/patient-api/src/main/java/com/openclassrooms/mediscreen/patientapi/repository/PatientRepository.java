package com.openclassrooms.mediscreen.patientapi.repository;

import com.openclassrooms.mediscreen.patientapi.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Integer> {

}
