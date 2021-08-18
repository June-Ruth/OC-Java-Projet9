package com.openclassrooms.mediscreen.reportmicroservice.service;

import com.openclassrooms.mediscreen.reportmicroservice.model.Risk;

import java.util.List;

public interface ReportService {
    /**
     * Get the diabetes assessment level for the given parameters.
     * @param patientSex .
     * @param age .
     * @param allNotesContent .
     * @return risk depending on parameters
     */
    Risk getDiabetesAssessmentLevel(char patientSex, int age, List<String> allNotesContent);
}
