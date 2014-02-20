--<ScriptOptions statementTerminator=";"/>

CREATE TABLE sample_submission (
		id VARCHAR(2147483647) NOT NULL,
		pred INT4
	);

CREATE UNIQUE INDEX pk_sample_submission ON sample_submission (id ASC);

ALTER TABLE sample_submission ADD CONSTRAINT pk_sample_submission PRIMARY KEY (id);

