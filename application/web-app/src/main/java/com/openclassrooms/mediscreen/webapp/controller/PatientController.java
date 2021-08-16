package com.openclassrooms.mediscreen.webapp.controller;

import com.openclassrooms.mediscreen.webapp.model.Note;
import com.openclassrooms.mediscreen.webapp.model.Patient;
import com.openclassrooms.mediscreen.webapp.service.NoteService;
import com.openclassrooms.mediscreen.webapp.service.PatientService;
import com.openclassrooms.mediscreen.webapp.service.ReportService;
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
    /**
     * @see Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(PatientController.class);

    /**
     * @see PatientService
     */
    private final PatientService patientService;
    /**
     * @see NoteService
     */
    private final NoteService noteService;
    /**
     * @see ReportService
     */
    private final ReportService reportService;

    /**
     * Public constructor.
     * @param patientService1 .
     * @param noteService1 .
     */
    public PatientController(final PatientService patientService1,
                             final NoteService noteService1,
                             final ReportService reportService1) {
        patientService = patientService1;
        noteService = noteService1;
        reportService = reportService1;
    }

    /**
     * Get all patient on page.
     * @param model .
     * @return page with all patient as list
     */
    @GetMapping("/patients/list")
    public String getAllPatients(final Model model) {
        LOGGER.info("Getting all patients.");
        List<Patient> patientList = patientService.findAllPatients();
        model.addAttribute("patientList", patientList);
        return "patients/list";
    }

    /**
     * Get patient information.
     * @param id of patient searched
     * @param model .
     * @return patient information
     */
    @GetMapping("/patients/profile/{id}")
    public String getPatient(@PathVariable final Integer id,
                             final Model model) {
        LOGGER.info("Getting patient with id : " + id);
        Patient patient = patientService.findPatientById(id);
        model.addAttribute("patient", patient);
        model.addAttribute("noteList", noteService.getAllNoteOfOnePatient(id));
        model.addAttribute("assessment", reportService.getDiabetesAssessmentByPatient(patient));
        return "patients/profile";
    }

    /**
     * Show the form to complete to add a new patient.
     * @param patient .
     * @return form to add patient
     */
    @GetMapping("/patients/add")
    public String addPatientForm(final Patient patient) {
        LOGGER.info("Show form to add patient");
        return "patients/add";
    }

    /**
     * Validate the form to add a new patient.
     * @param patient with its information
     * @param result .
     * @param model .
     * @return patient list page
     */
    @PostMapping("/patients/validate")
    public String validateNewPatient(@Valid final Patient patient,
                                     final BindingResult result,
                                     final Model model) {
        LOGGER.info("Saving new patient");
        if (!result.hasErrors()) {
            patientService.savePatient(patient);
            return "redirect:/patients/list";
        }
        return "patients/add";
    }

    /**
     * Show form to update a patient.
     * @param id of patient searched
     * @param model .
     * @return the form to update fullfil with actual patient information
     */
    @GetMapping("/patients/update/{id}")
    public String showUpdateForm(@PathVariable final Integer id,
                                 final Model model) {
        LOGGER.info("Show form to update patient");
        model.addAttribute("patient", patientService.findPatientById(id));
        return "patients/update";
    }

    /**
     * Validate update form of a patient.
     * @param id of updated patient
     * @param updatedPatient with nw information
     * @param result .
     * @param model .
     * @return the patient information page
     */
    @PostMapping("/patients/update/{id}")
    public String updatePatient(@PathVariable final Integer id,
                                @Valid final Patient updatedPatient,
                                final BindingResult result,
                                final Model model) {
        LOGGER.info("Updating patient with id : " + id);
        if (!result.hasErrors()) {
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

    /**
     * Delete a patient.
     * @param id of patient to delete
     * @param model .
     * @return patient list
     */
    @GetMapping("/patients/delete/{id}")
    public String deletePatient(@PathVariable final Integer id,
                                final Model model) {
        LOGGER.info("Deleting patient with id : " + id);
        patientService.deletePatient(id);
        return "redirect:/patients/list";
    }
}
