CREATE VIEW residents_medication AS

SELECT r.*, me.name as medname, me.lethaldosage as maximumdosage 
FROM residents as r LEFT JOIN (SELECT * FROM medications) as me
ON r.ailment = me.ailment WHERE r.ailment = me.ailment;
