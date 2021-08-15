package com.openclassrooms.mediscreen.reportmicroservice.model;

public enum Risk {
    /**
     * Risk level none.
     */
    NONE("None"),
    /**
     * Risk level borderline.
     */
    BORDERLINE("Borderline"),
    /**
     * Risk level in danger.
     */
    IN_DANGER("In Danger"),
    /**
     * Risk level early onset.
     */
    EARLY_ONSET("Early Onset");
    /**
     * Risk label.
     */
    private final String risk;

    /**
     * Constructor.
     * @param risk1 label
     */
    Risk(final String risk1) {
        risk = risk1;
    }

    /**
     * Getter risk.
     * @return risk label
     */
    public String getRisk() {
        return risk;
    }
}
