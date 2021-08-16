package com.openclassrooms.mediscreen.reportmicroservice.service;

import com.openclassrooms.mediscreen.reportmicroservice.model.Keyword;
import com.openclassrooms.mediscreen.reportmicroservice.model.Risk;
import com.openclassrooms.mediscreen.reportmicroservice.repository.KeywordRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class ReportServiceTest {

    @Mock
    private static KeywordRepository keywordRepository;

    private static ReportService reportService;

    private char patientSex;
    private int age;
    private List<String> allNotesContent;
    private List<Keyword> allKeywords;

    private String note1;
    private String note2;
    private String note3;
    private String note4;
    private String note5;
    private String note6;
    private String note7;
    private String note8;
    private String note9;

    @BeforeEach()
    void beforeEach() {
        reportService = new ReportServiceImpl(keywordRepository);
        allNotesContent = new ArrayList<>();
        note1 = "Sentence with keyword1";
        note2 = "Sentence with keyword2";
        note3 = "Sentence with keyword3";
        note4 = "Sentence with keyword4";
        note5 = "Sentence with keyword5";
        note6 = "Sentence with keyword6";
        note7 = "Sentence with keyword7";
        note8 = "Sentence with keyword8";
        note9 = "Sentence with keyword9";
        allKeywords = new ArrayList<>();
        allKeywords.add(new Keyword("Keyword1"));
        allKeywords.add(new Keyword("Keyword2"));
        allKeywords.add(new Keyword("Keyword3"));
        allKeywords.add(new Keyword("Keyword4"));
        allKeywords.add(new Keyword("Keyword5"));
        allKeywords.add(new Keyword("Keyword6"));
        allKeywords.add(new Keyword("Keyword7"));
        allKeywords.add(new Keyword("Keyword8"));
        allKeywords.add(new Keyword("Keyword9"));
    }

    // GET DIABETES ASSESSMENT LEVEL TEST

    // CASE age >= 30 && keywords <= 1, RETURN NONE

    @Test
    void getDiabetesAssessmentLevelAgeMoreThan30KeywordsLessThan1SexMaleTest() {
        age = 35;
        patientSex = 'H';
        when(keywordRepository.getAll()).thenReturn(allKeywords);
        assertEquals(Risk.NONE, reportService.getDiabetesAssessmentLevel(patientSex, age, allNotesContent));
    }

    // CASE age >= 30 && keywords <= 5, RETURN BORDERLINE

    @Test
    void getDiabetesAssessmentLevelAgeMoreThan30KeywordsLessThan5SexMaleTest() {
        age = 35;
        patientSex = 'H';
        allNotesContent.add(note1);
        allNotesContent.add(note2);
        allNotesContent.add(note3);
        allNotesContent.add(note4);
        when(keywordRepository.getAll()).thenReturn(allKeywords);
        assertEquals(Risk.BORDERLINE, reportService.getDiabetesAssessmentLevel(patientSex, age, allNotesContent));
    }

    // CASE age >= 30 && keywords <= 7, RETURN IN_DANGER

    @Test
    void getDiabetesAssessmentLevelAgeMoreThan30KeywordsLessThan7SexMaleTest() {
        age = 35;
        patientSex = 'H';
        allNotesContent.add(note1);
        allNotesContent.add(note2);
        allNotesContent.add(note3);
        allNotesContent.add(note4);
        allNotesContent.add(note5);
        allNotesContent.add(note6);
        when(keywordRepository.getAll()).thenReturn(allKeywords);
        assertEquals(Risk.IN_DANGER, reportService.getDiabetesAssessmentLevel(patientSex, age, allNotesContent));
    }

    // CASE age >= 30 && keywords > 7, RETURN EARLY_ONSET

    @Test
    void getDiabetesAssessmentLevelAgeMoreThan30KeywordsMoreThan7SexMaleTest() {
        age = 35;
        patientSex = 'H';
        allNotesContent.add(note1);
        allNotesContent.add(note2);
        allNotesContent.add(note3);
        allNotesContent.add(note4);
        allNotesContent.add(note5);
        allNotesContent.add(note6);
        allNotesContent.add(note7);
        allNotesContent.add(note8);
        allNotesContent.add(note9);
        when(keywordRepository.getAll()).thenReturn(allKeywords);
        assertEquals(Risk.EARLY_ONSET, reportService.getDiabetesAssessmentLevel(patientSex, age, allNotesContent));
    }

    // CASE age < 30 && sex='H' && keywords <= 2, RETURN NONE

    @Test
    void getDiabetesAssessmentLevelAgeLessThan30KeywordsLessThan2SexMaleTest() {
        age = 25;
        patientSex = 'H';
        when(keywordRepository.getAll()).thenReturn(allKeywords);
        assertEquals(Risk.NONE, reportService.getDiabetesAssessmentLevel(patientSex, age, allNotesContent));
    }

    // CASE age < 30 && sex='H' && keywords <= 4, RETURN IN_DANGER

    @Test
    void getDiabetesAssessmentLevelAgeLessThan30KeywordsLessThan4SexMaleTest() {
        age = 25;
        patientSex = 'H';
        allNotesContent.add(note1);
        allNotesContent.add(note2);
        allNotesContent.add(note3);
        when(keywordRepository.getAll()).thenReturn(allKeywords);
        assertEquals(Risk.IN_DANGER, reportService.getDiabetesAssessmentLevel(patientSex, age, allNotesContent));
    }

    // CASE age < 30 && sex='H' && keywords > 4, RETURN EARLY_ONSET

    @Test
    void getDiabetesAssessmentLevelAgeLessThan30KeywordsMoreThan4SexMaleTest() {
        age = 25;
        patientSex = 'H';
        allNotesContent.add(note1);
        allNotesContent.add(note2);
        allNotesContent.add(note3);
        allNotesContent.add(note4);
        allNotesContent.add(note5);
        allNotesContent.add(note6);
        when(keywordRepository.getAll()).thenReturn(allKeywords);
        assertEquals(Risk.EARLY_ONSET, reportService.getDiabetesAssessmentLevel(patientSex, age, allNotesContent));
    }

    // CASE age < 30 && sex='F' && keywords <= 3, RETURN NONE

    @Test
    void getDiabetesAssessmentLevelAgeLessThan30KeywordsLessThan3SexFemaleTest() {
        age = 25;
        patientSex = 'F';
        when(keywordRepository.getAll()).thenReturn(allKeywords);
        assertEquals(Risk.NONE, reportService.getDiabetesAssessmentLevel(patientSex, age, allNotesContent));
    }

    // CASE age < 30 && sex='F' && keywords <= 6, RETURN IN_DANGER

    @Test
    void getDiabetesAssessmentLevelAgeLessThan30KeywordsLessThan6SexFemaleTest() {
        age = 25;
        patientSex = 'F';
        allNotesContent.add(note1);
        allNotesContent.add(note2);
        allNotesContent.add(note3);
        allNotesContent.add(note4);
        allNotesContent.add(note5);
        when(keywordRepository.getAll()).thenReturn(allKeywords);
        assertEquals(Risk.IN_DANGER, reportService.getDiabetesAssessmentLevel(patientSex, age, allNotesContent));
    }

    // CASE age < 30 && sex='F' && keywords > 6, RETURN EARLY_ONSET

    @Test
    void getDiabetesAssessmentLevelAgeLessThan30KeywordsMoreThan6SexFemaleTest() {
        age = 25;
        patientSex = 'F';
        allNotesContent.add(note1);
        allNotesContent.add(note2);
        allNotesContent.add(note3);
        allNotesContent.add(note4);
        allNotesContent.add(note5);
        allNotesContent.add(note6);
        allNotesContent.add(note7);
        when(keywordRepository.getAll()).thenReturn(allKeywords);
        assertEquals(Risk.EARLY_ONSET, reportService.getDiabetesAssessmentLevel(patientSex, age, allNotesContent));
    }



}
