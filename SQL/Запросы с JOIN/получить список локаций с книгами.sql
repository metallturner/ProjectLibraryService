SELECT locations.name, books.name
FROM locations
JOIN books ON books.location_id = locations.id