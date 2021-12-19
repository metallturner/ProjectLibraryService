package com.library.dao.interfaces;

import com.library.domain.models.PatentDocument;

import java.util.List;

public interface PatentDocumentsDaoInterface {
    public void createPatentDocument(PatentDocument patentDocument);
    public PatentDocument getById(int id);
    public void deletePatentDocument(PatentDocument patentDocument);
    public List<PatentDocument> showContent();
    public void updatePatentDocument(PatentDocument patentDocument);
}
