package com.example.flashcard_tomcat.model;

public class Card {
    private final long idCard;
    private final String question;
    private final String answer;
    private boolean learned;

    public Card(long idCard, String question, String answer, boolean learned) {
        this.idCard = idCard;
        this.question = question;
        this.answer = answer;
        this.learned = learned;
    }

    public long getIdCard() {
        return idCard;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public boolean isLearned() {
        return learned;
    }

    @Override
    public String toString() {
        return "  CARD" +
                " idCard = " + idCard +
                " Question = " + question +
                " Answer = " + answer +
                " Learned = " + learned + "\n";
    }
}
