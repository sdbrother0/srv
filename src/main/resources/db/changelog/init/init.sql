--liquibase formatted sql
--changeset sdbrother0:init
CREATE TABLE product (
    id bigserial primary key,
    name varchar
)