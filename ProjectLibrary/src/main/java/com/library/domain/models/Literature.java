package com.library.domain.models;
import java.time.LocalDate;
public class Literature {
    private int id;
    private String name;
    private transient Location location;
    private LocalDate dateAddedToTheLibrary;
    private LocalDate  dateOfModification ;
    private int locationId;


    public Literature(){

    }



    public Literature(int id, String name, int locationId,
                      int yearAdd, int monthAdd, int dayAdd,
                      int yearMod, int monthMod, int dayMod) {

        this.id = id;
        this.name = name;
        this.locationId = locationId;
        this.dateAddedToTheLibrary = LocalDate.of(yearAdd,monthAdd,dayAdd);
        this.dateOfModification = LocalDate.of(yearMod,monthMod,dayMod);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public LocalDate getDateAddedToTheLibrary() {
        return dateAddedToTheLibrary;
    }

    public void setDateAddedToTheLibrary(int yearAdd,int monthAdd,int dayAdd) {
        this.dateAddedToTheLibrary = LocalDate.of(yearAdd,monthAdd,dayAdd);
    }

    public LocalDate getDateOfModification() {
        return dateOfModification;
    }

    public void setDateOfModification(int yearMod, int monthMod, int dayMod) {
        this.dateOfModification =  LocalDate.of(yearMod,monthMod,dayMod);
    }



}

