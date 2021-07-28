package com.openclassrooms.mediscreen.patientapi.service;

import com.openclassrooms.mediscreen.patientapi.exception.ElementNotFoundException;
import com.openclassrooms.mediscreen.patientapi.model.Patient;
import com.openclassrooms.mediscreen.patientapi.repository.PatientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PatientServiceImpl.class);

    private final PatientRepository patientRepository;

    public PatientServiceImpl(final PatientRepository patientRepository1) {
        patientRepository = patientRepository1;
    }

    /**
     * @inheritDoc
     */
    @Override
    public List<Patient> findAllPatients() {
        LOGGER.info("Finding all patients");
        return patientRepository.findAll();
    }

    /**
     * @inheritDoc
     */
    @Override
    public Patient findPatientById(final Integer id) {
        LOGGER.info("Finding patient with id : " + id);
        return patientRepository.findById(id).orElseThrow(() -> new ElementNotFoundException("No user find for id : " + id));
    }

    /**
     * @inheritDoc
     */
    @Override
    public Patient savePatient(final Patient newPatient) {
        LOGGER.info("Adding patient with family name " + newPatient.getFamily());
        return patientRepository.save(newPatient);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void deletePatient(final Integer id) {
        LOGGER.info("Deleting patient with id : " + id);
        patientRepository.deleteById(id);

    }
}
