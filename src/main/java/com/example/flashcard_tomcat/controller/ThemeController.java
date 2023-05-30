package com.example.flashcard_tomcat.controller;

import com.example.flashcard_tomcat.model.Theme;
import com.example.flashcard_tomcat.service.IThemeService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = "/themes")
public class ThemeController extends HttpServlet {
    private IThemeService themeService;

    @Override
    public void init() {
        ServletContext context = getServletContext();
        themeService = (IThemeService) context.getAttribute("themeService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Theme> themes = themeService.getAll();
        String responseBody = themes.isEmpty() ? "Нет тем" : themes.stream()
                .map(theme -> "%3s %-20s %2s / %-2s".formatted(
                        theme.getIdTheme(),
                        theme.getTitle(),
                        theme.getLearnedCardsCount(),
                        theme.getTotalCardsCount()
                ))
                .collect(Collectors.joining("\n"));

        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println(responseBody);
    }
}
