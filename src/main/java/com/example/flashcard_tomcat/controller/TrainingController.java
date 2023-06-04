package com.example.flashcard_tomcat.controller;

import com.example.flashcard_tomcat.model.Card;
import com.example.flashcard_tomcat.service.TrainingService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

@WebServlet(urlPatterns = "/training")
public class TrainingController extends HttpServlet {
    private TrainingService trainingService;

    @Override
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        trainingService = (TrainingService) context.getAttribute("trainingService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long themeId = Long.parseLong(req.getParameter("themeId"));

        String previousCardIdStr = req.getParameter("previousCardId");
        if (previousCardIdStr == null) {
            Card card = trainingService.getFirst(themeId);
            sendCard(resp, card);

        } else {
            Optional<Card> cardOptional = trainingService.getNext(themeId, Long.parseLong(previousCardIdStr));
            if (cardOptional.isPresent()) {
                Card card = cardOptional.orElseThrow();
                sendCard(resp, card);
            } else {
                resp.sendRedirect(req.getContextPath() + "/themes");
            }
        }
    }

    private void sendCard(HttpServletResponse response, Card card) throws IOException {
        String respBody = card.toString();

        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        response.getWriter().println(respBody);
    }
}
