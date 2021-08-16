package com.openclassrooms.mediscreen.webapp.dto;

public class PatientAssessmentDto {
    /**
     * Id of patient.
     */
    private Integer id;
    /**
     * Family name of patient.
     */
    private String family;
    /**
     * Given name of patient.
     */
    private String given;
    /**
     * Diabetes assessment.
     */
    private String assessment;

    /**
     * Empty constructor.
     */
    public PatientAssessmentDto() {

    }

    /**
     * Getter id.
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Setter id.
     * @param id1 to set
     */
    public void setId(final Integer id1) {
        id = id1;
    }

    /**
     * Getter family.
     * @return family
     */
    public String getFamily() {
        return family;
    }

    /**
     * Setter family.
     * @param family1 to set
     */
    public void setFamily(final String family1) {
        family = family1;
    }

    /**
     * Getter given.
     * @return given
     */
    public String getGiven() {
        return given;
    }

    /**
     * Setter given.
     * @param given1 to set
     */
    public void setGiven(final String given1) {
        given = given1;
    }

    /**
     * Getter risk.
     * @return risk
     */
    public String getAssessment() {
        return assessment;
    }

    /**
     * Setter risk.
     * @param assessment1 to set
     */
    public void setAssessment(final String assessment1) {
        assessment = assessment1;
    }
}
