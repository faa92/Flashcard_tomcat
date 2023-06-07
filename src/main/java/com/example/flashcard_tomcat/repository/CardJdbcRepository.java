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
import java.util.Optional;

public class CardJdbcRepository implements CardRepository {
    private final DataSource db;

    public CardJdbcRepository(DataSource db) {
        this.db = db;
    }

    @Override
    public Optional<Card> findOneNotLearnedByThemeIdAndIdGreaterThen(long themeId, long greaterThanId) {
        String sql = """
                SELECT card.id          AS id,
                       card.question    AS question,
                       card.answer      AS answer,
                       card.learned     AS learned
                FROM card
                WHERE card.theme_id = ?
                  AND NOT card.learned
                  AND card.id > ?
                ORDER BY card.id
                LIMIT 1
                """;
        try (
                Connection connection = db.getConnection();
                PreparedStatement pStatement = connection.prepareStatement(sql);
        ) {
            pStatement.setLong(1, themeId);
            pStatement.setLong(2, greaterThanId);

            ResultSet resultSet = pStatement.executeQuery();
            if (resultSet.next()) {
                Card card = new Card(
                        resultSet.getLong("id"),
                        themeId, resultSet.getString("question"),
                        resultSet.getString("answer"),
                        resultSet.getBoolean("learned")
                );
                return Optional.of(card);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
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
                        resultSet.getLong("themeId"),
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

    @Override
    public Optional<Object> findById(long cardId) {
        String sql = """
                SELECT card.id              AS id,
                       card.theme_id        AS theme_id,
                       card.learned         AS learned,
                       card.question        AS question,
                       card.answer          AS answer,
                       card.learned         AS learned
                FROM card
                WHERE card.id = ?""";
        try (
                Connection connection = db.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
        ) {
            statement.setLong(1, cardId);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(mapToItem(resultSet));
            } else {
                return Optional.empty();
            }

        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }
        return Optional.empty();
}

