package com.library.domain.ControllerInterfaces;

import com.library.domain.models.Location;

public interface LocationControllerInterface {
    public void createLocation(Location location);
    public void searchLocationName(String name);
    public void deleteLocation(Location location);
    public void showContent();
    public void updateLocation(Location location);
}
