package com.openclassrooms.mediscreen.reportmicroservice.service;

import com.openclassrooms.mediscreen.reportmicroservice.model.Keyword;
import com.openclassrooms.mediscreen.reportmicroservice.model.Risk;
import com.openclassrooms.mediscreen.reportmicroservice.repository.KeywordRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {
    /**
     * @see Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ReportServiceImpl.class);
    /**
     * @see KeywordRepository
     */
    private KeywordRepository keywordRepository;

    /**
     * Public constructor.
     */
    public ReportServiceImpl(final KeywordRepository keywordRepository1) {
        keywordRepository = keywordRepository1;
    }

    /**
     * @inheritDoc
     */
    @Override
    public Risk getDiabetesAssessmentLevel(char patientSex, int age, final List<String> allNotesContent) { //TODO : prepare test
        int numberOfKeywordsInNotes = calculateKeywordsInNotesContent(allNotesContent);
        if (age>=30) {
            if (numberOfKeywordsInNotes<=1) {
                return Risk.NONE;
            } else if (numberOfKeywordsInNotes<=5) {
                return Risk.BORDERLINE;
            } else if (numberOfKeywordsInNotes<=7) {
                return Risk.IN_DANGER;
            } else {
                return Risk.EARLY_ONSET;
            }
        } else if (patientSex =='H') {
            if (numberOfKeywordsInNotes<=2) {
                return Risk.NONE;
            } else if (numberOfKeywordsInNotes<=4) {
                return Risk.IN_DANGER;
            } else {
                return Risk.EARLY_ONSET;
            }
        } else {
            if (numberOfKeywordsInNotes<=3) {
                return Risk.NONE;
            } else if (numberOfKeywordsInNotes<=6) {
                return Risk.IN_DANGER;
            } else {
                return Risk.EARLY_ONSET;
            }
        }
    }

    /**
     * Calculate the number of keyword in selected notes content.
     * Duplicates count for one only.
     * @param allNotesContent set
     * @return number of keyword detected
     */
    private int calculateKeywordsInNotesContent(final List<String> allNotesContent) {
        List<Keyword> keywords = keywordRepository.getAll();
        int numberOfKeyword = 0;
        for(Keyword keyword : keywords) {
            for(String note : allNotesContent) {
                if(note.contains(keyword.toString())) {
                    numberOfKeyword++;
                    break;
                }
            }
        }
        return numberOfKeyword;
    }
}
