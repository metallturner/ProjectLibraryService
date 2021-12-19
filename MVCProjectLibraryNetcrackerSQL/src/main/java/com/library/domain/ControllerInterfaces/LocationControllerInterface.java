package com.library.domain.ControllerInterfaces;

import com.library.domain.models.Location;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface LocationControllerInterface {
    public ResponseEntity<?> createLocation(Location location);
    public ResponseEntity<Location> getById(int id);
    public ResponseEntity<?> deleteLocation(int id);
    public ResponseEntity<List<Location>> showContent();
    public ResponseEntity<?> updateLocation(Location location);
}
