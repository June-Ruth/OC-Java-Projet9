package com.openclassrooms.mediscreen.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

import static com.openclassrooms.mediscreen.constant.ErrorMessage.FIELD_IS_MANDATORY;
import static com.openclassrooms.mediscreen.constant.ErrorMessage.TOO_MUCH_CHARACTERS;
import static com.openclassrooms.mediscreen.constant.Number.*;

@Entity
@Table(name = "patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "patient_id")
    private Integer id;

    @NotBlank(message = FIELD_IS_MANDATORY)
    @Size(max = TWENTY, message = TOO_MUCH_CHARACTERS)
    @Column(name = "family")
    private String family;

    @NotBlank(message = FIELD_IS_MANDATORY)
    @Size(max = TWENTY, message = TOO_MUCH_CHARACTERS)
    @Column(name = "given")
    private String given;

    @NotBlank(message = FIELD_IS_MANDATORY)
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @NotBlank(message = FIELD_IS_MANDATORY)
    @Column(name = "sex")
    private char sex;

    @NotBlank(message = FIELD_IS_MANDATORY)
    @Size(max = HUNDRED, message = TOO_MUCH_CHARACTERS)
    @Column(name = "address")
    private String address;

    @NotBlank(message = FIELD_IS_MANDATORY)
    @Size(max = FIFTEEN, message = TOO_MUCH_CHARACTERS)
    @Column(name = "phone")
    private String phone;

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

    public Patient() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id1) {
        id = id1;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(final String family1) {
        family = family1;
    }

    public String getGiven() {
        return given;
    }

    public void setGiven(final String given1) {
        given = given1;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(final LocalDate dateOfBirth1) {
        dateOfBirth = dateOfBirth1;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(final char sex1) {
        sex = sex1;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(final String address1) {
        address = address1;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(final String phone1) {
        phone = phone1;
    }
}
