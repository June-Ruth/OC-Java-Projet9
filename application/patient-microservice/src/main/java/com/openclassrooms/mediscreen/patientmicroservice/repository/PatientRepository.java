package com.openclassrooms.mediscreen.patientmicroservice.repository;

import com.openclassrooms.mediscreen.patientmicroservice.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Integer> {

    List<Patient> findByFamilyContainingIgnoreCaseOrGivenContainingIgnoreCase(String family, String given);

    List<Patient> findByFamilyContainingIgnoreCase(String family);

    List<Patient> findByGivenContainingIgnoreCase(String given);
}
