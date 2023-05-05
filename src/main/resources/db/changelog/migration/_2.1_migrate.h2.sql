-- liquibase formatted sql

-- changeset Bence:1683319006607-1
CREATE TABLE student (id VARCHAR(255) NOT NULL, email VARCHAR(255) NOT NULL, name VARCHAR(255) NOT NULL, CONSTRAINT studentPK PRIMARY KEY (id));

