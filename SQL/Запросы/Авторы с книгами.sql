SELECT books.name, authors.name
FROM books, authors
WHERE books.author_id = authors.id