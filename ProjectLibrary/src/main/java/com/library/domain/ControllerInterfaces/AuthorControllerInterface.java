package com.library.domain.ControllerInterfaces;

import com.library.domain.models.Author;

public interface AuthorControllerInterface {
    public void createAuthor(Author author);
    public void searchAuthorName(String name);
    public void deleteAuthor(Author author);
    public void showContent();
    public void updateAuthor(Author author);
}
