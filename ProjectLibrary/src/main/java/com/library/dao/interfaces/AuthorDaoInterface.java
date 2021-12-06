package com.library.dao.interfaces;

import com.library.domain.models.Author;

public interface AuthorDaoInterface {
    public void createAuthor(Author author);
    public void searchAuthorName(String name);
    public void deleteAuthor(Author author);
    public void showContent();
    public void updateAuthor(Author author);
}
