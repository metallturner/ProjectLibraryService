package com.library.domain.models;
import java.time.LocalDate;
public class Literature {
    private int id;
    private String name;
    private String  location;
    private LocalDate dateAddedToTheLibrary;
    private LocalDate  dateOfModification ;



    public Literature(int id, String name, String location,
                      int yearAdd, int monthAdd, int dayAdd,
                      int yearMod, int monthMod, int dayMod) {

        this.id = id;
        this.name = name;
        this.location = location;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
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

    public void setDateOfModification(LocalDate dateOfModification) {
        this.dateOfModification = dateOfModification;
    }



}

