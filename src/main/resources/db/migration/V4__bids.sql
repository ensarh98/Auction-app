CREATE TABLE core.bids(
	id integer NOT NULL GENERATED ALWAYS AS IDENTITY,
	user_id integer NOT NULL,
	bid_price numeric,
	item_id integer NOT NULL,
	bids integer DEFAULT 0,
	purchased bool NOT NULL DEFAULT false,
	CONSTRAINT pk_bid_id PRIMARY KEY (id)
);

ALTER TABLE core.bids ADD CONSTRAINT fk_bids_items FOREIGN KEY (item_id)
REFERENCES core.items (id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION NOT DEFERRABLE;

ALTER TABLE core.bids ADD CONSTRAINT fk_bids_users FOREIGN KEY (user_id)
REFERENCES core.users (id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION NOT DEFERRABLE;