package com.library.dao;

import com.library.dao.interfaces.LocationDaoInterface;
import com.library.dao.mappers.LocationMapper;
import com.library.domain.models.Location;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class LocationDao implements LocationDaoInterface {
    private static final Logger log = Logger.getLogger(AuthorDao.class);
    JdbcTemplate jdbcTemplate;
@Autowired
    public LocationDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void createLocation(Location location) {
        log.info("была добавлена локация с ID "+ location.getId());
        jdbcTemplate.update("INSERT into locations(name, city, state) VALUES (?,?,?)"
                ,location.getName()
                ,location.getCity()
                ,location.getState());

    }

    @Override
    public Location getById(int id) {
        log.info("была получена локация с ID "+ id);
        List<Location> locations = jdbcTemplate.query("SELECT * from locations where id=?", new Object[]{id}, new LocationMapper());
        for (Location l : locations) {
            return l;
        }
        return null;
    }

    @Override
    public void deleteLocation(Location location) {
        log.info("была удалена локация с ID "+ location.getId());
        jdbcTemplate.update("DELETE FROM locations WHERE id=?", location.getId());

    }


    @Override
    public List<Location> showContent() {
        log.info("были показаны все локации");
        return jdbcTemplate.query("SELECT * FROM locations", new LocationMapper());
    }

    @Override
    public void updateLocation(Location location) {
        log.info("была редактирована локация с ID "+ location.getId());
        jdbcTemplate.update("UPDATE locations SET name=?, state=?, city=? WHERE id = ?" ,
                location.getName(), location.getState(), location.getCity(), location.getId());
    }
}
