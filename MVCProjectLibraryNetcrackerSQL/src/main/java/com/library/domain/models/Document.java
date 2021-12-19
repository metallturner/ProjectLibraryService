package com.library.domain.models;

import java.time.LocalDate;

public class Document extends Literature {
    private MyDate dateOfDocumentCreation;
    private String documentNumber;

public Document(){

}
    public Document(int id, String name, String documentNumber, int locationId,
                    String dataCreate, String dataAdd, String dataMod) {
        super(id, name, locationId, dataAdd,dataMod);
        this.dateOfDocumentCreation = new MyDate(dataCreate);
        this.documentNumber = documentNumber;
    }

    public MyDate getDateOfDocumentCreation() {
        return dateOfDocumentCreation;
    }

    public void setDateOfDocumentCreation(String dataCreate) {
        this.dateOfDocumentCreation = new MyDate(dataCreate);
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
