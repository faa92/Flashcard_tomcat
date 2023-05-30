package com.example.flashcard_tomcat;

import com.example.flashcard_tomcat.repository.CardJdbcRepository;
import com.example.flashcard_tomcat.repository.ICardRepository;
import com.example.flashcard_tomcat.repository.IThemeRepository;
import com.example.flashcard_tomcat.repository.ThemeJdbcRepository;
import com.example.flashcard_tomcat.service.CardServiceImpl;
import com.example.flashcard_tomcat.service.ICardService;
import com.example.flashcard_tomcat.service.IThemeService;
import com.example.flashcard_tomcat.service.ThemeServiceImpl;
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
        hikariConfig.setJdbcUrl("FLASH_CARDS_URL");
        hikariConfig.setUsername("FLASH_CARDS_USER");
        hikariConfig.setPassword("FLASH_CARDS_PASSWORD");
        hikariConfig.setDriverClassName("org.postgresql.Driver");
        HikariDataSource dataSource = new HikariDataSource(hikariConfig);

        IThemeRepository themeRepository = new ThemeJdbcRepository(dataSource);
        ICardRepository cardRepository = new CardJdbcRepository(dataSource);

        IThemeService themeService = new ThemeServiceImpl(themeRepository);
        ICardService cardService = new CardServiceImpl(themeRepository, cardRepository);

        ServletContext context = event.getServletContext();
        context.setAttribute("dataSource", dataSource);
        context.setAttribute("themeService", themeService);
        context.setAttribute("cardService", cardService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        ServletContext context = event.getServletContext();
        HikariDataSource dataSource = (HikariDataSource) context.getAttribute("dataSource");
        dataSource.close();
    }
}