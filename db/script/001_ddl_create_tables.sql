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

create table users (
	id serial primary key,
	name varchar(50) not null,
	email varchar(120) unique not null,
	password varchar(120) not null
);

create table user_roles (
	user_id int not null,
	role_id int not null,
	primary key (user_id, role_id),
	foreign key (user_id) references users(id) on delete cascade,
	foreign key (role_id) references roles(id) on delete cascade
)
