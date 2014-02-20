--<ScriptOptions statementTerminator=";"/>

CREATE TABLE teams (
		id INT4 NOT NULL,
		name VARCHAR(2147483647)
	);

CREATE UNIQUE INDEX pk_teams ON teams (id ASC);

ALTER TABLE teams ADD CONSTRAINT pk_teams PRIMARY KEY (id);

