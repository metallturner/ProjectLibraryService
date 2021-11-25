package com.library.domain.ControllerInterfaces;

import com.library.domain.models.Book;

public interface BookControllerInterface {
    public void createBook(Book book);
    public void searchBookName(String name);
    public void deleteBook(Book book);
    public void showContent();
    public void updateBook(Book book);

}
