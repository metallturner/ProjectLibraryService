package com.library.domain.models;

public class PatentDocument extends Literature {
    private String author;
    private String patentNumber;

    public PatentDocument(){

    }


    public PatentDocument(int id, String name, String patentNumber, String author, String location,
                          int yearAdd, int monthAdd, int dayAdd,
                          int yearMod, int monthMod, int dayMod) {
        super(id, name, location, yearAdd, monthAdd, dayAdd, yearMod, monthMod, dayMod);
        this.author = author;
        this.patentNumber = patentNumber;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPatentNumber() {
        return patentNumber;
    }

    public void setPatentNumber(String patentNumber) {
        this.patentNumber = patentNumber;
    }

    public static PatentDocument toCreatePatentDocument(int id, String name, String patentNumber,
                                                        String author, String location,
                                                        int yearAdd, int monthAdd, int dayAdd,
                                                        int yearMod, int monthMod, int dayMod) {

        return new PatentDocument(id, name, patentNumber, author, location,
                yearAdd, monthAdd, dayAdd,
                yearMod, monthMod, dayMod);
    }

    @Override
    public String toString() {
        return "PatentDocument{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", patentNumber='" + patentNumber + '\'' +
                ", author='" + author + '\'' +
                ", location='" + getLocation() + '\'' +
                ", dateAddedToTheLibrary=" + getDateAddedToTheLibrary() +
                ", dateOfModification=" + getDateOfModification() +
                '}';
    }
}
