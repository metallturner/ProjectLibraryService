package com.library.domain.Controllers;

import com.library.dao.DocumentDao;
import com.library.dao.interfaces.DocumentsDaoInterface;
import com.library.domain.ControllerInterfaces.DocumentsControllerInterface;
import com.library.domain.models.Document;

public class DocumentController implements DocumentsControllerInterface {
    DocumentsDaoInterface documentsDaoInterface = new DocumentDao();

    @Override
    public void createDocument(Document document) {
        documentsDaoInterface.createDocument(document);
    }

    @Override
    public void searchDocumentName(String name) {
documentsDaoInterface.searchDocumentName(name);
    }

    @Override
    public void deleteDocument(Document document) {
documentsDaoInterface.deleteDocument(document);
    }

    @Override
    public void showContent() {
documentsDaoInterface.showContent();
    }

    @Override
    public void updateDocument(Document document) {
documentsDaoInterface.updateDocument(document);
    }
}
