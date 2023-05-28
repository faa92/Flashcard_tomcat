package com.example.flashcard_tomcat.repository;

import com.example.flashcard_tomcat.model.Theme;

import java.util.List;

public interface IThemeRepository {
    List<Theme> findThemeById(long idTheme);

    void save(String title);

    void remove(long idTheme);
}
