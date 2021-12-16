package com.library.dao.interfaces;


import com.library.domain.models.Document;

import java.util.List;

public interface DocumentsDaoInterface {
    public void createDocument(Document document);
    public Document getById(int id);
    public void deleteDocument(Document document);
    public List<Document> showContent();
    public void updateDocument(Document document);
    public int itemAvailability(Document document);
}
