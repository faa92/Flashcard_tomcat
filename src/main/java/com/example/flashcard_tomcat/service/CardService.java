package com.example.flashcard_tomcat.service;

import com.example.flashcard_tomcat.model.Card;

import java.util.List;

public interface CardService {
    List<Card> getAllByTheme(long themeId);

    void create(long themeId, String question, String answer);

    Card updateLearned(long cardId, boolean learned);

    Card remove(long cardId);
}
