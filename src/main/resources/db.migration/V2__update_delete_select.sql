INSERT INTO theme (theme_title)
VALUES ('ENGLISH');
INSERT INTO theme (theme_title)
VALUES ('MATHEMATICS');
INSERT INTO theme (theme_title)
VALUES ('JAVA');

INSERT INTO card (theme_id, question, answer, learned)
VALUES (1, 'RED', 'КРАСНЫЙ', false);
INSERT INTO card (theme_id, question, answer, learned)
VALUES (1, 'GREEN', 'ЗЕЛЁНЫЙ', false);
INSERT INTO card (theme_id, question, answer, learned)
VALUES (1, 'BLUE', 'СИНИЙ', false);
INSERT INTO card (theme_id, question, answer, learned)
VALUES (1, 'WHITE', 'БЕЛЫЙ', false);
INSERT INTO card (theme_id, question, answer, learned)
VALUES (1, 'BLACK', 'ЧЁРНЫЙ', false);

INSERT INTO card (theme_id, question, answer, learned)
VALUES (2, '1*1', '1', false);
INSERT INTO card (theme_id, question, answer, learned)
VALUES (2, '2*2', '4', false);
INSERT INTO card (theme_id, question, answer, learned)
VALUES (2, '3*3', '9', false);
INSERT INTO card (theme_id, question, answer, learned)
VALUES (2, '4*4', '16', false);
INSERT INTO card (theme_id, question, answer, learned)
VALUES (2, '5*5', '25', false);

INSERT INTO card (theme_id, question, answer, learned)
VALUES (3, '\s', 'ПРОБЕЛЬНЫЙ СИМВОЛ', false);
INSERT INTO card (theme_id, question, answer, learned)
VALUES (3, '^', 'НАЧАЛО СТРОКИ', false);
INSERT INTO card (theme_id, question, answer, learned)
VALUES (3, '$', 'КОНЕЦ СТРОКИ', false);
INSERT INTO card (theme_id, question, answer, learned)
VALUES (3, '\b', 'ГРАНИЦА СЛОВА', false);
INSERT INTO card (theme_id, question, answer, learned)
VALUES (3, '\.', 'ЭКРАНИРОВАНИЕ СПЕЦ.СИМВОЛОВ', false);

UPDATE card
SET question = 'YELLOW'
WHERE card_id = 1;

UPDATE card
SET answer = 'ЖЁЛТЫЙ'
WHERE card_id = 1;

UPDATE theme
SET theme_title = 'MATH'
WHERE theme_title = 'ENGLISH';

UPDATE theme
SET theme_title = 'ENGLISH'
WHERE theme_title = 'MATH';

UPDATE card
SET learned = true
WHERE card_id = 3;

UPDATE card
SET learned = true
WHERE card_id = 5;

UPDATE card
SET learned = false
WHERE card_id IN (1, 3, 5, 7, 12);

UPDATE card
SET learned = true
WHERE card_id = 3
   OR card_id = 2;

DELETE
FROM card
WHERE card_id > 2;

DELETE
FROM card
WHERE question = 'GREEN'
  AND answer = 'ЗЕЛЁНЫЙ';

DELETE
FROM card
WHERE theme_id = 2
  AND question = '4*4';

DELETE
FROM card
WHERE card_id BETWEEN 10 AND 14;

SELECT theme, theme_title
FROM theme;

SELECT card_id, theme_id, question, answer, learned
FROM card;

SELECT card.card_id  AS id,
       card.question AS question,
       card.answer   AS answer,
       card.learned  AS learned
FROM card
WHERE theme_id = 2
  AND NOT card.learned
ORDER BY card_id LIMIT 1
OFFSET 3;

SELECT card.card_id  AS id,
       card.question AS question,
       card.answer   AS answer,
       card.learned  AS learned
FROM card
WHERE card.theme_id = 2
  AND NOT card.learned
  AND card_id > 6
ORDER BY card_id LIMIT 2;

SELECT theme.id          AS id,
       theme.theme_title AS title,
       count(card_id)       FILTER ( WHERE card.learned )  AS learned_cards_count, count(card_id) AS total_cards_count
FROM theme
            LEFT JOIN card ON theme.id = card.theme_id
GROUP BY theme.id;