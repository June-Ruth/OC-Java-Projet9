package com.openclassrooms.mediscreen.patientmicroservice.service;

import com.openclassrooms.mediscreen.patientmicroservice.exception.ElementNotFoundException;
import com.openclassrooms.mediscreen.patientmicroservice.model.Patient;
import com.openclassrooms.mediscreen.patientmicroservice.repository.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class PatientServiceTest {

    @Mock
    private static PatientRepository patientRepository;

    private static PatientService patientService;

    private Patient patient1;
    private Patient patient2;
    private List<Patient> allPatients;

    @BeforeEach
    void beforeEach() {
        patientService = new PatientServiceImpl(patientRepository);
        patient1 = new Patient("family1", "given1", LocalDate.of(2001, 1, 1), 'F', "address1", "phone1");
        patient2 = new Patient("family2", "given2", LocalDate.of(2002, 2, 2), 'M', "address2", "phone2");
        allPatients = new ArrayList<>();
        allPatients.add(patient1);
        allPatients.add(patient2);
    }

    // FIND ALL PATIENTS //

    @Test
    void findAllPatientsTest() {
        when(patientRepository.findAll()).thenReturn(allPatients);
        patientService.findAllPatients();
        verify(patientRepository, times(1)).findAll();
    }

    // FIND PATIENT BY FULL NAME //

    @Test
    void findAllPatientsByFullNameTest() {
        when(patientRepository.findByFamilyLikeAndGivenLike(anyString(), anyString())).thenReturn(allPatients);
        patientService.findAllPatientsByFullName("family", "given");
        verify(patientRepository, times(1)).findByFamilyLikeAndGivenLike("family", "given");
    }

    // FIND PATIENT BY ID //

    @Test
    void findExistingPatientByIdTest() {
        when(patientRepository.findById(anyInt())).thenReturn(Optional.of(patient1));
        patientService.findPatientById(1);
        verify(patientRepository, times(1)).findById(1);
    }

    @Test
    void findNonExistentPatientByIdTest() {
        when(patientRepository.findById(anyInt())).thenReturn(Optional.empty());
        assertThrows(ElementNotFoundException.class, () -> patientService.findPatientById(1));
    }

    // SAVE PATIENT //

    @Test
    void savePatientTest() {
        when(patientRepository.save(any(Patient.class))).thenReturn(patient1);
        patientService.savePatient(patient1);
        verify(patientRepository, times(1)).save(patient1);
    }

    // DELETE BID LIST TEST //

    @Test
    void deletePatientTest() {
        doNothing().when(patientRepository).deleteById(anyInt());
        patientService.deletePatient(1);
        verify(patientRepository, times(1)).deleteById(1);
    }
}
