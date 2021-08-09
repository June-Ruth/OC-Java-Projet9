package com.openclassrooms.mediscreen.webapp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

import static com.openclassrooms.mediscreen.webapp.constant.ErrorMessage.FIELD_IS_MANDATORY;
import static com.openclassrooms.mediscreen.webapp.constant.ErrorMessage.TOO_MUCH_CHARACTERS;
import static com.openclassrooms.mediscreen.webapp.constant.Number.*;

public class Patient {

    /**
     * ID. Auto-generated.
     */
    private Integer id;

    /**
     * Family Name.
     */
    @NotBlank(message = FIELD_IS_MANDATORY)
    @Size(max = TWENTY, message = TOO_MUCH_CHARACTERS)
    private String family;

    /**
     * Fist name.
     */
    @NotBlank(message = FIELD_IS_MANDATORY)
    @Size(max = TWENTY, message = TOO_MUCH_CHARACTERS)
    private String given;

    /**
     * Date of birth.
     */
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NotNull(message = FIELD_IS_MANDATORY)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    /**
     * Sex / Gender.
     */
    @NotNull(message = FIELD_IS_MANDATORY)
    private char sex;

    /**
     * Address.
     */
    @Size(max = HUNDRED, message = TOO_MUCH_CHARACTERS)
    private String address;

    /**
     * Phone.
     */
    @Size(max = FIFTEEN, message = TOO_MUCH_CHARACTERS)
    private String phone;

    /**
     * Public constructor.
     * @param family1 of patient
     * @param given1 of patient
     * @param dateOfBirth1 of patient
     * @param sex1 of patient
     * @param address1 of patient
     * @param phone1 of patient
     */
    public Patient(final String family1,
                   final String given1,
                   final LocalDate dateOfBirth1,
                   final char sex1,
                   final String address1,
                   final String phone1) {
        family = family1;
        given = given1;
        dateOfBirth = dateOfBirth1;
        sex = sex1;
        address = address1;
        phone = phone1;
    }

    /**
     * Public empty constructor.
     */
    public Patient() { }

    /**
     * Get ID.
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Set ID.
     * @param id1 to set
     */
    public void setId(final Integer id1) {
        id = id1;
    }

    /**
     * Get family name.
     * @return family name
     */
    public String getFamily() {
        return family;
    }

    /**
     * Set family name.
     * @param family1 to set
     */
    public void setFamily(final String family1) {
        family = family1;
    }

    /**
     * Get first name.
     * @return first name
     */
    public String getGiven() {
        return given;
    }

    /**
     * Set first name.
     * @param given1 to set
     */
    public void setGiven(final String given1) {
        given = given1;
    }

    /**
     * Get date of birth.
     * @return date of birth
     */
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Set date of birth.
     * @param dateOfBirth1 to set
     */
    public void setDateOfBirth(final LocalDate dateOfBirth1) {
        dateOfBirth = dateOfBirth1;
    }

    /**
     * Get sex (gender).
     * @return sex
     */
    public char getSex() {
        return sex;
    }

    /**
     * Set sex (gender).
     * @param sex1 to set
     */
    public void setSex(final char sex1) {
        sex = sex1;
    }

    /**
     * Get address.
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Set address.
     * @param address1 to set
     */
    public void setAddress(final String address1) {
        address = address1;
    }

    /**
     * Get phone.
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Set phone.
     * @param phone1 to set
     */
    public void setPhone(final String phone1) {
        phone = phone1;
    }
}
