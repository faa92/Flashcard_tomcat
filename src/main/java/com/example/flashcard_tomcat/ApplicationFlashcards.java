package com.example.flashcard_tomcat;

import com.example.flashcard_tomcat.model.Card;
import com.example.flashcard_tomcat.model.Theme;
import com.example.flashcard_tomcat.repository.CardJdbcRepository;
import com.example.flashcard_tomcat.repository.ThemeJdbcRepository;
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

            List<Theme> allThemes = themeJdbcRepository.getAllTheme();
            List<Card> allCardsByTheme = cardJdbcRepository.findAllCardsByTheme(1);


//        themeJdbcRepository.save("History");
//        themeJdbcRepository.remove(4);

//        cardJdbcRepository.save(2, "FWFWFWF", "12345", true);
//        cardJdbcRepository.remove(17);
//        cardJdbcRepository.updateIsLearned(1, true);

            System.out.println(allThemes);
            System.out.println(allCardsByTheme);
        }
    }
}
