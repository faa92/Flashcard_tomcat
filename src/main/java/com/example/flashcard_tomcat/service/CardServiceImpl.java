package com.example.flashcard_tomcat.service;

import com.example.flashcard_tomcat.model.Card;

import java.util.List;

public class CardServiceImpl implements ICardService{
    @Override
    public List<Card> getAllByTheme(long themeId) {
        return null;
    }

    @Override
    public void save(long themeId, String title) {

    }

    @Override
    public void updateLearned(long cardId, boolean learned) {

    }

    @Override
    public void remove(long cardId) {

    }
}
