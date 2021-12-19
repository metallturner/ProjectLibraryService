package com.library.domain.models;

import java.time.LocalDate;

public class Magazine extends Literature {
    private MyDate dateOfPublication;

    public Magazine() {

    }


    public Magazine(int id, String name, int locationId,
                    String dataAdd, String dataPub, String dataMod) {
        super(id, name, locationId, dataAdd, dataMod);
        this.dateOfPublication = new MyDate(dataPub);
    }

    public MyDate getDateOfPublication() {
        return dateOfPublication;
    }

    public void setDateOfPublication(String dataPub) {
        this.dateOfPublication = new MyDate(dataPub);
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
