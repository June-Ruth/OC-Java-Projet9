package com.openclassrooms.mediscreen.patientapi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

import static com.openclassrooms.mediscreen.patientapi.constant.ErrorMessage.FIELD_IS_MANDATORY;
import static com.openclassrooms.mediscreen.patientapi.constant.ErrorMessage.TOO_MUCH_CHARACTERS;
import static com.openclassrooms.mediscreen.patientapi.constant.Number.*;

@Entity
@Table(name = "patients")
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

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NotNull(message = FIELD_IS_MANDATORY)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @NotNull(message = FIELD_IS_MANDATORY)
    @Column(name = "sex")
    private char sex;

    @Size(max = HUNDRED, message = TOO_MUCH_CHARACTERS)
    @Column(name = "address")
    private String address;

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
