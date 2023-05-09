CREATE TABLE core.items(
	id integer NOT NULL GENERATED ALWAYS AS IDENTITY,
	name varchar(400) NOT NULL,
	description varchar(4000),
	address varchar(400),
	photo varchar(4000),
	start_price double precision NOT NULL,
	start_date date NOT NULL,
	end_date date NOT NULL,
	subcategory_id integer NOT NULL,
	user_id integer NOT NULL,
	CONSTRAINT pk_item_id PRIMARY KEY (id)
);

ALTER TABLE core.items ADD CONSTRAINT fk_items_users FOREIGN KEY (user_id)
REFERENCES core.users (id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION NOT DEFERRABLE;

ALTER TABLE core.items ADD CONSTRAINT fk_items_subcategories FOREIGN KEY (subcategory_id)
REFERENCES core.subcategories (id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION NOT DEFERRABLE;