package com.library.domain.ControllerInterfaces;

import com.library.domain.models.Book;

public interface BookControllerInterface {
    public void createBook(String name, String isbn, String author, String location,
                           int yearPub, int monthPub, int dayPub,
                           int yearAdd, int monthAdd, int dayAdd,
                           int yearMod, int monthMod, int dayMod);
    public Book searchBookName();
    public void deleteBook();
    public void showContent();
    public void updateBook();
    public void deserializationOnCollection();
}
