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
    PRIMARY KEY (id),
    INDEX (product_id)
) ENGINE InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_bin;

