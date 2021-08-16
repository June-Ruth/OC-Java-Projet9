package com.openclassrooms.mediscreen.reportmicroservice.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
public class KeywordsRepositoryTest {

    private KeywordFileRepositoryImpl keywordRepository;

    @BeforeEach
    void beforeEach() {
        keywordRepository = new KeywordFileRepositoryImpl();
    }

    @Test
    void getAllTest() {
        keywordRepository.setFilepath("src/main/resources/keywords_test.txt");
        assertEquals(4, keywordRepository.getAll().size());
    }

}
