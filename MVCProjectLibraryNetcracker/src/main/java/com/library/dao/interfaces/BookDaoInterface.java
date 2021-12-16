package com.library.dao.interfaces;

import com.library.domain.models.Book;

import java.util.List;

public interface BookDaoInterface {
    public void createBook(Book book);
    public Book getById(int id);
    public void deleteBook(Book book);
    public List<Book> showContent();
    public void updateBook(Book book);
    public int itemAvailability(Book book);

}
