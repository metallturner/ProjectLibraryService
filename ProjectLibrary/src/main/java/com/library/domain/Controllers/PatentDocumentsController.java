package com.library.domain.Controllers;
import com.library.dao.PatentDocumentsDao;
import com.library.dao.interfaces.PatentDocumentsDaoInterface;
import com.library.domain.ControllerInterfaces.PatentDocumentsControllerInterface;
import com.library.domain.models.PatentDocument;
import org.springframework.stereotype.Component;

@Component
public class PatentDocumentsController implements PatentDocumentsControllerInterface {
    PatentDocumentsDaoInterface patentDocumentsDaoInterface = new PatentDocumentsDao();

    public void createPatentDocument(PatentDocument patentDocument){
        patentDocumentsDaoInterface.createPatentDocument(patentDocument);
    }
    public void searchPatentDocumentName(String name){
        patentDocumentsDaoInterface.searchPatentDocumentName(name);
    }
    public void deletePatentDocument(PatentDocument patentDocument){
        patentDocumentsDaoInterface.deletePatentDocument(patentDocument);
    }
    public void showContent(){
        patentDocumentsDaoInterface.showContent();
    }
    public void updatePatentDocument(PatentDocument patentDocument){
        patentDocumentsDaoInterface.updatePatentDocument(patentDocument);
    }
}
