CREATE TEMP TABLE list_id
(
    id int
);
WITH id_loc(var) as (values (9))
INSERT INTO list_id(id)
SELECT books.id
FROM books
         JOIN documents
              ON books.location_id = (SELECT * FROM id_loc)
UNION
SELECT documents.id
FROM documents
         JOIN magazines
              ON documents.location_id = (SELECT * FROM id_loc)
UNION
SELECT magazines.id
FROM magazines
         JOIN patents
              ON magazines.location_id = (SELECT * FROM id_loc)
UNION
SELECT patents.id
FROM patents
         JOIN books
              ON patents.location_id = (SELECT * FROM id_loc);
SELECT *
FROM list_id;
