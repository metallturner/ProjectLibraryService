package com.library.domain.Controllers;

import com.library.dao.interfaces.LocationDaoInterface;
import com.library.domain.ControllerInterfaces.LocationControllerInterface;
import com.library.domain.models.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class LocationController implements LocationControllerInterface {

    LocationDaoInterface locationDaoInterface;

    @Autowired
    public LocationController(@Qualifier("locationDao") LocationDaoInterface locationDaoInterface) {
        this.locationDaoInterface = locationDaoInterface;
    }

    @Override
    public void createLocation(Location location) {
        locationDaoInterface.createLocation(location);
    }

    @Override
    public void searchLocationName(String name) {
        locationDaoInterface.searchLocationName(name);
    }

    @Override
    public void deleteLocation(Location location) {
        locationDaoInterface.deleteLocation(location);
    }

    @Override
    public void showContent() {
        locationDaoInterface.showContent();
    }

    @Override
    public void updateLocation(Location location) {
        locationDaoInterface.updateLocation(location);
    }
}
