SELECT books.name, authors.name
FROM books
JOIN authors ON books.author_id = authors.id
AND authors.name='Dale Carnagey'
