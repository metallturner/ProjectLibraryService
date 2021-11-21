package com.library.domain.Controllers;

import com.library.dao.BookDao;
import com.library.dao.interfaces.BookDaoInterface;
import com.library.domain.ControllerInterfaces.BookControllerInterface;
import com.library.domain.models.Book;

public class BookController implements BookControllerInterface {
    BookDaoInterface bookDaoInterface = new BookDao();

   // public BookController(BookDaoInterface bookDaoInterface) {
       // this.bookDaoInterface = bookDaoInterface;
   // }

    public void createBook(String name, String isbn, String author, String location,
                           int yearPub, int monthPub, int dayPub,
                           int yearAdd, int monthAdd, int dayAdd,
                           int yearMod, int monthMod, int dayMod) {
        bookDaoInterface.createBook(name, isbn, author, location, yearPub, monthPub, dayPub,
                yearAdd, monthAdd, dayAdd,
                yearMod, monthMod, dayMod);
    }
    public Book searchBookName() {
        return bookDaoInterface.searchBookName();
    }
    public void deleteBook(){
        bookDaoInterface.deleteBook();
    }
    public void showContent(){
        bookDaoInterface.showContent();
    }
    public void updateBook() {
        bookDaoInterface.updateBook();
    }
    public void deserializationOnCollection(){
        bookDaoInterface.deserializationOnCollection();
    }
    }

