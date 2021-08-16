package com.openclassrooms.mediscreen.reportmicroservice.repository;

import com.openclassrooms.mediscreen.reportmicroservice.model.Keyword;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class KeywordFileRepositoryImpl implements KeywordRepository {
    /**
     * @see Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(KeywordFileRepositoryImpl.class);
    /**
     * Filepath of keywords file.
     */
    private String filepath;

    /**
     * Constructor.
     */
    public KeywordFileRepositoryImpl() {
        filepath="src/main/resources/keywords.txt";
    }

    /**
     * @inheritDoc
     */
    @Override
    public List<Keyword> getAll() {
        LOGGER.info("Get all  keywords in file.");
        List<Keyword> keywordList = new ArrayList<>();

        try (FileReader fileReader = new FileReader(filepath)) {
            BufferedReader reader = new BufferedReader(fileReader);
            String line = reader.readLine();
            while (line != null) {
                keywordList.add(new Keyword(line));
                line = reader.readLine();
            }
        } catch (IOException e) {
            LOGGER.error("IOException", e);
        }
        return keywordList;    }

    /**
     * Getter filepath.
     * @return filepath
     */
    public String getFilepath() {
        return filepath;
    }

    /**
     * Setter filepath.
     * @param filepath1 to set
     */
    public void setFilepath(final String filepath1) {
        filepath = filepath1;
    }
}
