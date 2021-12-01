package com.library.domain.Controllers;
import com.library.dao.BookDao;
import com.library.dao.interfaces.BookDaoInterface;
import com.library.domain.ControllerInterfaces.BookControllerInterface;
import com.library.domain.models.Book;
import org.springframework.stereotype.Component;

@Component
public class BookController implements BookControllerInterface {
    BookDaoInterface bookDaoInterface = new BookDao();



    public void createBook(Book book) {
       bookDaoInterface.createBook(book);
    }
    public void searchBookName(String name) {
        bookDaoInterface.searchBookName(name);
    }
    public void deleteBook(Book book){
        bookDaoInterface.deleteBook(book);
    }
    public void showContent(){
        bookDaoInterface.showContent();
    }
    public void updateBook(Book book) {
        bookDaoInterface.updateBook(book);
    }

    }

