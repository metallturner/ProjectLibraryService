package com.library.dao.interfaces;

import com.library.domain.models.Location;

import java.util.List;

public interface LocationDaoInterface {
    public void createLocation(Location location);
    public Location getById(int id);
    public void deleteLocation(Location location);
    public List<Location> showContent();
    public void updateLocation(Location location);

}
