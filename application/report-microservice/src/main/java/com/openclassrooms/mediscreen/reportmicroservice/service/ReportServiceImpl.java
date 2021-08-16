package com.openclassrooms.mediscreen.reportmicroservice.service;

import com.openclassrooms.mediscreen.reportmicroservice.model.Keyword;
import com.openclassrooms.mediscreen.reportmicroservice.model.Risk;
import com.openclassrooms.mediscreen.reportmicroservice.repository.KeywordRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
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
    public Risk getDiabetesAssessmentLevel(char patientSex, int age, final List<String> allNotesContent) {
        LOGGER.info("Get Diabetes assessment level.");
        int numberOfKeywordsInNotes = calculateKeywordsInNotesContent(allNotesContent);
        if (age>=30) {
            if (numberOfKeywordsInNotes<=1) {
                LOGGER.info("risk is " + Risk.NONE);
                return Risk.NONE;
            } else if (numberOfKeywordsInNotes<=5) {
                LOGGER.info("risk is " + Risk.BORDERLINE);
                return Risk.BORDERLINE;
            } else if (numberOfKeywordsInNotes<=7) {
                LOGGER.info("risk is " + Risk.IN_DANGER);
                return Risk.IN_DANGER;
            } else {
                LOGGER.info("risk is " + Risk.EARLY_ONSET);
                return Risk.EARLY_ONSET;
            }
        } else if (patientSex =='H') {
            if (numberOfKeywordsInNotes<=2) {
                LOGGER.info("risk is " + Risk.NONE);
                return Risk.NONE;
            } else if (numberOfKeywordsInNotes<=4) {
                LOGGER.info("risk is " + Risk.IN_DANGER);
                return Risk.IN_DANGER;
            } else {
                LOGGER.info("risk is " + Risk.EARLY_ONSET);
                return Risk.EARLY_ONSET;
            }
        } else {
            if (numberOfKeywordsInNotes<=3) {
                LOGGER.info("risk is " + Risk.NONE);
                return Risk.NONE;
            } else if (numberOfKeywordsInNotes<=6) {
                LOGGER.info("risk is " + Risk.IN_DANGER);
                return Risk.IN_DANGER;
            } else {
                LOGGER.info("risk is " + Risk.EARLY_ONSET);
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
        LOGGER.info("calculate number of keyword in " + allNotesContent);
        List<Keyword> keywords = keywordRepository.getAll();
        int numberOfKeyword = 0;
        for(Keyword keyword : keywords) {
            String normalizedKeyword = normalizeString(keyword.toString());
            for(String note : allNotesContent) {
                String normalizedNote = normalizeString(note);
                if (normalizedNote.toLowerCase().contains(normalizedKeyword)) {
                    numberOfKeyword++;
                    break;
                }
            }
        }
        LOGGER.info("Found number of keywords = " + numberOfKeyword);
        return numberOfKeyword;
    }

    /**
     * Normalize String removing all accent.
     * @param string to normalize
     * @return String normalized
     */
    private String normalizeString(final String string) {
        LOGGER.info("Normalize string : " + string);
        return Normalizer.normalize(string, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");

    }
}
