package com.openclassrooms.mediscreen.webapp.controller;

import com.openclassrooms.mediscreen.webapp.service.NoteService;
import com.openclassrooms.mediscreen.webapp.service.PatientService;
import com.openclassrooms.mediscreen.webapp.service.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

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
     * Generate the diabetes assessment of a patient found by its id.
     * @param patientId of patient concerned.
     * @return View of the diabetes assessment
     */
    @GetMapping("/assess/patients/{patientId}")
    public String getDiabetesAssessmentByPatientId(@PathVariable final Integer patientId) {
        return null;
    }

    /**
     * Generate list of the diabetes assessment of patients found by fullname.
     * @param family of patient concerned
     * @param given of patient concerned.
     * @return View of the diabetes assessment of all patients found with fullname
     */
    @GetMapping("/assess")
    public String getDiabetesAssessmentByPatientFullName(@RequestParam final String family,
                                                         @RequestParam final String given) {
        return null;
    }


}
