package com.example.flashcard_tomcat.controller;

import com.example.flashcard_tomcat.service.CardService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/create-card")
public class CardCreateController extends HttpServlet {
    private CardService cardService;

    @Override
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        cardService = (CardService) context.getAttribute("cardService");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long themeId = Long.parseLong(req.getParameter("themeId"));
        String question = req.getParameter("question");
        String answer = req.getParameter("answer");
        cardService.create(themeId, question, answer);
        resp.sendRedirect(req.getContextPath() + CardController.PATH + "?themeId=" + themeId);
    }
}
