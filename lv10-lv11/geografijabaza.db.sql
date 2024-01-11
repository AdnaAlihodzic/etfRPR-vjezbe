BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "grad" (
	"id"	INTEGER,
	"naziv"	TEXT,
	"broj_stanovnika"	INTEGER,
	"drzava"	int,
	PRIMARY KEY("id"),
	FOREIGN KEY("drzava") REFERENCES "drzava"("id")
);
CREATE TABLE IF NOT EXISTS "drzava" (
	"id"	INTEGER,
	"naziv"	TEXT,
	"glavni_grad"	int,
	PRIMARY KEY("id"),
	FOREIGN KEY("glavni_grad") REFERENCES "grad"("id")
);
INSERT INTO "grad" VALUES (1,'Pariz',10000,1);
INSERT INTO "grad" VALUES (2,'London',8000,2);
INSERT INTO "grad" VALUES (3,'Bec',7000,3);
INSERT INTO "grad" VALUES (4,'Manchester',5000,2);
INSERT INTO "grad" VALUES (5,'Graz',6000,3);
INSERT INTO "drzava" VALUES (1,'Francuska',1);
INSERT INTO "drzava" VALUES (2,'UK',2);
INSERT INTO "drzava" VALUES (3,'Austrija',3);
COMMIT;