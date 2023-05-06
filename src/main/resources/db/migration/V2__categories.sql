CREATE TABLE core.categories(
	id integer NOT NULL GENERATED ALWAYS AS IDENTITY ,
	name varchar(50) NOT NULL,
	CONSTRAINT pk_category_id PRIMARY KEY (id)

);

CREATE TABLE core.subcategories(
	id integer NOT NULL GENERATED ALWAYS AS IDENTITY ,
	name varchar(50),
	category_id integer NOT NULL,
	CONSTRAINT pk_subcategory_id PRIMARY KEY (id)
);

ALTER TABLE core.subcategories ADD CONSTRAINT fk_subcategories_categories FOREIGN KEY (category_id)
REFERENCES core.categories (id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION NOT DEFERRABLE;

ALTER TABLE core.categories OWNER TO postgres;
ALTER TABLE core.subcategories OWNER TO postgres;