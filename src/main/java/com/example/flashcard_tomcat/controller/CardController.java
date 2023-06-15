package com.example.flashcard_tomcat.controller;

import com.example.flashcard_tomcat.model.Card;
import com.example.flashcard_tomcat.service.CardService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import static com.example.flashcard_tomcat.controller.CardController.PATH;

@WebServlet(urlPatterns = PATH)
public class CardController extends HttpServlet {

    public static final String PATH = "/cards";
    private CardService cardService;

    @Override
    public void init() {
        ServletContext context = getServletContext();
        cardService = (CardService) context.getAttribute("cardService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long themeId = Long.parseLong(request.getParameter("themeId"));

        List<Card> cardList = cardService.getAllByTheme(themeId);

        request.setAttribute("cards", cardList);
        request.setAttribute("themeId", themeId);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/cards.jsp");
        dispatcher.forward(request, response);
    }
}
