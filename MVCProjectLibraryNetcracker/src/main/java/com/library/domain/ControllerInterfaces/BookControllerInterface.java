package com.library.domain.ControllerInterfaces;

import com.library.domain.models.Book;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BookControllerInterface {
    public ResponseEntity<?>createBook(Book book);
    public ResponseEntity<Book> getById(int id);
    public ResponseEntity<?> deleteBook(int id);
    public ResponseEntity<List<Book>>showContent();
    public ResponseEntity<?> updateBook(Book book);

}
