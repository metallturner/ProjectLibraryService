package com.library.dao;


import com.library.dao.interfaces.AuthorDaoInterface;
import com.library.dao.mappers.AuthorMapper;
import com.library.domain.models.Author;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class AuthorDao implements AuthorDaoInterface {
    JdbcTemplate jdbcTemplate;
    private static final Logger log = Logger.getLogger(AuthorDao.class);

@Autowired
    public AuthorDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void createAuthor(Author author) {
    log.info("был создан автор с id " + author.getId());
        jdbcTemplate.update("INSERT into authors(name) VALUES (?)",
                author.getName());

    }

    @Override
    public Author getById(int id) {
        log.info("был получен автор с id " + id);
        List<Author> authors = jdbcTemplate.query("SELECT * from authors where id=?", new Object[]{id}, new AuthorMapper());
        for (Author a : authors) {
            return a;
        }
        return null;
    }

    @Override
    public void deleteAuthor(Author author) {
        log.info("был удален автор с id " + author.getId());
        jdbcTemplate.update("DELETE FROM authors WHERE id=?", author.getId());

    }


    @Override
    public List<Author> showContent() {
        log.info("были показаны все авторы");
        return jdbcTemplate.query("SELECT * FROM authors", new AuthorMapper());
    }

    @Override
    public void updateAuthor(Author author) {
        log.info("был редактирован автор с id " + author.getId());
        jdbcTemplate.update("UPDATE authors SET name=? WHERE id = ?" ,
                author.getName(), author.getId());
    }
    }


