CREATE SCHEMA IF NOT EXISTS ecommerce_vivo;

SET
search_path TO ecommerce_vivo;

CREATE TABLE IF NOT EXISTS  ecommerce_vivo."order"
(
	id uuid NOT NULL,
	user_id int4 NULL,
	status varchar(255) NULL,
	total_price float8 NULL,
	uuid_string varchar(255) NULL,
	id_product int4 NULL,
	CONSTRAINT order_pkey PRIMARY KEY (id)
);




CREATE TABLE IF NOT EXISTS ecommerce_vivo.orderitem (
	id bigserial NOT NULL PRIMARY KEY,
	price float8 NULL,
	amount int4 NULL,
	partial_amount float8 NULL,
	id_product int4 NULL,
	order_uuid_string varchar(255) NULL
)