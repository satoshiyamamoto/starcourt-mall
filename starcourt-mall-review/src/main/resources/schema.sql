CREATE TABLE reviews
(
    id         BIGINT AUTO_INCREMENT,
    product_id BIGINT                              NOT NULL,
    username   VARCHAR(200)                        NOT NULL,
    title      VARCHAR(200)                        NOT NULL,
    comment    TEXT                                NOT NULL,
    rating     INT       DEFAULT 3                 NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    PRIMARY KEY (id)
);

CREATE INDEX product_id ON reviews (product_id);

