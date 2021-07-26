package com.openclassrooms.mediscreen.repository;

import com.openclassrooms.mediscreen.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Integer> {

}
