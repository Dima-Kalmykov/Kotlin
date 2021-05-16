CREATE SCHEMA my_schema;
GRANT ALL ON SCHEMA my_schema TO docker_user;
ALTER USER docker_user SET search_path TO my_schema;

CREATE TABLE my_schema.students
(
    id   SERIAL PRIMARY KEY,
    grade INTEGER
);