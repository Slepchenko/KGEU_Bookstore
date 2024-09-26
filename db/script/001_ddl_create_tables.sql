CREATE TABLE category (
   id SERIAL PRIMARY KEY,
   name character(60) not null
);

CREATE TABLE book (
   id SERIAL PRIMARY KEY,
   name character(60) not null,
   author character(60),
   description text,
   price int,
   category_id int not null references category(id)
);

CREATE TABLE book_purchase (
    id SERIAL PRIMARY KEY,
    name character(60) not null,
 	created timestamp,
	book_id int not null references book(id)
);

CREATE TABLE feedback (
    id SERIAL PRIMARY KEY,
    name character(120) not null,
 	phone int not null,
 	email character(60) not null,
 	note text
);

