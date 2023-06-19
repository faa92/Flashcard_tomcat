package com.example.flashcard_tomcat;

import com.example.flashcard_tomcat.repository.CardJdbcRepository;
import com.example.flashcard_tomcat.repository.CardRepository;
import com.example.flashcard_tomcat.repository.ThemeJdbcRepository;
import com.example.flashcard_tomcat.repository.ThemeRepository;
import com.example.flashcard_tomcat.service.*;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class FlashcardsWeb implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(System.getenv("FLASH_CARDS_URL"));
        hikariConfig.setUsername(System.getenv("FLASH_CARDS_USER"));
        hikariConfig.setPassword(System.getenv("FLASH_CARDS_PASSWORD"));
        hikariConfig.setDriverClassName("org.postgresql.Driver");
        HikariDataSource dataSource = new HikariDataSource(hikariConfig);

        ThemeRepository themeRepository = new ThemeJdbcRepository(dataSource);
        CardRepository cardRepository = new CardJdbcRepository(dataSource);

        ThemeService themeService = new ThemeServiceImpl(themeRepository);
        CardService cardService = new CardServiceImpl(themeRepository, cardRepository);
        TrainingService trainingService = new TrainingServiceImpl(themeRepository, cardRepository);

        ServletContext context = event.getServletContext();
        context.setAttribute("dataSource", dataSource);
        context.setAttribute("themeService", themeService);
        context.setAttribute("cardService", cardService);
        context.setAttribute("trainingService", trainingService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        ServletContext context = event.getServletContext();
        HikariDataSource dataSource = (HikariDataSource) context.getAttribute("dataSource");
        dataSource.close();
    }
}