package com.example.flashcard_tomcat.service;import com.example.flashcard_tomcat.model.Theme;import java.util.List;public interface ThemeService {    List<Theme> getAll();    void create(String title);    void remove(long themeId);}