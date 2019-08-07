CREATE TABLE products
(
    id          BIGINT AUTO_INCREMENT,
    name        VARCHAR(200)                        NOT NULL,
    description TEXT,
    category    VARCHAR(100)                        NOT NULL,
    price       DECIMAL(8, 2)                       NOT NULL,
    manufacture VARCHAR(200)                        NOT NULL,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (name)
);