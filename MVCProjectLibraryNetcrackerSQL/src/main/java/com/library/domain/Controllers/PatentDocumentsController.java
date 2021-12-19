package com.library.domain.Controllers;

import com.library.dao.interfaces.ClassGetAuthorAndLocationInterface;
import com.library.dao.interfaces.PatentDocumentsDaoInterface;
import com.library.domain.ControllerInterfaces.PatentDocumentsControllerInterface;
import com.library.domain.models.PatentDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@RestController
@RequestMapping("/patent")
public class PatentDocumentsController implements PatentDocumentsControllerInterface {

    PatentDocumentsDaoInterface patentDocumentsDaoInterface;
    ClassGetAuthorAndLocationInterface classGetAuthorAndLocationInterface;

    @Autowired
    public PatentDocumentsController(@Qualifier("patentDocumentsDao") PatentDocumentsDaoInterface patentDocumentsDaoInterface,
                                     @Qualifier("classGetAuthorAndLocation") ClassGetAuthorAndLocationInterface classGetAuthorAndLocationInterface) {
        this.patentDocumentsDaoInterface = patentDocumentsDaoInterface;
        this.classGetAuthorAndLocationInterface = classGetAuthorAndLocationInterface;
    }


    @PostMapping("/create")
    public ResponseEntity<?> createPatentDocument(@RequestBody PatentDocument patentDocument) {
        patentDocumentsDaoInterface.createPatentDocument(patentDocument);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<PatentDocument> getById(@PathVariable("id") int id) {
        if (patentDocumentsDaoInterface.getById(id) != null) {
            PatentDocument patentDocument = patentDocumentsDaoInterface.getById(id);
            patentDocument.setAuthor(classGetAuthorAndLocationInterface.getAuthor(patentDocument.getAuthorId()));
            patentDocument.setLocation(classGetAuthorAndLocationInterface.getLocation(patentDocument.getLocationId()));
            return new ResponseEntity<>(patentDocument, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deletePatentDocument(@PathVariable int id) {
        PatentDocument patentDocument = new PatentDocument();
        patentDocument.setId(id);
        patentDocumentsDaoInterface.deletePatentDocument(patentDocument);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<PatentDocument>> showContent() {
        List<PatentDocument> patents = patentDocumentsDaoInterface.showContent();
        if (patents != null && !patents.isEmpty()) {
            for (PatentDocument p : patents) {
                p.setLocation(classGetAuthorAndLocationInterface.getLocation(p.getLocationId()));
                p.setAuthor(classGetAuthorAndLocationInterface.getAuthor(p.getAuthorId()));
            }
            return new ResponseEntity<>(patents, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

@PutMapping("/update")
    public ResponseEntity<?> updatePatentDocument(@RequestBody PatentDocument patentDocument) {
        patentDocumentsDaoInterface.updatePatentDocument(patentDocument);
        return new ResponseEntity<>(HttpStatus.OK);

    }
}
