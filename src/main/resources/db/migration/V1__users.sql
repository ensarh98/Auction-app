CREATE SCHEMA core;

ALTER SCHEMA core OWNER TO postgres;

CREATE TABLE core.users (
	id integer NOT NULL GENERATED ALWAYS AS IDENTITY ,
	first_name varchar(400) NOT NULL,
	last_name varchar(400) NOT NULL,
	password varchar(400) NOT NULL,
	email varchar(400) NOT NULL,
	address varchar(4000) NULL,
	phone varchar(40) NULL,
	photo varchar(4000) NULL,
	city varchar(4000) NULL,
	registration_date date NOT NULL,
	secret bytea NOT NULL,
	CONSTRAINT users_pk PRIMARY KEY (id),
	CONSTRAINT email_uq UNIQUE (email)
);

ALTER TABLE core.users OWNER TO postgres;