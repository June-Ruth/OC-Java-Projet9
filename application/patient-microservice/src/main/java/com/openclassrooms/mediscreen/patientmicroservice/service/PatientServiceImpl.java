package com.openclassrooms.mediscreen.patientmicroservice.service;

import com.openclassrooms.mediscreen.patientmicroservice.exception.ElementNotFoundException;
import com.openclassrooms.mediscreen.patientmicroservice.model.Patient;
import com.openclassrooms.mediscreen.patientmicroservice.repository.PatientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    /**
     * @see Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(PatientServiceImpl.class);

    /**
     * @see PatientRepository
     */
    private final PatientRepository patientRepository;

    /**
     * Public constructor.
     * @param patientRepository1 - autowired
     */
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
    public List<Patient> findAllPatientsByFullName(final String family, final String given) {
        LOGGER.info("Finding all patient with family : " + family + " and given : " + given);
        if ((family != null && !family.isBlank()) && (given != null && !given.isBlank())) {
            return patientRepository.findByFamilyContainingIgnoreCaseOrGivenContainingIgnoreCase(family, given);
        } else if ((family == null || family.isBlank()) && (given != null && !given.isBlank())) {
            return patientRepository.findByGivenContainingIgnoreCase(given);
        } else if ((family != null && !family.isBlank()) && (given == null || given.isBlank())) {
            return patientRepository.findByFamilyContainingIgnoreCase(family);
        } else {
            return findAllPatients();
        }
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
