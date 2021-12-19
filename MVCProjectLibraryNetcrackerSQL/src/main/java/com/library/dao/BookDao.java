package com.library.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.library.dao.interfaces.BookDaoInterface;
import com.library.dao.interfaces.ClassGetAuthorAndLocationInterface;
import com.library.dao.mappers.BookMapper;
import com.library.domain.models.Book;
import com.library.domain.models.messages.Messages;
import com.library.Gson.SerializerDate;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class BookDao implements BookDaoInterface {
    private static final Logger log = Logger.getLogger(BookDao.class);

    JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }



    @Override
    public void createBook(Book book) {
        log.info("Была создана книга с ID " + book.getId());
        jdbcTemplate.update("INSERT into books(name, isbn, author_id, location_id, date_add, date_mod, date_pub) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?)"
                ,book.getName()
                ,book.getIsbn()
                ,book.getAuthorId()
                ,book.getLocationId()
                ,book.getDateAddedToTheLibrary().getData()
                ,book.getDateOfModification().getData()
                ,book.getDateOfPublication().getData());
    }

    @Override
    public Book getById(int id) {
        log.info("Была получена книга с ID " + id);
        List<Book> books = jdbcTemplate.query("SELECT * from books where id=?", new Object[]{id}, new BookMapper());
        for (Book b : books) {
            return b;
        }
        return null;
    }

    @Override
    public void deleteBook(Book book) {
        log.info("Была удалена книга с ID " + book.getId());
        jdbcTemplate.update("DELETE FROM Books WHERE id=?", book.getId());

    }


    @Override
    public List<Book> showContent() {
        log.info("Юыл выполнен показ всех книг");
        return jdbcTemplate.query("SELECT * FROM Books", new BookMapper());
    }

    @Override
    public void updateBook(Book book) {
        log.info("Была редактирована книга с ID " + book.getId());
        jdbcTemplate.update("UPDATE Books SET name=?, isbn=?, " +
                        "author_id=?, location_id=?, date_add=?, date_mod=?, date_pub=? WHERE id = ?"
                ,book.getName()
                ,book.getIsbn()
                ,book.getAuthorId()
                ,book.getLocationId()
                ,book.getDateAddedToTheLibrary().getData()
                ,book.getDateOfModification().getData()
                ,book.getDateOfPublication().getData()
                ,book.getId());
    }
}











