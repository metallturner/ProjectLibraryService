package com.library.domain.Controllers;

import com.library.dao.interfaces.LocationDaoInterface;
import com.library.dao.mappers.LocationMapper;
import com.library.domain.ControllerInterfaces.LocationControllerInterface;

import com.library.domain.models.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Component
@RestController
@RequestMapping("/location")
public class LocationController implements LocationControllerInterface {
    JdbcTemplate jdbcTemplate;
    LocationDaoInterface locationDaoInterface;

    @Autowired
    public LocationController(@Qualifier("locationDao") LocationDaoInterface locationDaoInterface, JdbcTemplate jdbcTemplate) {
        this.locationDaoInterface = locationDaoInterface;
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createLocation(@RequestBody Location location) {
        locationDaoInterface.createLocation(location);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<Location> getById(@PathVariable int id) {
        if (locationDaoInterface.getById(id) != null) {
            Location location = locationDaoInterface.getById(id);
            return new ResponseEntity<>(location, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteLocation(@PathVariable("id") int id) {
        Location location = new Location();
        location.setId(id);
        locationDaoInterface.deleteLocation(location);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Location>> showContent() {
        List<Location> locations = locationDaoInterface.showContent();
        if (locations != null && !locations.isEmpty()) {
            return new ResponseEntity<>(locations, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateLocation(@RequestBody Location location) {
        locationDaoInterface.updateLocation(location);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
