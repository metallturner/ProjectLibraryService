SELECT books.name
FROM books, authors
WHERE books.author_id = authors.id and (authors.name='Pushkin' or authors.name = 'Dale Carnagey')