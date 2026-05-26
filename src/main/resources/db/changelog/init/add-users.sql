--liquibase formatted sql
--changeset sdbrother0:add-users
CREATE TABLE users
(
    id       bigserial primary key,
    username varchar not null unique,
    password varchar not null
);