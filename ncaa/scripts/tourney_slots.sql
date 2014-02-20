--<ScriptOptions statementTerminator=";"/>

CREATE TABLE tourney_slots (
		season VARCHAR(2147483647) NOT NULL,
		slot VARCHAR(2147483647) NOT NULL,
		strongseed VARCHAR(2147483647) NOT NULL,
		weakseed VARCHAR(2147483647) NOT NULL
	);

CREATE UNIQUE INDEX pk_tourney_slots ON tourney_slots (season ASC, slot ASC, strongseed ASC, weakseed ASC);

ALTER TABLE tourney_slots ADD CONSTRAINT pk_tourney_slots PRIMARY KEY (season, slot, strongseed, weakseed);

