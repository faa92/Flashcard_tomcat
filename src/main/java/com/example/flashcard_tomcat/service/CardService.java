package com.example.flashcard_tomcat.service;

import com.example.flashcard_tomcat.model.Card;

import java.util.List;

public interface CardService {
    List<Card> getAllByTheme(long themeId);

    void save(long themeId, String question, String answer, boolean learned);

    void updateLearned(long cardId, boolean learned);

    void remove(long cardId);
}
