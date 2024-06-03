CREATE TABLE IF NOT EXISTS stats
(
    id        INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    app       VARCHAR(250)                NOT NULL,
    uri       VARCHAR(250)                NOT NULL,
    ip        VARCHAR(15)                NOT NULL,
    timestamp TIMESTAMP WITHOUT TIME ZONE NOT NULL
);

INSERT INTO stats (app, uri, ip, timestamp) VALUES
-- один эндпоинт - разные даты, один айпи
('app', '/my-api/endpoint', '192.168.1.1', '2024-01-08 12:00:00'),
('app', '/my-api/endpoint', '192.168.1.1', '2024-01-09 12:00:00'),
('app', '/my-api/endpoint', '192.168.1.1', '2024-02-10 12:00:00'),
('app', '/my-api/endpoint', '192.168.1.1', '2024-02-11 12:00:00'),
('app', '/my-api/endpoint', '192.168.1.1', '2024-03-12 12:00:00'),
-- один эндпоинт - разные айпи адреса, одинаковые даты
('app', '/my-api/endpoint', '192.168.1.2', CURRENT_TIMESTAMP),
('app', '/my-api/endpoint', '192.168.1.3', CURRENT_TIMESTAMP),
('app', '/my-api/endpoint', '192.168.1.4', CURRENT_TIMESTAMP),
('app', '/my-api/endpoint', '192.168.1.5', CURRENT_TIMESTAMP),
-- один эндпоинт - разные даты и адреса
('app', '/my-api/endpoint', '192.168.1.1', '2024-03-08 12:00:00'),
('app', '/my-api/endpoint', '192.168.1.2', '2024-03-09 12:00:00'),
('app', '/my-api/endpoint', '192.168.1.3', '2024-03-10 12:00:00'),
('app', '/my-api/endpoint', '192.168.1.4', '2024-03-11 12:00:00'),
('app', '/my-api/endpoint', '192.168.1.5', '2024-03-12 12:00:00'),
-- другие эндпоинты
('app', '/create', '192.168.1.1', CURRENT_TIMESTAMP),
('app', '/about', '192.168.1.2', CURRENT_TIMESTAMP),
('app', '/signup', '192.168.1.6', CURRENT_TIMESTAMP);