package com.library.domain.ControllerInterfaces;


import com.library.domain.models.Document;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DocumentsControllerInterface {
    public ResponseEntity<?> createDocument(Document document);
    public ResponseEntity<Document> getById(int id);
    public ResponseEntity<?> deleteDocument(int id);
    public ResponseEntity<List<Document>> showContent();
    public ResponseEntity<?> updateDocument(Document document);
}
