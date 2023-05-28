
-- DROP TABLE card;

CREATE TABLE theme
(
      theme_id    BIGSERIAL PRIMARY KEY,
      theme_title TEXT NOT NULL
);

CREATE TABLE card
(
      card_id  BIGSERIAL PRIMARY KEY,
      theme_id BIGINT  NOT NULL REFERENCES theme ON DELETE CASCADE,
      question TEXT    NOT NULL,
      answer   TEXT    NOT NULL,
      learned  BOOLEAN NOT NULL
);





