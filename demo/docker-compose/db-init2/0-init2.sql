CREATE SCHEMA my_schema;
GRANT ALL ON SCHEMA my_schema TO admin;
ALTER USER admin SET search_path TO my_schema;

CREATE TABLE my_schema.students
(
    id   SERIAL PRIMARY KEY,
    grade INTEGER
);