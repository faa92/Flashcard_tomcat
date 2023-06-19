package com.example.flashcard_tomcat.controller;

import com.example.flashcard_tomcat.model.Theme;
import com.example.flashcard_tomcat.service.ThemeService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = ThemeController.PATH)
public class ThemeController extends HttpServlet {

    public static final String PATH = "/themes";
    private ThemeService themeService;

    @Override
    public void init() {
        ServletContext context = getServletContext();
        themeService = (ThemeService) context.getAttribute("themeService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Theme> themeList = themeService.getAll();

        request.setAttribute("themes", themeList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/themes.jsp");
        dispatcher.forward(request, response);
    }
}

