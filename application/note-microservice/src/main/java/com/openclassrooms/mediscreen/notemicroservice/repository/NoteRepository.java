package com.openclassrooms.mediscreen.notemicroservice.repository;

import com.openclassrooms.mediscreen.notemicroservice.model.Note;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigInteger;
import java.util.List;

public interface NoteRepository extends MongoRepository<Note, BigInteger> {
    /**
     * Find all the note of a specific patient.
     * @param patientId of patient concerned
     * @return list of all is note
     */
    List<Note> findByPatientId(Integer patientId);
}
