package com.library.dao.mappers;

import com.library.domain.models.Location;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class LocationMapper implements RowMapper<Location> {
    @Override
    public Location mapRow(ResultSet rs, int rowNum) throws SQLException {
        Location location = new Location();
        location.setId(rs.getInt("id"));
        location.setState(rs.getString("state"));
        location.setCity(rs.getString("city"));
        location.setName(rs.getString("name"));
        return location;
    }
}
