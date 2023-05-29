package com.example.flashcard_tomcat.service;

import com.example.flashcard_tomcat.exception.BusinessException;
import com.example.flashcard_tomcat.model.Card;
import com.example.flashcard_tomcat.repository.ICardRepository;
import com.example.flashcard_tomcat.repository.IThemeRepository;

import java.util.List;

public class CardServiceImpl implements ICardService{
    private static final boolean DEFAULT_IS_LEARNED = false;
    private final IThemeRepository themeRepository;
    private final ICardRepository cardRepository;

    public CardServiceImpl(IThemeRepository themeRepository, ICardRepository cardRepository) {
        this.themeRepository = themeRepository;
        this.cardRepository = cardRepository;
    }

    @Override
    public List<Card> getAllByTheme(long themeId) {
        validateThemeExist(themeId);
        return cardRepository.findAllCardsByTheme(themeId);
    }

    @Override
    public void save(long themeId, String question, String answer, boolean learned) {
        validateThemeExist(themeId);
        cardRepository.save(themeId, question, answer, DEFAULT_IS_LEARNED);
    }

    @Override
    public void updateLearned(long cardId, boolean learned) {
        boolean existed = cardRepository.updateIsLearned(cardId, learned);
        if (!existed) {
            throw new BusinessException("Карты не существует");
        }
    }

    @Override
    public void remove(long cardId) {
        boolean existed = cardRepository.remove(cardId);
        if (!existed) {
            throw new BusinessException("Карты не существует");
        }
    }

    private void validateThemeExist(long themeId) { //todo
        if (themeRepository.existsById(themeId)) {
            throw new BusinessException("Темы не существует");
        }
    }
}
