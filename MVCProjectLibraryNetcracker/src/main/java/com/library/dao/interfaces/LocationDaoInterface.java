package com.library.dao.interfaces;

import com.library.domain.models.Location;

public interface LocationDaoInterface {
    public void createLocation(Location location);
    public void searchLocationName(String name);
    public void deleteLocation(Location location);
    public void showContent();
    public void updateLocation(Location location);
}
