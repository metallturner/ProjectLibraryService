package com.library.dao.interfaces;

import com.library.domain.models.Author;

import java.util.List;

public interface AuthorDaoInterface {
    public void createAuthor(Author author);
    public Author getById(int id);
    public void deleteAuthor(Author author);
    public List<Author> showContent();
    public void updateAuthor(Author author);
    public int itemAvailability(Author author);
}
