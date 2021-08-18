package com.openclassrooms.mediscreen.webapp.dto;

import com.openclassrooms.mediscreen.webapp.model.Patient;

public final class DtoConverter {
    /**
     * Private constructor.
     */
    private DtoConverter() {

    }

    /**
     * Convert patient and risk to Dto.
     * @param patient concerned
     * @param risk calculated
     * @return patient dto for assessment
     */
    public static PatientAssessmentDto convertToPatientAssessmentDto(final Patient patient, final String risk) {
        PatientAssessmentDto patientAssessmentDto = new PatientAssessmentDto();
        patientAssessmentDto.setId(patient.getId());
        patientAssessmentDto.setFamily(patient.getFamily());
        patientAssessmentDto.setGiven(patient.getGiven());
        patientAssessmentDto.setAssessment(risk);
        return patientAssessmentDto;
    }
}
