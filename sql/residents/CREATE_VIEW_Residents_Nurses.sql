CREATE VIEW residents_nurses AS (

SELECT r.*, n.firstname as nurse_firstname, n.lastname as nurse_lastname, n.iscert, n.assignments FROM residents as r LEFT JOIN (SELECT nurses.firstname, nurses.lastname, nurses.iscert, nurses.assignments, nurses.id FROM nurses) n ON r.nurseid = n.id


)