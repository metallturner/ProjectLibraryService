package com.library.dao.mappers;


import com.library.domain.models.Document;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DocMapper implements RowMapper<Document> {
    @Override
    public Document mapRow(ResultSet rs, int rowNum) throws SQLException {
        Document document = new Document();
        document.setName(rs.getString("name"));
        document.setId(rs.getInt("id"));
        document.setDocumentNumber(rs.getString("doc_number"));
        document.setLocationId(rs.getInt("location_id"));
        document.setDateAddedToTheLibrary(rs.getString("date_add"));
        document.setDateOfDocumentCreation(rs.getString("date_create"));
        document.setDateOfModification(rs.getString("date_mod"));

        return document;
    }
}
