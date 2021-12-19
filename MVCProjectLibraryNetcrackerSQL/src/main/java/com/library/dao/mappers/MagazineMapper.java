package com.library.dao.mappers;


import com.library.domain.models.Magazine;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MagazineMapper implements RowMapper<Magazine> {
    @Override
    public Magazine mapRow(ResultSet rs, int rowNum) throws SQLException {
        Magazine magazine = new Magazine();
        magazine.setName(rs.getString("name"));
        magazine.setId(rs.getInt("id"));
        magazine.setLocationId(rs.getInt("location_id"));
        magazine.setDateAddedToTheLibrary(rs.getString("date_add"));
        magazine.setDateOfPublication(rs.getString("date_pub"));
        magazine.setDateOfModification(rs.getString("date_mod"));

        return magazine;
    }
}
