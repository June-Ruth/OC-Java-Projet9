package com.openclassrooms.mediscreen.reportmicroservice.repository;

import com.openclassrooms.mediscreen.reportmicroservice.model.Keyword;

import java.util.List;

public interface KeywordRepository {
    /**
     * Get All Keywords.
     * @return keyword list
     */
    List<Keyword> getAll();
}
