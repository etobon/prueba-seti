-- Tablas base
CREATE TABLE IF NOT EXISTS franchises (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS branches (
    id BIGINT NOT NULL AUTO_INCREMENT,
    franchise_id BIGINT NOT NULL,
    name VARCHAR(255),
    PRIMARY KEY (id),
    FOREIGN KEY (franchise_id) REFERENCES franchises(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS products (
    id BIGINT NOT NULL AUTO_INCREMENT,
    branch_id BIGINT NOT NULL,
    name VARCHAR(255),
    stock BIGINT,
    PRIMARY KEY (id),
    FOREIGN KEY (branch_id) REFERENCES branches(id) ON DELETE CASCADE
);
