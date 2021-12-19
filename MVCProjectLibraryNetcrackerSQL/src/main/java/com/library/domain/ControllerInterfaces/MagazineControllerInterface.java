package com.library.domain.ControllerInterfaces;


import com.library.domain.models.Magazine;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MagazineControllerInterface {
    public ResponseEntity<?> createMagazine(Magazine magazine);
    public ResponseEntity<Magazine> getById(int id);
    public ResponseEntity<?> deleteMagazine(int id);
    public ResponseEntity<List<Magazine>> showContent();
    public ResponseEntity<?> updateMagazine(Magazine magazine);

}
