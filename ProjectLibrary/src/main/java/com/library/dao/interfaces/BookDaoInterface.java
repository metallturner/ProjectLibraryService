package com.library.dao.interfaces;

import com.library.domain.models.Book;

public interface BookDaoInterface {
    public void createBook(Book book);
    public void searchBookName(String name);
    public void deleteBook(Book book);
    public void showContent();
    public void updateBook(Book book);

}
