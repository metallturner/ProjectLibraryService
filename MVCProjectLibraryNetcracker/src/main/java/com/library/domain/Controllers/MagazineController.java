package com.library.domain.Controllers;

import com.library.dao.interfaces.ClassGetAuthorAndLocationInterface;
import com.library.dao.interfaces.MagazineDaoInterface;
import com.library.domain.ControllerInterfaces.MagazineControllerInterface;
import com.library.domain.models.Magazine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@RestController
@RequestMapping("/magazine")
public class MagazineController implements MagazineControllerInterface {

    MagazineDaoInterface magazineDaoInterface;
    ClassGetAuthorAndLocationInterface classGetAuthorAndLocationInterface;
@Autowired
    public MagazineController(@Qualifier("magazineDao") MagazineDaoInterface magazineDaoInterface,
                              @Qualifier("classGetAuthorAndLocation") ClassGetAuthorAndLocationInterface classGetAuthorAndLocationInterface) {
        this.magazineDaoInterface = magazineDaoInterface;
        this.classGetAuthorAndLocationInterface = classGetAuthorAndLocationInterface;
}



    @PostMapping("/create")
    public ResponseEntity<?> createMagazine(@RequestBody Magazine magazine) {
        magazineDaoInterface.createMagazine(magazine);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<Magazine> getById(@PathVariable("id") int id) {
        if(magazineDaoInterface.getById(id)!=null){
            Magazine magazine = magazineDaoInterface.getById(id);
            magazine.setLocation(classGetAuthorAndLocationInterface.getLocation(magazine.getLocationId()));
            return new ResponseEntity<>(magazine, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteMagazine(@PathVariable int id) {
        Magazine magazine = new Magazine();
        magazine.setId(id);
        if(magazineDaoInterface.itemAvailability(magazine)==-1){
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
        magazineDaoInterface.deleteMagazine(magazine);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Magazine>> showContent() {
        List<Magazine> mags = magazineDaoInterface.showContent();
        if(mags!=null && !mags.isEmpty()){
            for (Magazine m : mags) {
                m.setLocation(classGetAuthorAndLocationInterface.getLocation(m.getLocationId()));
            }
            return new ResponseEntity<>(mags, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

@PutMapping("/update")
    public ResponseEntity<?> updateMagazine(@RequestBody Magazine magazine) {
    magazineDaoInterface.updateMagazine(magazine);
return new ResponseEntity<>(HttpStatus.OK);
    }
}

