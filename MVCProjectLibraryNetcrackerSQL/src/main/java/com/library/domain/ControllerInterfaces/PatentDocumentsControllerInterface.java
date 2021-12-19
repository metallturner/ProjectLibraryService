package com.library.domain.ControllerInterfaces;

import com.library.domain.models.PatentDocument;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PatentDocumentsControllerInterface {
    public ResponseEntity<?> createPatentDocument(PatentDocument patentDocument);
    public ResponseEntity<PatentDocument> getById(int id);
    public ResponseEntity<?> deletePatentDocument(int id);
    public ResponseEntity<List<PatentDocument>> showContent();
    public ResponseEntity<?> updatePatentDocument(PatentDocument patentDocument);
}
