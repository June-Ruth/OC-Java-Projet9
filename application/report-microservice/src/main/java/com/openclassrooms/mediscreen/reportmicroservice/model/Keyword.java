package com.openclassrooms.mediscreen.reportmicroservice.model;

import java.util.Objects;

public class Keyword implements Comparable<Keyword> {
    /**
     * Keyword searched in notes' content.
     */
    private String keyword;

    /**
     * Public constructor.
     * @param keyword1 .
     */
    public Keyword(final String keyword1) {
        keyword = keyword1.toLowerCase();
    }

    /**
     * Getter keyword.
     * @return keyword
     */
    public String getKeyword() {
        return  keyword;
    }

    /**
     * Setter keyword.
     * @param keyword1 to set
     */
    public void setKeyword(final String keyword1) {
        keyword = keyword1.toLowerCase();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Keyword symptom = (Keyword) o;
        return keyword.equals(symptom.keyword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(keyword);
    }

    @Override
    public int compareTo(Keyword keyword1) {
        return keyword.compareTo(keyword1.keyword);
    }

    @Override
    public String toString(){
        return keyword;
    }
}
