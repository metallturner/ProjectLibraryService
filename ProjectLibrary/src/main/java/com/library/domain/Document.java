package com.library.domain;

import java.time.LocalDate;

public class Document extends Literature {
    private LocalDate dateOfDocumentCreation;
    private String documentNumber;

    public Document(int id, String name, String documentNumber, String location,
                    int yearCreate, int monthCreate, int dayCreate,
                    int yearAdd, int monthAdd, int dayAdd,
                    int yearMod, int monthMod, int dayMod) {
        super(id, name, location, yearAdd, monthAdd, dayAdd, yearMod, monthMod, dayMod);
        this.dateOfDocumentCreation = LocalDate.of(yearCreate, monthCreate, dayCreate);
        this.documentNumber = documentNumber;
    }

    public LocalDate getDateOfDocumentCreation() {
        return dateOfDocumentCreation;
    }

    public void setDateOfDocumentCreation(int yearCreate, int monthCreate, int dayCreate) {
        this.dateOfDocumentCreation = LocalDate.of(yearCreate, monthCreate, dayCreate);
    }

    public static Document toCreateDocument(int id, String name, String documentNumber, String location,
                                            int yearCreate, int monthCreate, int dayCreate,
                                            int yearAdd, int monthAdd, int dayAdd,
                                            int yearMod, int monthMod, int dayMod) {

        return new Document(id, name, documentNumber, location,
                yearCreate, monthCreate, dayCreate,
                yearAdd, monthAdd, dayAdd,
                yearMod, monthMod, dayMod);
    }

    @Override
    public String toString() {
        return "Document{" +
                "id=" + getId() + 
                ", name='" + getName() + '\'' +
                ", location='" + getLocation() + '\'' +
                ", dateOfDocumentCreation=" + dateOfDocumentCreation +
                ", dateAddedToTheLibrary=" + getDateAddedToTheLibrary() +
                ", dateOfModification=" + getDateOfModification() +
                '}';
    }
}
