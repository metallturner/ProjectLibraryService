package com.library.dao;

import com.library.dao.interfaces.DocumentsDaoInterface;
import com.library.dao.mappers.DocMapper;
import com.library.domain.models.Document;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DocumentDao implements DocumentsDaoInterface {
    private static final Logger log = Logger.getLogger(DocumentDao.class);


    JdbcTemplate jdbcTemplate;

    @Autowired
    public DocumentDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void createDocument(Document document) {
        log.info("Был создан документ с ID " + document.getId());
        jdbcTemplate.update("INSERT into documents(name, doc_number, location_id, date_add, date_mod, date_create) " +
                        "VALUES (?, ?, ?, ?, ?, ?)"
                , document.getName()
                , document.getDocumentNumber()
                , document.getLocationId()
                , document.getDateAddedToTheLibrary().getData()
                , document.getDateOfModification().getData()
                , document.getDateOfDocumentCreation().getData());
    }

    @Override
    public Document getById(int id) {
        log.info("Был показан документ с ID " + id);
        List<Document> documents = jdbcTemplate.query("SELECT * from documents where id=?", new Object[]{id}, new DocMapper());
        for (Document d : documents) {
            return d;
        }
        return null;
    }

    @Override
    public void deleteDocument(Document document) {
        log.info("Был удален документ с ID " + document.getId());
        jdbcTemplate.update("DELETE FROM documents WHERE id=?", document.getId());

    }


    @Override
    public List<Document> showContent() {
        log.info("Был выполнен показ всех документов");
        return jdbcTemplate.query("SELECT * FROM documents", new DocMapper());
    }

    @Override
    public void updateDocument(Document document) {
        log.info("Был редактирован документ с ID " + document.getId());
        jdbcTemplate.update("UPDATE documents SET name=?, doc_number=?, " +
                        "location_id=?, date_add=?, date_mod=?, date_create=? WHERE id = ?"
                ,document.getName()
                ,document.getDocumentNumber()
                ,document.getLocationId()
                ,document.getDateAddedToTheLibrary().getData()
                ,document.getDateOfModification().getData()
                ,document.getDateOfDocumentCreation().getData()
                ,document.getId());
    }

}
