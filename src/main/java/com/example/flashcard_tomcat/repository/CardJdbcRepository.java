package com.example.flashcard_tomcat.repository;

import com.example.flashcard_tomcat.exception.RepositoryException;
import com.example.flashcard_tomcat.model.Card;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CardJdbcRepository implements CardRepository {
    private final DataSource db;

    public CardJdbcRepository(DataSource db) {
        this.db = db;
    }

    @Override
    public List<Card> findAllCardsByTheme(long idTheme) {
        String sql = """
                SELECT card_id             AS id,
                       theme_id            AS theme_id,
                       question            AS question,
                       answer              AS answer,
                       learned             AS learned
                FROM card
                WHERE card.theme_id = ?""";
        try (
                Connection connection = db.getConnection();
                PreparedStatement pStatement = connection.prepareStatement(sql);
        ) {
            pStatement.setLong(1, idTheme);

            ResultSet resultSet = pStatement.executeQuery();
            List<Card> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(new Card(
                        resultSet.getLong("id"),
                        resultSet.getString("question"),
                        resultSet.getString("answer"),
                        resultSet.getBoolean("learned")
                ));
            }
            return result;

        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public void save(long idTheme, String question, String answer, boolean learned) {
        String sql = """
                INSERT INTO card (theme_id, question, answer, learned)
                VALUES (?, ?, ?, ?)""";
        try (
                Connection connection = db.getConnection();
                PreparedStatement pStatement = connection.prepareStatement(sql);
        ) {
            pStatement.setLong(1, idTheme);
            pStatement.setString(2, question);
            pStatement.setString(3, answer);
            pStatement.setBoolean(4, learned);
            pStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RepositoryException(e);
        }

    }

    @Override
    public boolean updateIsLearned(long idCard, boolean learned) {
        String sql = """
                UPDATE card
                SET learned = ?
                WHERE card_id = ?""";
        try (
                Connection connection = db.getConnection();
                PreparedStatement pStatement = connection.prepareStatement(sql);
        ) {
            pStatement.setBoolean(1, learned);
            pStatement.setLong(2, idCard);
            pStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RepositoryException(e);
        }

        return learned;
    }

    @Override
    public boolean remove(long idCard) {
        String sql = """
                DELETE FROM  card
                WHERE card_id = ?""";
        try (
                Connection connection = db.getConnection();
                PreparedStatement pStatement = connection.prepareStatement(sql);
        ) {
            pStatement.setLong(1, idCard);
            pStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
        return false;
    }
}
