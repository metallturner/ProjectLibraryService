package com.library.domain.Controllers;

import com.library.dao.BookDao;
import com.library.dao.interfaces.BookDaoInterface;
import com.library.dao.interfaces.ClassGetAuthorAndLocationInterface;
import com.library.domain.ControllerInterfaces.BookControllerInterface;
import com.library.domain.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Component
@RestController
@RequestMapping("/book")
public class BookController implements BookControllerInterface {
    BookDaoInterface bookDaoInterface;
    ClassGetAuthorAndLocationInterface classGetAuthorAndLocationInterface;


    @Autowired
    public BookController(@Qualifier("bookDao") BookDaoInterface bookDaoInterface,
                          @Qualifier("classGetAuthorAndLocation") ClassGetAuthorAndLocationInterface classGetAuthorAndLocationInterface) {
        this.bookDaoInterface = bookDaoInterface;
        this.classGetAuthorAndLocationInterface = classGetAuthorAndLocationInterface;
    }




@PostMapping("/create")
    public ResponseEntity<?> createBook(@RequestBody Book book) {
        bookDaoInterface.createBook(book);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<Book> getById(@PathVariable("id") int id) {
        if(bookDaoInterface.getById(id)!=null){
            Book book = bookDaoInterface.getById(id);
            book.setAuthor(classGetAuthorAndLocationInterface.getAuthor(book.getAuthorId()));
            book.setLocation(classGetAuthorAndLocationInterface.getLocation(book.getLocationId()));
            return new ResponseEntity<>(book, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
@DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable("id") int id) {
        Book book = new Book();
        book.setId(id);
        bookDaoInterface.deleteBook(book);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping()
    public ResponseEntity<List<Book>> showContent() {
        List<Book> books = bookDaoInterface.showContent();
        if(books!=null && !books.isEmpty()){
        for (Book b : books) {
            b.setAuthor(classGetAuthorAndLocationInterface.getAuthor(b.getAuthorId()));
            b.setLocation(classGetAuthorAndLocationInterface.getLocation(b.getLocationId()));
        }
        return new ResponseEntity<>(books, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
@PutMapping("/update")
    public ResponseEntity<?> updateBook(@RequestBody Book book) {
        bookDaoInterface.updateBook(book);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}

