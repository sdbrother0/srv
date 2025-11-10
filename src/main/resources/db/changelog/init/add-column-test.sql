--liquibase formatted sql
--changeset sdbrother0:add-test-sql
alter table product add column test varchar;