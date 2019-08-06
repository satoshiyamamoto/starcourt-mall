CREATE TABLE stocks
(
    id          BIGINT AUTO_INCREMENT,
    product_id  BIGINT                              NOT NULL,
    receiving   INT       DEFAULT 0                 NOT NULL,
    shipping    INT       DEFAULT 0                 NOT NULL,
    description TEXT,
    supplier    VARCHAR(200),
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    PRIMARY KEY (id)
);

CREATE INDEX product_id ON stocks (product_id);
