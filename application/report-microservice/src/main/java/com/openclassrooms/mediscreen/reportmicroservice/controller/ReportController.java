package com.openclassrooms.mediscreen.reportmicroservice.controller;

import com.openclassrooms.mediscreen.reportmicroservice.service.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

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

    public void getDiabetesAssessmentLevel(final char patientSex, final int age /*List allNotes*/ ) {

    }

}
