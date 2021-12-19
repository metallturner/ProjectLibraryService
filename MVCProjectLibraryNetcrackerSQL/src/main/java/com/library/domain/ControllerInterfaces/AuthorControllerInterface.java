package com.library.domain.ControllerInterfaces;

import com.library.domain.models.Author;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AuthorControllerInterface {
    public ResponseEntity<?> createAuthor(Author author);
    public ResponseEntity<Author> getById(int id);
    public ResponseEntity<?> deleteAuthor(int id);
    public ResponseEntity<List<Author>> showContent();
    public ResponseEntity<?> updateAuthor(Author author);
}
