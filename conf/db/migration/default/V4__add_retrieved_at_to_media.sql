ALTER TABLE media ADD COLUMN retrieved_at DATETIME;
CREATE INDEX media_retrieved_at_idx ON media(retrieved_at);
