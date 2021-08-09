package com.openclassrooms.mediscreen.patientmicroservice.repository;

import com.openclassrooms.mediscreen.patientmicroservice.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Integer> {

}
