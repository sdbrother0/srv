--liquibase formatted sql
--changeset sdbrother0:init
CREATE TABLE product
(
    id    bigserial primary key,
    name  varchar,
    price numeric(18, 2)
);

CREATE TABLE customer
(
    id    bigserial primary key,
    name  varchar,
    first_name varchar,
    last_name varchar,
    street varchar,
    city varchar,
    state varchar,
    zip_code varchar,
    phone_number varchar
)