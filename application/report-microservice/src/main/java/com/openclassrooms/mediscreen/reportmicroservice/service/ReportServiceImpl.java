package com.openclassrooms.mediscreen.reportmicroservice.service;

import com.openclassrooms.mediscreen.reportmicroservice.enums.Risk;
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
     * Public constructor.
     */
    public ReportServiceImpl() {

    }

    /**
     * @inheritDoc
     */
    @Override
    public Risk getDiabetesAssessmentLevel(char patientSex, int age, List<String> allNotesContent) { //TODO : prepare test
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

    private int calculateKeywordsInNotesContent(List<String> allNotesContent) {
        //TODO
        return 0;
    }
}