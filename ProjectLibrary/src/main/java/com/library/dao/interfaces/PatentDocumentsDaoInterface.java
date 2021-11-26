package com.library.dao.interfaces;

import com.library.domain.models.PatentDocument;

public interface PatentDocumentsDaoInterface {
    public void createPatentDocument(PatentDocument patentDocument);
    public void searchPatentDocumentName(String name);
    public void deletePatentDocument(PatentDocument patentDocument);
    public void showContent();
    public void updatePatentDocument(PatentDocument patentDocument);
}
