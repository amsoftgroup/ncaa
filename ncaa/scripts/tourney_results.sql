--<ScriptOptions statementTerminator=";"/>

CREATE TABLE tourney_results (
		season VARCHAR(2147483647) NOT NULL,
		daynum INT4 NOT NULL,
		wteam INT4 NOT NULL,
		wscore INT4,
		lteam INT4,
		lscore INT4,
		numot VARCHAR(2147483647)
	);

CREATE UNIQUE INDEX pk_tourney_results ON tourney_results (season ASC, daynum ASC, wteam ASC);

ALTER TABLE tourney_results ADD CONSTRAINT pk_tourney_results PRIMARY KEY (season, daynum, wteam);

