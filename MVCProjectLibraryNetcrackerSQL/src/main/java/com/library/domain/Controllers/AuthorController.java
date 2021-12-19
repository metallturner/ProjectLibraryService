package com.library.domain.Controllers;

import com.library.dao.interfaces.AuthorDaoInterface;
import com.library.domain.ControllerInterfaces.AuthorControllerInterface;
import com.library.domain.models.Author;
import com.library.domain.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@RestController
@RequestMapping("/author")
public class AuthorController implements AuthorControllerInterface {
    AuthorDaoInterface authorDaoInterface;
@Autowired
    public AuthorController(@Qualifier("authorDao") AuthorDaoInterface authorDaoInterface) {
        this.authorDaoInterface = authorDaoInterface;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createAuthor(@RequestBody Author author) {
    authorDaoInterface.createAuthor(author);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @GetMapping("/search/{id}")
    public ResponseEntity<Author> getById(@PathVariable int id) {
        if(authorDaoInterface.getById(id)!=null){
            Author author = authorDaoInterface.getById(id);
            return new ResponseEntity<>(author, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteAuthor(@PathVariable("id") int id) {
        Author author = new Author();
        author.setId(id);
        authorDaoInterface.deleteAuthor(author);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Author>> showContent() {
        List<Author> authors = authorDaoInterface.showContent();
        if(authors!=null && !authors.isEmpty()){
            return new ResponseEntity<>(authors, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateAuthor(@RequestBody Author author) {


    authorDaoInterface.updateAuthor(author);


return new ResponseEntity<>(HttpStatus.OK);
    }
}
