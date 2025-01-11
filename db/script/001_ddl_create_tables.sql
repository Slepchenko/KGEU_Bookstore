CREATE TABLE category (
   id SERIAL PRIMARY KEY,
   name varchar(128) not null
);

CREATE TABLE book (
   id SERIAL PRIMARY KEY,
   name varchar(256) not null,
   author varchar(128),
   description text,
   image_url varchar(128),
   price int,
   category_id int not null references category(id)
);

--CREATE TABLE book_purchase (
--    id SERIAL PRIMARY KEY,
--    name varchar(256) not null,
-- 	created timestamp,
--	book_id int not null references book(id)
--);

create table feedback (
    id SERIAL PRIMARY KEY,
    name varchar(128) not null,
 	phone varchar(60),
 	email varchar(128),
	feedback varchar(60) not null,
 	note text
);

create table shopping_cart (
	id SERIAL PRIMARY KEY,
	total_price int not null default 0
);

--create table users (
--	id serial primary key,
--	name varchar(120) not null,
--	second_name varchar (120),
--	surname varchar(120) not null,
--	sex boolean,
--	email varchar(120) unique not null,
--	password varchar(120) not null,
--	shopping_cart_id int unique,
--	FOREIGN KEY (shopping_cart_id) REFERENCES shopping_cart (id) ON DELETE SET NULL
--);

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    second_name VARCHAR(255),
    surname VARCHAR(255),
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    sex BOOLEAN NOT NULL,
    shopping_cart_id INT unique,
    CONSTRAINT fk_shopping_cart FOREIGN KEY (shopping_cart_id) REFERENCES shopping_cart(id)
);

create table cart_item (
	id SERIAL PRIMARY KEY,
	cart_id INT NOT NULL,
    book_id INT NOT NULL,
    quantity INT NOT NULL DEFAULT 1,
    price INT NOT NULL,
    FOREIGN KEY (cart_id) REFERENCES shopping_cart (id) ON DELETE CASCADE,
    FOREIGN KEY (book_id) REFERENCES book (id) ON DELETE CASCADE
);

create table book_purchase(
	id serial primary key,
	creation_date timestamp not null default current_timestamp,
	user_id int,
	paid int not null,
	all_books_purchase text,
	FOREIGN KEY (user_id) REFERENCES users(id)
)