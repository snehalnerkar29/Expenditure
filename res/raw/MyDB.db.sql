BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS `INCOME` (
	`DATE`	TEXT,
	`TIME`	TEXT,
	`AMOUNT`	TEXT,
	`OCCUPATION`	TEXT,
	`PAYMENTMODE`	TEXT
);
CREATE TABLE IF NOT EXISTS `EXPENSE` (
	`DATE`	TEXT,
	`TIME`	TEXT,
	`AMOUNT`	TEXT,
	`THING`	TEXT,
	`PAYMENTMODE`	TEXT
);
COMMIT;