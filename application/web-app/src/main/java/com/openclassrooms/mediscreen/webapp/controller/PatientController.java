package com.openclassrooms.mediscreen.webapp.controller;

import com.openclassrooms.mediscreen.webapp.model.Patient;
import com.openclassrooms.mediscreen.webapp.service.PatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class PatientController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PatientController.class);

    private final PatientService patientService;

    public PatientController(final PatientService patientService1) {
        patientService = patientService1;
    }

    @GetMapping("/patients/list")
    public String getAllPatients(final Model model) {
        LOGGER.info("Getting all patients.");
        List<Patient> patientList = patientService.findAllPatients();
        model.addAttribute("patientList", patientList);
        return "patients/list";
    }

    @GetMapping("/patients/profile/{id}")
    public String getPatient(@PathVariable final Integer id,
                             final Model model) {
        LOGGER.info("Getting patient with id : " + id);
        model.addAttribute("patient", patientService.findPatientById(id));
        return "patients/profile";
    }

    @GetMapping("/patients/add")
    public String addPatientForm(final Patient patient) {
        LOGGER.info("Show form to add patient");
        return "patients/add";
    }

    @PostMapping("/patients/validate")
    public String validateNewPatient(@Valid final Patient patient,
                                     final BindingResult result,
                                     final Model mode) {
        LOGGER.info("Saving new patient");
        if(!result.hasErrors()){
            Patient patient1 = patientService.savePatient(patient);
            return "redirect:/patients/list";
        }
        return "patients/add";
    }

    @GetMapping("/patients/update/{id}")
    public String showUpdateForm(@PathVariable final Integer id,
                                 final Model model) {
        LOGGER.info("Show form for update");
        model.addAttribute("patient", patientService.findPatientById(id));
        return "patients/update";
    }

    @PostMapping("/patients/update/{id}")
    public String updatePatient(@PathVariable final Integer id,
                                @Valid final Patient updatedPatient,
                                final BindingResult result,
                                final Model model) {
        LOGGER.info("Updating patient with id : " + id);
        if(!result.hasErrors()) {
            Patient patient = patientService.findPatientById(id);
            patient.setFamily(updatedPatient.getFamily());
            patient.setGiven(updatedPatient.getGiven());
            patient.setDateOfBirth(updatedPatient.getDateOfBirth());
            patient.setSex(updatedPatient.getSex());
            patient.setAddress(updatedPatient.getAddress());
            patient.setPhone(updatedPatient.getPhone());
            patientService.updatePatient(patient);
            return "redirect:/patients/profile/{id}";
        }
        updatedPatient.setId(id);
        return "patients/update";
    }

    @GetMapping("/patients/delete/{id}")
    public String deletePatient(@PathVariable final Integer id,
                                final Model model) {
        LOGGER.info("Deleting patient with id : " + id);
        patientService.deletePatient(id);
        return "redirect:/patients/list";
    }
}
