package com.library.domain.ControllerInterfaces;

import com.library.domain.models.PatentDocument;

public interface PatentDocumentsControllerInterface {
    public void createPatentDocument(PatentDocument patentDocument);
    public void searchPatentDocumentName(String name);
    public void deletePatentDocument(PatentDocument patentDocument);
    public void showContent();
    public void updatePatentDocument(PatentDocument patentDocument);
}
