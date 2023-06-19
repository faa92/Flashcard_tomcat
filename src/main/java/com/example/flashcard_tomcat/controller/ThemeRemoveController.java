package com.example.flashcard_tomcat.controller;

import com.example.flashcard_tomcat.service.ThemeService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/remove-theme")
public class ThemeRemoveController extends HttpServlet {
    private ThemeService themeService;

    @Override
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        themeService = (ThemeService) context.getAttribute("themeService");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long themeId = Long.parseLong(req.getParameter("themeId"));
        themeService.remove(themeId);
        resp.sendRedirect(req.getContextPath() + ThemeController.PATH);
    }
}
