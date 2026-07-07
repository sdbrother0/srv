--liquibase formatted sql
--changeset sdbrother0:add-category
CREATE TABLE category
(
    id        bigserial primary key,
    name      varchar,
    parent_id bigint references category (id)
);

alter table product add column category bigint references category (id);

-- ids are assigned by the identity sequence in insertion order (1..7 on the fresh table),
-- so parent_id references the values generated just above and the sequence stays in sync
INSERT INTO category(name, parent_id) VALUES ('Folder_01', null); -- 1
INSERT INTO category(name, parent_id) VALUES ('Folder_02', 1);       -- 2
INSERT INTO category(name, parent_id) VALUES ('Folder_03', 2);      -- 3
INSERT INTO category(name, parent_id) VALUES ('Folder_04', 2);      -- 4
INSERT INTO category(name, parent_id) VALUES ('Folder_05', 1);       -- 5
INSERT INTO category(name, parent_id) VALUES ('Folder_06', null);      -- 6
INSERT INTO category(name, parent_id) VALUES ('Folder_07', 6);        -- 7

UPDATE product SET category = 3 WHERE id = 1;
UPDATE product SET category = 3 WHERE id = 2;
UPDATE product SET category = 5 WHERE id = 3;
