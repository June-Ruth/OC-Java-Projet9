package com.openclassrooms.mediscreen.webapp.constant;

public final class ErrorMessage {
    /**
     * Private constructor. Override default.
     */
    private ErrorMessage() { }
    /**
     * Error message when field is mandatory and should not be blank.
     */
    public static final String FIELD_IS_MANDATORY = "Field is mandatory";
    /**
     * Error message when an entry have too much characters.
     */
    public static final String TOO_MUCH_CHARACTERS = "Entry has too much characters";
}
