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

