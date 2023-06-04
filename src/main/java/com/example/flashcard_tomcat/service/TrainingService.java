package com.example.flashcard_tomcat.service;

import com.example.flashcard_tomcat.model.Card;

import java.util.Optional;

public interface TrainingService {
    Card getFirst(long themeId);

    Optional<Card> getNext(long themeId, long previousCardId);

    void markLearned(long cardId);
}
