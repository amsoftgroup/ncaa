--<ScriptOptions statementTerminator=";"/>

CREATE TABLE seasons (
		season VARCHAR(2147483647) NOT NULL,
		years VARCHAR(2147483647) NOT NULL,
		dayzero DATE NOT NULL,
		regionW VARCHAR(2147483647) NOT NULL,
		regionX VARCHAR(2147483647) NOT NULL,
		regionY VARCHAR(2147483647) NOT NULL,
		regionZ VARCHAR(2147483647) NOT NULL
	);

CREATE UNIQUE INDEX pk_seasons ON seasons (season ASC, years ASC, dayzero ASC);

ALTER TABLE seasons ADD CONSTRAINT pk_seasons PRIMARY KEY (season, years, dayzero, regionW, regionX, regionY, regionZ);

