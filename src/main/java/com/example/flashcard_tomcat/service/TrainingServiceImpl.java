package com.example.flashcard_tomcat.service;

import com.example.flashcard_tomcat.exception.BusinessException;
import com.example.flashcard_tomcat.model.Card;
import com.example.flashcard_tomcat.repository.CardRepository;
import com.example.flashcard_tomcat.repository.ThemeRepository;

import java.util.Optional;

public class TrainingServiceImpl implements TrainingService {
    private final ThemeRepository themeRepository;
    private final CardRepository cardRepository;

    public TrainingServiceImpl(ThemeRepository themeRepository, CardRepository cardRepository) {
        this.themeRepository = themeRepository;
        this.cardRepository = cardRepository;
    }

    @Override
    public Card getFirst(long themeId) {
        return getNext(themeId, 0)
                .orElseThrow(() -> new BusinessException("Тема выучена"));
    }

    @Override
    public Optional<Card> getNext(long themeId, long previousCardId) {
        if (!themeRepository.existsById(themeId)) throw new BusinessException("Темы не существует");
        return cardRepository.findOneNotLearnedByThemeIdAndIdGreaterThen(themeId, previousCardId);
    }

    @Override
    public void markLearned(long cardId) {
        boolean exist = cardRepository.updateIsLearned(cardId, true);
        if (!exist) throw new BusinessException("Карточки не существует");
    }
}
