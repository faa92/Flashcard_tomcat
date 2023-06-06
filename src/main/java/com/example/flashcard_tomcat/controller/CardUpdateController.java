package com.example.flashcard_tomcat.controller;

import com.example.flashcard_tomcat.model.Card;
import com.example.flashcard_tomcat.service.CardService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/update")
public class CardUpdateController extends HttpServlet {
    private CardService cardService;

    @Override
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        cardService = (CardService) context.getAttribute("cardService");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long cardId = Long.parseLong(req.getParameter("cardId"));
        boolean learned = Boolean.parseBoolean(req.getParameter("learned"));
        Card updateCard = cardService.updateLearned(cardId, learned);
        resp.sendRedirect(req.getContextPath() + CardController.PATH + "?themeId" + updateCard.getThemeId());
    }
}
