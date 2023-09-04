DELETE FROM COUPLE_MATCH;
DELETE FROM Match;
DELETE FROM couple;
DELETE FROM husband;
DELETE FROM wife;
DELETE FROM team;

INSERT INTO team (name) VALUES
('Barcelona'),
('Bayer Munchen'),
('Ferencvaros'),
('Barsa'),
('Shakhtar'),
('Real Madrid');
DELETE FROM husband;
INSERT INTO husband (name, consumedBear) VALUES
('Janos', 0),
('Ferenc', 0),
('Aladar', 0),
('Janos', 0),
('Andras', 0),
('Nandor', 0),
('Andras', 0),
('Peter', 0);
DELETE FROM wife;
INSERT INTO wife (name, spareTime) VALUES
('Anna', 0),
('Helga', 0),
('Sara', 0),
('Eszter', 0),
('Emma', 0),
('Szilvia', 0),
('Kata', 0),
('Maria', 0);
