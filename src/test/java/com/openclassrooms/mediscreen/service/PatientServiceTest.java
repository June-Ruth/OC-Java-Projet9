package com.openclassrooms.mediscreen.service;

import com.openclassrooms.mediscreen.model.Patient;
import com.openclassrooms.mediscreen.repository.PatientRepository;
import com.openclassrooms.mediscreen.repository.PatientServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

}
