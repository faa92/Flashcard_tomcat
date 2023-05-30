package com.example.flashcard_tomcat.repository;

import com.example.flashcard_tomcat.model.Card;

import java.util.List;

public interface CardRepository {
    List<Card> findAllCardsByTheme(long idTheme);

    void save(long idTheme, String question, String answer, boolean learned);

    boolean updateIsLearned(long idCard, boolean learned);

    boolean remove(long idCard);
}
