-- liquibase formatted sql

-- changeset Bence:1683317888417-1
CREATE TABLE student (id VARCHAR(255) NOT NULL, email VARCHAR(255), name VARCHAR(255), CONSTRAINT studentPK PRIMARY KEY (id));

