package com.library.dao;

import com.library.dao.interfaces.MagazineDaoInterface;
import com.library.dao.mappers.MagazineMapper;
import com.library.domain.models.Magazine;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class MagazineDao implements MagazineDaoInterface {
    private static final Logger log = Logger.getLogger(Magazine.class);

    JdbcTemplate jdbcTemplate;
@Autowired
    public MagazineDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void createMagazine(Magazine magazine) {
        log.info("Был создан журнал с ID " + magazine.getId());
        jdbcTemplate.update("INSERT into magazines(name, location_id, date_add, date_mod, date_pub) " +
                        "VALUES (?, ?, ?, ?, ?)"
                , magazine.getName()
                , magazine.getLocationId()
                , magazine.getDateAddedToTheLibrary().getData()
                , magazine.getDateOfModification().getData()
                , magazine.getDateOfPublication().getData());
    }

    @Override
    public Magazine getById(int id) {
        log.info("Был показан журнал с ID " + id);
        List<Magazine> magazines = jdbcTemplate.query("SELECT * from magazines where id=?", new Object[]{id}, new MagazineMapper());
        for (Magazine m : magazines) {
            return m;
        }
        return null;
    }

    @Override
    public void deleteMagazine(Magazine magazine) {
        log.info("Был удален журнал с ID " + magazine.getId());
        jdbcTemplate.update("DELETE FROM magazines WHERE id=?", magazine.getId());

    }


    @Override
    public List<Magazine> showContent() {
        log.info("Был выполнен показ всех журналов");
        return jdbcTemplate.query("SELECT * FROM magazines", new MagazineMapper());
    }

    @Override
    public void updateMagazine(Magazine magazine) {
        log.info("Был редактирован журнал с ID " + magazine.getId());
        jdbcTemplate.update("UPDATE magazines SET name=?," +
                        "location_id=?, date_add=?, date_mod=?, date_pub=? WHERE id = ?"
                ,magazine.getName()
                ,magazine.getLocationId()
                ,magazine.getDateAddedToTheLibrary().getData()
                ,magazine.getDateOfModification().getData()
                ,magazine.getDateOfPublication().getData()
                ,magazine.getId());
    }
}
