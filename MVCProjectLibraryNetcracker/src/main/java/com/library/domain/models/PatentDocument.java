package com.library.domain.models;

public class PatentDocument extends Literature {
    private Author author;
    private int authorId;
    private String patentNumber;

    public PatentDocument(){

    }


    public PatentDocument(int id, String name, String patentNumber, int authorId, int locationId,
                          String dataAdd, String dataMod) {
        super(id, name, locationId, dataAdd, dataMod);
        this.authorId = authorId;
        this.patentNumber = patentNumber;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getPatentNumber() {
        return patentNumber;
    }

    public void setPatentNumber(String patentNumber) {
        this.patentNumber = patentNumber;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    @Override
    public String toString() {
        return "PatentDocument{" + "\n"+
                "id = " + getId() + "\n"+
                "name = '" + getName()+ "\n" + '\'' +
                "patentNumber = '" + patentNumber + '\''+ "\n" +
                "authorId = '" + authorId + '\'' + "\n"+
                "author = '" + author + '\''+ "\n" +
                "locationId = '" + getLocationId() + '\''+ "\n" +
                "location = '" + getLocation() + '\'' + "\n"+
                "dateAddedToTheLibrary = " + getDateAddedToTheLibrary()+ "\n" +
                "dateOfModification = " + getDateOfModification() +
                '}';
    }
}
