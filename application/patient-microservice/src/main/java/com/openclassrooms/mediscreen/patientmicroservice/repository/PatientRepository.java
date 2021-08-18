package com.openclassrooms.mediscreen.patientmicroservice.repository;

import com.openclassrooms.mediscreen.patientmicroservice.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
    /**
     * Find patient by its full name.
     * @param family .
     * @param given .
     * @return List of patient that should correspond
     */
    List<Patient> findByFamilyContainingIgnoreCaseOrGivenContainingIgnoreCase(String family, String given);

    /**
     * Find patient by its family name.
     * @param family .
     * @return List of patient that should correspond
     */
    List<Patient> findByFamilyContainingIgnoreCase(String family);

    /**
     * Find a patient by its given name.
     * @param given .
     * @return List of patient that should correspond
     */
    List<Patient> findByGivenContainingIgnoreCase(String given);
}
