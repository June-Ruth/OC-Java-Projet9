package com.openclassrooms.mediscreen.webapp.controller;

import com.openclassrooms.mediscreen.webapp.dto.DtoConverter;
import com.openclassrooms.mediscreen.webapp.dto.PatientAssessmentDto;
import com.openclassrooms.mediscreen.webapp.model.Patient;
import com.openclassrooms.mediscreen.webapp.service.NoteService;
import com.openclassrooms.mediscreen.webapp.service.PatientService;
import com.openclassrooms.mediscreen.webapp.service.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ReportController {

    /**
     * @see Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ReportController.class);

    /**
     * @see NoteService
     */
    private final ReportService reportService;
    /**
     * @see PatientService
     */
    private final PatientService patientService;

    /**
     * Public constructor.
     * @param reportService1 .
     */
    public ReportController(final ReportService reportService1,
                            final PatientService patientService1) {
        reportService = reportService1;
        patientService = patientService1;
    }

    /**
     * Generate list of the diabetes assessment of patients found by full name.
     * @param family of patient concerned
     * @param given of patient concerned.
     * @return View of the diabetes assessment of all patients found with full name
     */
    @GetMapping("/assess")
    public String getDiabetesAssessmentByPatientFullName(@RequestParam(required = false) final String family,
                                                         @RequestParam(required = false) final String given,
                                                         final Model model) {
        List<Patient> resultList = patientService.findAllPatientsByFullName(family, given);
        List<PatientAssessmentDto> patientList = new ArrayList<>();
        for (Patient patient : resultList) {
            String assessment = reportService.getDiabetesAssessmentByPatient(patient);
            PatientAssessmentDto patientDto = DtoConverter.convertToPatientAssessmentDto(patient, assessment);
            patientList.add(patientDto);
        }
        model.addAttribute("patientList", patientList);
        return "assess";
    }


}
