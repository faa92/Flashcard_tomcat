package com.example.flashcard_tomcat.model;

public class Theme {
    private final long idTheme;
    private final String title;
    private final long totalCardsCount;
    private final long learnedCardsCount;

    public Theme(long idTheme, String title, long totalCardsCount, long learnedCardsCount) {
        this.idTheme = idTheme;
        this.title = title;
        this.totalCardsCount = totalCardsCount;
        this.learnedCardsCount = learnedCardsCount;
    }

    public long getIdTheme() {
        return idTheme;
    }

    public String getTitle() {
        return title;
    }

    public long getTotalCardsCount() {
        return totalCardsCount;
    }

    public long getDoneCardsCount() {
        return learnedCardsCount;
    }

    @Override
    public String toString() {
        return " Theme" +
                " idTheme = " + idTheme +
                " title = " + title +
                " totalCardsCount = " + totalCardsCount +
                " learnedCardsCount = " + learnedCardsCount + "\n";
    }
}
