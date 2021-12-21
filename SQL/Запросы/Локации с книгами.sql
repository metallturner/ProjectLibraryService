SELECT locations.name, books.name
FROM books, locations
WHERE books.location_id = locations.id
