package com.library.dao.mappers;

import com.library.domain.models.Author;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorMapper implements RowMapper<Author> {

    @Override
public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
        Author author = new Author();
        author.setName(rs.getString("name"));
        author.setId(rs.getInt("id"));
        return author;
        }

}
