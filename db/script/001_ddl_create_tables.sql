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

CREATE TABLE book_purchase (
    id SERIAL PRIMARY KEY,
    name varchar(256) not null,
 	created timestamp,
	book_id int not null references book(id)
);

CREATE TABLE feedback (
    id SERIAL PRIMARY KEY,
    name varchar(128) not null,
 	phone int not null,
 	email varchar(128) not null,
 	note text
);

create table shopping_cart (
	id SERIAL PRIMARY KEY,
	total_price int not null default 0
);

create table users (
	id serial primary key,
	name varchar(50) not null,
	email varchar(120) unique not null,
	password varchar(120) not null,
	shopping_cart_id int unique,
	FOREIGN KEY (shopping_cart_id) REFERENCES shopping_cart (id) ON DELETE SET NULL
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
