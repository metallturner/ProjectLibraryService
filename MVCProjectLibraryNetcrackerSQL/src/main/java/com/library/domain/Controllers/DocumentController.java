package com.library.domain.Controllers;

import com.library.dao.interfaces.ClassGetAuthorAndLocationInterface;
import com.library.dao.interfaces.DocumentsDaoInterface;
import com.library.domain.ControllerInterfaces.DocumentsControllerInterface;
import com.library.domain.models.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@RestController
@RequestMapping("/document")
public class DocumentController implements DocumentsControllerInterface {
    DocumentsDaoInterface documentsDaoInterface;
    ClassGetAuthorAndLocationInterface classGetAuthorAndLocationInterface;

    @Autowired
    public DocumentController(@Qualifier("documentDao") DocumentsDaoInterface documentsDaoInterface,
                              @Qualifier("classGetAuthorAndLocation") ClassGetAuthorAndLocationInterface classGetAuthorAndLocationInterface) {
        this.documentsDaoInterface = documentsDaoInterface;
        this.classGetAuthorAndLocationInterface = classGetAuthorAndLocationInterface;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createDocument(@RequestBody Document document) {
        documentsDaoInterface.createDocument(document);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<Document> getById(@PathVariable("id") int id) {
        if(documentsDaoInterface.getById(id)!=null){
            Document document = documentsDaoInterface.getById(id);
            document.setLocation(classGetAuthorAndLocationInterface.getLocation(document.getLocationId()));
            return new ResponseEntity<>(document, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteDocument(@PathVariable int id) {
        Document document = new Document();
        document.setId(id);
        documentsDaoInterface.deleteDocument(document);
        return new ResponseEntity<>(HttpStatus.OK);
    }

@GetMapping()
    public ResponseEntity<List<Document>> showContent() {
        List<Document> docs = documentsDaoInterface.showContent();
        if(docs!=null && !docs.isEmpty()){
            for (Document d : docs) {
                d.setLocation(classGetAuthorAndLocationInterface.getLocation(d.getLocationId()));
            }
            return new ResponseEntity<>(docs, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

@PutMapping("/update")
    public ResponseEntity<?> updateDocument(@RequestBody Document document) {
        documentsDaoInterface.updateDocument(document);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
