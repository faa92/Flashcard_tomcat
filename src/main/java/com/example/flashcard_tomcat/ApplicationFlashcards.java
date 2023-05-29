package com.example.flashcard_tomcat;

import com.example.flashcard_tomcat.model.Card;
import com.example.flashcard_tomcat.model.Theme;
import com.example.flashcard_tomcat.repository.CardJdbcRepository;
import com.example.flashcard_tomcat.repository.ThemeJdbcRepository;
import com.example.flashcard_tomcat.service.CardServiceImpl;
import com.example.flashcard_tomcat.service.ThemeServiceImpl;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.util.List;

public class ApplicationFlashcards {

    public static void main(String[] args) {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(System.getenv("FLASH_CARDS_URL"));
        hikariConfig.setUsername(System.getenv("FLASH_CARDS_USER"));
        hikariConfig.setPassword(System.getenv("FLASH_CARDS_PASSWORD"));

        try (HikariDataSource hikariDataSource = new HikariDataSource(hikariConfig)) {

            ThemeJdbcRepository themeJdbcRepository = new ThemeJdbcRepository(hikariDataSource);
            CardJdbcRepository cardJdbcRepository = new CardJdbcRepository(hikariDataSource);

            ThemeServiceImpl themeService = new ThemeServiceImpl(themeJdbcRepository);
            CardServiceImpl cardService = new CardServiceImpl(themeJdbcRepository, cardJdbcRepository);

            List<Theme> allThemes = themeService.getAll();
            List<Card> allCardsByTheme = cardService.getAllByTheme(3);


            System.out.println(allThemes);
            System.out.println(allCardsByTheme);
        }
    }
}
