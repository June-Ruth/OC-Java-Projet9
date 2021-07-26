package com.openclassrooms.mediscreen.repository;

import com.openclassrooms.mediscreen.model.Patient;
import com.openclassrooms.mediscreen.service.PatientService;
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
}
