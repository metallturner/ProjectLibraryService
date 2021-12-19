package com.library.dao;

import com.library.dao.interfaces.PatentDocumentsDaoInterface;
import com.library.dao.mappers.PatentMapper;
import com.library.domain.models.PatentDocument;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PatentDocumentsDao implements PatentDocumentsDaoInterface {
    private static final Logger log = Logger.getLogger(PatentDocumentsDao.class);

    JdbcTemplate jdbcTemplate;

    @Autowired
    public PatentDocumentsDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void createPatentDocument(PatentDocument patentDocument) {
        log.info("Была создан патент с ID " + patentDocument.getId());
        jdbcTemplate.update("INSERT into patents(name, patent_number, author_id, location_id, date_add, date_mod) " +
                        "VALUES (?, ?, ?, ?, ?, ?)"
                ,patentDocument.getName()
                ,patentDocument.getPatentNumber()
                ,patentDocument.getAuthorId()
                ,patentDocument.getLocationId()
                ,patentDocument.getDateAddedToTheLibrary().getData()
                ,patentDocument.getDateOfModification().getData());

    }

    @Override
    public PatentDocument getById(int id) {
        log.info("Была получен патент с ID " + id);
        List<PatentDocument> patentDocuments = jdbcTemplate.query("SELECT * from patents where id=?", new Object[]{id}, new PatentMapper());
        for (PatentDocument p : patentDocuments) {
            return p;
        }
        return null;
    }

    @Override
    public void deletePatentDocument(PatentDocument patentDocument) {
        log.info("Была удален патент с ID " + patentDocument.getId());
        jdbcTemplate.update("DELETE FROM patents WHERE id=?", patentDocument.getId());

    }


    @Override
    public List<PatentDocument> showContent() {
        log.info("Был выполнен показ всех патентов");
        return jdbcTemplate.query("SELECT * FROM patents", new PatentMapper());
    }

    @Override
    public void updatePatentDocument(PatentDocument patentDocument) {
        log.info("Был редактирован патент с ID " + patentDocument.getId());
        jdbcTemplate.update("UPDATE patents SET name=?, patent_number=?," +
                        "author_id=?, location_id=?, date_add=?, date_mod=? WHERE id = ?"
                ,patentDocument.getName()
                ,patentDocument.getPatentNumber()
                ,patentDocument.getAuthorId()
                ,patentDocument.getLocationId()
                ,patentDocument.getDateAddedToTheLibrary().getData()
                ,patentDocument.getDateOfModification().getData()
                ,patentDocument.getId());
    }
}
