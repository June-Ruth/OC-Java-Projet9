package com.openclassrooms.mediscreen.reportmicroservice.controller;

import com.openclassrooms.mediscreen.reportmicroservice.model.Risk;
import com.openclassrooms.mediscreen.reportmicroservice.service.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReportController {
    /**
     * @see Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ReportController.class);
    /**
     * @see ReportService
     */
    private ReportService reportService;

    /**
     * Public constructor.
     * @param reportService1 .
     */
    public ReportController(final ReportService reportService1) {
        reportService = reportService1;
    }

    /**
     * Get the diabetes assessment level for given parameters.
     * @param patientSex .
     * @param age .
     * @param allNotesContent .
     * @return risk depending on parameters
     */
    @GetMapping("/assessment")
    public Risk getDiabetesAssessmentLevel(@RequestParam final char patientSex,
                                           @RequestParam final int age,
                                           @RequestParam final List<String> allNotesContent) {
        LOGGER.info("Get diabetes assessment level for sex " + patientSex
                + "and age : " + age + "and notes : " + allNotesContent);
        return reportService.getDiabetesAssessmentLevel(patientSex, age, allNotesContent);
    }
}
