package com.openclassrooms.mediscreen.notemicroservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Document(collection = "note")
public class Note {
    /**
     * ID of the note.
     */
    @Id
    private String id;
    /**
     * ID of the patient concerned by the note.
     */
    @NotNull
    @Field(value = "patient_id")
    private Integer patientId;
    /**
     * Date of creation of the note.
     */
    @NotNull
    @Field(value = "creation_date")
    private LocalDate creationDate;
    /**
     * Date of the last modification of the note.
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Field(value = "last_modification_date")
    private LocalDate lastModificationDate;
    /**
     * Content of the note.
     */
    @NotBlank
    @Field(value = "content")
    private String content;

    /**
     * Public constructor.
     * @param patientId1 .
     * @param creationDate1 .
     * @param lastModificationDate1 .
     * @param content1 .
     */
    public Note(final Integer patientId1,
                final LocalDate creationDate1,
                final LocalDate lastModificationDate1,
                final String content1) {
        patientId = patientId1;
        creationDate = creationDate1;
        lastModificationDate = lastModificationDate1;
        content = content1;
    }

    /**
     * Public empty constructor.
     */
    public Note() { }

    /**
     * Getter ID.
     * @return ID
     */
    public String getId() {
        return id;
    }

    /**
     * Setter ID.
     * @param id1 to set
     */
    public void setId(final String id1) {
        id = id1;
    }

    /**
     * Getter patient ID.
     * @return patient ID
     */
    public Integer getPatientId() {
        return patientId;
    }

    /**
     * Setter Patient ID.
     * @param patientId1 to set
     */
    public void setPatientId(final Integer patientId1) {
        patientId = patientId1;
    }

    /**
     * Getter creation date.
     * @return creation date
     */
    public LocalDate getCreationDate() {
        return creationDate;
    }

    /**
     * Setter creation date.
     * @param creationDate1 to set
     */
    public void setCreationDate(final LocalDate creationDate1) {
        creationDate = creationDate1;
    }

    /**
     * Getter last modification date.
     * @return last modification date
     */
    public LocalDate getLastModificationDate() {
        return lastModificationDate;
    }

    /**
     * Setter last modification date.
     * @param lastModificationDate1 to set
     */
    public void setLastModificationDate(final LocalDate lastModificationDate1) {
        lastModificationDate = lastModificationDate1;
    }

    /**
     * Getter content.
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * Setter content.
     * @param content1 to set
     */
    public void setContent(final String content1) {
        content = content1;
    }
}
