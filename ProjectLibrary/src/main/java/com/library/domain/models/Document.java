package com.library.domain.models;

import java.time.LocalDate;

public class Document extends Literature {
    private LocalDate dateOfDocumentCreation;
    private String documentNumber;

public Document(){

}
    public Document(int id, String name, String documentNumber, int locationId,
                    int yearCreate, int monthCreate, int dayCreate,
                    int yearAdd, int monthAdd, int dayAdd,
                    int yearMod, int monthMod, int dayMod) {
        super(id, name, locationId, yearAdd, monthAdd, dayAdd, yearMod, monthMod, dayMod);
        this.dateOfDocumentCreation = LocalDate.of(yearCreate, monthCreate, dayCreate);
        this.documentNumber = documentNumber;
    }

    public LocalDate getDateOfDocumentCreation() {
        return dateOfDocumentCreation;
    }

    public void setDateOfDocumentCreation(int yearCreate, int monthCreate, int dayCreate) {
        this.dateOfDocumentCreation = LocalDate.of(yearCreate, monthCreate, dayCreate);
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }


    @Override
    public String toString() {
        return "Document{"+ "\n" +
                "id = " + getId() + "\n"+
                "name = '" + getName() + '\'' + "\n"+
                "locationId = '" + getLocationId() + '\'' + "\n"+
                "location = '" + getLocation() + '\''+ "\n" +
                "dateOfDocumentCreation = " + dateOfDocumentCreation+ "\n" +
                "dateAddedToTheLibrary = " + getDateAddedToTheLibrary()+ "\n" +
                "dateOfModification = " + getDateOfModification() +
                '}';
    }
}
