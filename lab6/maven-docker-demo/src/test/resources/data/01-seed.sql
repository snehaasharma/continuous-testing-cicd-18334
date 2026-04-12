-- PostgreSQL init script (docker-entrypoint-initdb.d) — version-controlled seed data.
CREATE TABLE IF NOT EXISTS users (
    id   TEXT PRIMARY KEY,
    name TEXT NOT NULL,
    email TEXT NOT NULL
);

DELETE FROM users WHERE id LIKE 'test_%';

INSERT INTO users (id, name, email) VALUES
    ('test_alice', 'Alice', 'alice_test@example.com'),
    ('test_bob',   'Bob',   'bob_test@example.com');
