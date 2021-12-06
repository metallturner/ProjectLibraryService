package com.library.domain.models;

import java.time.LocalDate;

public class Magazine extends Literature {
    private LocalDate dateOfPublication;

    public Magazine() {

    }


    public Magazine(int id, String name, int locationId,
                    int yearPub, int monthPub, int dayPub,
                    int yearAdd, int monthAdd, int dayAdd,
                    int yearMod, int monthMod, int dayMod) {
        super(id, name, locationId, yearAdd, monthAdd, dayAdd, yearMod, monthMod, dayMod);
        this.dateOfPublication = LocalDate.of(yearPub, monthPub, dayPub);
    }

    public LocalDate getDateOfPublication() {
        return dateOfPublication;
    }

    public void setDateOfPublication(int yearPub, int monthPub, int dayPub) {
        this.dateOfPublication = LocalDate.of(yearPub, monthPub, dayPub);
    }



    @Override
    public String toString() {
        return "Magazine{"+ "\n" +
                "id = " + getId() + "\n"+
                "name = '" + getName() + '\''+ "\n" +
                "locationId = '" + getLocationId() + '\''+ "\n" +
                "location = '" + getLocation() + '\''+ "\n" +
                "dateOfPublication = " + dateOfPublication + "\n"+
                "dateAddedToTheLibrary = " + getDateAddedToTheLibrary()+ "\n" +
                "dateOfModification = " + getDateOfModification() +
                '}';
    }
}
