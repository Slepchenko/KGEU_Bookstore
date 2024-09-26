CREATE TABLE book_purchase (
    id SERIAL PRIMARY KEY,
    name character not null,
 	created timestamp,
	book_id int references book(id)
);