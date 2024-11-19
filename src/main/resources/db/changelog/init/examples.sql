--liquibase formatted sql
--changeset sdbrother0:examples
INSERT INTO product(name, price) VALUES('Product 1', '100');
INSERT INTO product(name, price) VALUES('Product 2', '150');
INSERT INTO product(name, price) VALUES('Product 3', '400');

INSERT INTO customer(email, first_name, last_name) VALUES('cust1@cust.com', 'Cust1', 'Cust');
INSERT INTO customer(email, first_name, last_name) VALUES('cust2@cust.com', 'Cust2', 'Cust');

INSERT INTO invoice(created, customer_id) VALUES(now(), 1);
INSERT INTO invoice_product_details(invoice_id, product_id, quantity, price, tax) VALUES (1, 1, 1, 100, 20);
INSERT INTO invoice_product_details(invoice_id, product_id, quantity, price, tax) VALUES (1, 2, 2, 150, 30);
