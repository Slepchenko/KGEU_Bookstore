CREATE TABLE category (
   id SERIAL PRIMARY KEY,
   name varchar(60) not null
);

CREATE TABLE book (
   id SERIAL PRIMARY KEY,
   name varchar(120) not null,
   author varchar(60),
   description text,
   price int,
   image_url varchar(40),
   category_id int not null references category(id)
);

CREATE TABLE book_purchase (
    id SERIAL PRIMARY KEY,
    name varchar(120) not null,
 	created timestamp,
	book_id int not null references book(id)
);

CREATE TABLE feedback (
    id SERIAL PRIMARY KEY,
    name varchar(120) not null,
 	phone int not null,
 	email varchar(60) not null,
 	note text
);