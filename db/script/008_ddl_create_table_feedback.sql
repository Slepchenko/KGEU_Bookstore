CREATE TABLE feedback (
    id SERIAL PRIMARY KEY,
    name character not null,
 	phone int,
 	email character not null,
 	communicate_id int references communicate(id)
);