package com.library.domain.Controllers;

import com.library.dao.interfaces.AuthorDaoInterface;
import com.library.domain.ControllerInterfaces.AuthorControllerInterface;
import com.library.domain.models.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class AuthorController implements AuthorControllerInterface {
    AuthorDaoInterface authorDaoInterface;
@Autowired
    public AuthorController(@Qualifier("authorDao") AuthorDaoInterface authorDaoInterface) {
        this.authorDaoInterface = authorDaoInterface;
    }

    @Override
    public void createAuthor(Author author) {
    authorDaoInterface.createAuthor(author);

    }

    @Override
    public void searchAuthorName(String name) {
authorDaoInterface.searchAuthorName(name);
    }

    @Override
    public void deleteAuthor(Author author) {
authorDaoInterface.deleteAuthor(author);
    }

    @Override
    public void showContent() {
authorDaoInterface.showContent();
    }

    @Override
    public void updateAuthor(Author author) {
authorDaoInterface.updateAuthor(author);
    }
}
