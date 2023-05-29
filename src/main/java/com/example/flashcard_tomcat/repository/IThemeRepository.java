package com.example.flashcard_tomcat.repository;

import com.example.flashcard_tomcat.model.Theme;

import java.util.List;

public interface IThemeRepository {
    boolean existsById(long themeId);
    List<Theme> getAllTheme();
    List<Theme> findThemeById(long idTheme);

    void save(String title);

    boolean remove(long idTheme);
}
