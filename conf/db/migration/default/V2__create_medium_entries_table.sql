CREATE TABLE medium_articles (
  url VARCHAR(767) CHARACTER SET latin1 PRIMARY KEY,
  medium_id INTEGER NOT NULL,
  title VARCHAR(256) NOT NULL,
  description TEXT NOT NULL,
  published_at DATETIME NOT NULL,
  retrieved_at DATETIME NOT NULL
);

CREATE INDEX medium_articles_media_id_idx ON medium_articles(medium_id);
