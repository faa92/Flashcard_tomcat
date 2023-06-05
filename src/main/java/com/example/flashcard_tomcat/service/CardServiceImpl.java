package com.example.flashcard_tomcat.service;

import com.example.flashcard_tomcat.exception.BusinessException;
import com.example.flashcard_tomcat.model.Card;
import com.example.flashcard_tomcat.repository.CardRepository;
import com.example.flashcard_tomcat.repository.ThemeRepository;

import java.util.List;

public class CardServiceImpl implements CardService {
    private static final boolean DEFAULT_IS_LEARNED = false;
    private final ThemeRepository themeRepository;
    private final CardRepository cardRepository;

    public CardServiceImpl(ThemeRepository themeRepository, CardRepository cardRepository) {
        this.themeRepository = themeRepository;
        this.cardRepository = cardRepository;
    }

    @Override
    public List<Card> getAllByTheme(long themeId) {
        validateThemeExist(themeId);
        return cardRepository.findAllCardsByTheme(themeId);
    }

    @Override
    public void create(long themeId, String question, String answer) {
        if (question.isEmpty() || answer.isEmpty()) throw new BusinessException("Вопрос/Ответ не заполненны");
        else {
            validateThemeExist(themeId);
            cardRepository.save(themeId, question, answer, DEFAULT_IS_LEARNED);
        }
    }

    @Override
    public Card updateLearned(long cardId, boolean learned) {
        boolean existed = cardRepository.updateIsLearned(cardId, learned);
        if (!existed) {
            throw new BusinessException("Карты не существует");
        }
        return null;
    }

    @Override
    public Card remove(long cardId) {
        boolean existed = cardRepository.remove(cardId);
        if (!existed) {
            throw new BusinessException("Карты не существует");
        }
        return null;
    }

    private void validateThemeExist(long themeId) {
        if (!themeRepository.existsById(themeId)) {
            throw new BusinessException("Темы не существует");
        }
    }
}
