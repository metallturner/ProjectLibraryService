package com.library.dao.mappers;


import com.library.domain.models.PatentDocument;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PatentMapper implements RowMapper<PatentDocument> {
    @Override
    public PatentDocument mapRow(ResultSet rs, int rowNum) throws SQLException {
        PatentDocument patentDocument = new PatentDocument();
        patentDocument.setName(rs.getString("name"));
        patentDocument.setId(rs.getInt("id"));
        patentDocument.setPatentNumber(rs.getString("patent_number"));
        patentDocument.setAuthorId(rs.getInt("author_id"));
        patentDocument.setLocationId(rs.getInt("location_id"));
        patentDocument.setDateAddedToTheLibrary(rs.getString("date_add"));
        patentDocument.setDateOfModification(rs.getString("date_mod"));

        return patentDocument;
    }
}
