--<ScriptOptions statementTerminator=";"/>

CREATE TABLE tourney_seeds (
		season VARCHAR(2147483647) NOT NULL,
		seed VARCHAR(2147483647) NOT NULL,
		team INT4 NOT NULL
	);

CREATE UNIQUE INDEX pk_tourney_seeds ON tourney_seeds (season ASC, seed ASC, team ASC);

ALTER TABLE tourney_seeds ADD CONSTRAINT pk_tourney_seeds PRIMARY KEY (season, seed, team);

