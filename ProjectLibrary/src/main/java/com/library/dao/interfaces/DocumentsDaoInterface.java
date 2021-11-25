package com.library.dao.interfaces;


import com.library.domain.models.Document;

public interface DocumentsDaoInterface {
    public void createDocument(Document document);
    public void searchDocumentName(String name);
    public void deleteDocument(Document document);
    public void showContent();
    public void updateDocument(Document document);
}
