DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS clients;

CREATE TABLE IF NOT EXISTS clients
(
    id           BIGSERIAL PRIMARY KEY,
    name         VARCHAR(255) NOT NULL,
    phone_number VARCHAR(20)  NOT NULL,
    address      VARCHAR(255) NOT NULL,
    email        VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS orders
(
    id           BIGSERIAL PRIMARY KEY,
    description  VARCHAR(255)   NOT NULL,
    amount       NUMERIC(10, 2) NOT NULL,
    order_date   TIMESTAMP      NOT NULL,
    order_status VARCHAR(20)    NOT NULL,
    client_id    BIGINT         NOT NULL,
    FOREIGN KEY (client_id) REFERENCES clients (id)
);

CREATE INDEX ON orders (client_id);