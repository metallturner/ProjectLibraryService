package com.library.dao.mappers;

import com.library.domain.models.Book;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();
        book.setName(rs.getString("name"));
        book.setId(rs.getInt("id"));
        book.setIsbn(rs.getString("isbn"));
        book.setAuthorId(rs.getInt("author_id"));
        book.setLocationId(rs.getInt("location_id"));
        book.setDateAddedToTheLibrary(rs.getString("date_add"));
        book.setDateOfPublication(rs.getString("date_pub"));
        book.setDateOfModification(rs.getString("date_mod"));

        return book;
    }
}
