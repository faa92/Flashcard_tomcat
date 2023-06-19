package com.example.flashcard_tomcat.service;

import com.example.flashcard_tomcat.exception.BusinessException;
import com.example.flashcard_tomcat.model.Theme;
import com.example.flashcard_tomcat.repository.ThemeRepository;

import java.util.List;

public class ThemeServiceImpl implements ThemeService {
    private final ThemeRepository themeRepository;

    public ThemeServiceImpl(ThemeRepository themeRepository) {
        this.themeRepository = themeRepository;
    }

    @Override
    public List<Theme> getAll() {
        return themeRepository.getAllTheme();
    }

    @Override
    public void create(String title) {
        if (title.isEmpty()) {
            throw new BusinessException("Нет названия темы");
        }
        themeRepository.save(title);
    }

    @Override
    public void remove(long themeId) {
        boolean existed = themeRepository.remove(themeId);
        if (!existed) {
            throw new BusinessException("Темы не существует");
        }

    }
}
