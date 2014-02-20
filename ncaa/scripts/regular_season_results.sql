--<ScriptOptions statementTerminator=";"/>

CREATE TABLE regular_season_results (
		season VARCHAR(2147483647) NOT NULL,
		daynum INT4 DEFAULT (-1) NOT NULL,
		wteam INT4 DEFAULT (-1) NOT NULL,
		wscore INT4,
		lteam INT4,
		lscore INT4,
		wloc VARCHAR(2147483647),
		numot VARCHAR(2147483647)
	);

CREATE UNIQUE INDEX pk_regular_season_results ON regular_season_results (season ASC, daynum ASC, wteam ASC);

ALTER TABLE regular_season_results ADD CONSTRAINT pk_regular_season_results PRIMARY KEY (season, daynum, wteam);

