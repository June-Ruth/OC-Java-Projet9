package com.openclassrooms.mediscreen.webapp.service;

import com.openclassrooms.mediscreen.webapp.model.Patient;

public interface ReportService {
   /**
    * Generate the diabetes assessment report for a patient.
    * @param patient searched
    * @return the report
    */
   String getDiabetesAssessmentByPatient(Patient patient);
}
