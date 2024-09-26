CREATE TABLE book (
   id SERIAL PRIMARY KEY,
   name character not null,
   author character,
   description text,
   price int
   category_id int references category(id);
);