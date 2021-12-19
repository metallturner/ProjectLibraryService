package com.library.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.library.dao.interfaces.AuthorDaoInterface;
import com.library.dao.interfaces.ClassGetAuthorAndLocationInterface;
import com.library.dao.interfaces.LocationDaoInterface;
import com.library.domain.models.Author;
import com.library.domain.models.Location;
import com.library.Gson.SerializerDate;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.io.*;
import java.time.LocalDate;
import java.util.Scanner;
@Component
public class ClassGetAuthorAndLocation implements ClassGetAuthorAndLocationInterface {
    private static final Logger log = Logger.getLogger(ClassGetAuthorAndLocation.class);

    final static String PATH_LOCATION = "C:\\Users\\paul\\Desktop\\NetCracker lessons\\MVCProjectLibraryNetcracker\\src\\main\\resources\\Locations";

AuthorDaoInterface authorDaoInterface;
LocationDaoInterface locationDaoInterface;
@Autowired
    public ClassGetAuthorAndLocation(AuthorDaoInterface authorDaoInterface, LocationDaoInterface locationDaoInterface) {
        this.authorDaoInterface = authorDaoInterface;
        this.locationDaoInterface = locationDaoInterface;
    }

    @Override
   public Author getAuthor(int id){
     return authorDaoInterface.getById(id);
   }

    @Override
    public Location getLocation(int id){
return locationDaoInterface.getById(id);
    }






}
