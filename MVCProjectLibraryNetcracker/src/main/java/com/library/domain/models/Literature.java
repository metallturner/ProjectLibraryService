package com.library.domain.models;
import java.time.LocalDate;
public class Literature {
    private int id;
    private String name;
    private Location location;
    private MyDate dateAddedToTheLibrary;
    private MyDate dateOfModification ;
    private int locationId;


    public Literature(){

    }



    public Literature(int id, String name, int locationId,
                      String dataAdd, String dataMod) {

        this.id = id;
        this.name = name;
        this.locationId = locationId;
        this.dateAddedToTheLibrary = new MyDate(dataAdd);
        this.dateOfModification = new MyDate(dataMod);
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

    public MyDate getDateAddedToTheLibrary() {
        return dateAddedToTheLibrary;
    }

    public void setDateAddedToTheLibrary(MyDate dateAddedToTheLibrary) {
        this.dateAddedToTheLibrary = dateAddedToTheLibrary;
    }

    public MyDate getDateOfModification() {
        return dateOfModification;
    }

    public void setDateOfModification(MyDate dateOfModification) {
        this.dateOfModification =  dateOfModification;
    }



}

