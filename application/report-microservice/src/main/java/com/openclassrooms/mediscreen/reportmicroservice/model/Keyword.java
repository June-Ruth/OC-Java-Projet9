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

    /**
     * @inheritDoc
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Keyword keyword1 = (Keyword) o;
        return keyword.equals(keyword1.keyword);
    }

    /**
     * @inheritDoc
     */
    @Override
    public int hashCode() {
        return Objects.hash(keyword);
    }

    /**
     * @inheritDoc
     */
    @Override
    public int compareTo(final Keyword keyword1) {
        return keyword.compareTo(keyword1.keyword);
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString() {
        return keyword;
    }
}
