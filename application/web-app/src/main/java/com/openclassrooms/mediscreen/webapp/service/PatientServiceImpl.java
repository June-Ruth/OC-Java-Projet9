package com.openclassrooms.mediscreen.webapp.service;

import com.openclassrooms.mediscreen.webapp.model.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PatientServiceImpl.class);

    public PatientServiceImpl() {

    }

    /**
     * @inheritDoc
     */
    @Override
    public List<Patient> findAllPatients() {
        LOGGER.info("Finding all patients");
        //TODO : WebClient
        return null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public Patient findPatientById(final Integer id) {
        LOGGER.info("Finding patient with id : " + id);
        //TODO : WebClient
        return null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public Patient savePatient(final Patient patient) {
        LOGGER.info("Adding patient with family name " + patient.getFamily());
        //TODO : WebClient
        return null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void deletePatient(final Integer id) {
        LOGGER.info("Deleting patient with id : " + id);
        //TODO : WebClient
    }
}
