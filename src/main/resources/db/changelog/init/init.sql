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
    id           bigserial primary key,
    email        varchar,
    first_name   varchar,
    last_name    varchar,
    street       varchar,
    city         varchar,
    state        varchar,
    zip_code     varchar,
    phone_number varchar
);

CREATE TABLE invoice
(
    id          bigserial primary key,
    created     timestamp without time zone,
    customer_id bigint references customer (id)
);

CREATE TABLE invoice_product_details
(
    id         bigserial primary key,
    invoice_id bigint references invoice (id) on delete cascade,
    product_id bigint references product (id),
    quantity   integer,
    price      numeric(18, 2),
    tax        numeric(18, 2)
);


