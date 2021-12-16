package com.library.domain.models;


import java.time.LocalDate;
import java.util.Date;


public class Book extends Literature {
    private MyDate dateOfPublication;
    private String isbn;
    private Author author;
    private int authorId;


    public Book() {
    }

    public Book(int id, String name, int locationId,
                String dataPub, String dataAdd, String dataMod,
                String isbn, int authorId) {
        super(id, name, locationId, dataAdd, dataMod);
        this.dateOfPublication =new MyDate(dataPub);
        this.isbn = isbn;
        this.authorId = authorId;

    }


    public MyDate getDateOfPublication() {
        return dateOfPublication;
    }

    public void setDateOfPublication(MyDate dateOfPublication) {
        this.dateOfPublication = dateOfPublication;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    @Override
    public String toString() {
        return "Book{" + "\n"+
                "id = " + getId()+ "\n" +
                "name = '" + getName() + '\''+ "\n" +
                "isbn = '" + isbn + '\''+ "\n" +
                "authorId = '" + authorId + '\'' + "\n"+
                "author = '" + author + '\'' + "\n"+
                "locationId = '" + getLocationId() + '\''+ "\n" +
                "location = '" + getLocation() + '\''+ "\n" +
                "dateOfPublication = " + dateOfPublication + "\n"+
                "dateAddedToTheLibrary = " + getDateAddedToTheLibrary() + "\n"+
                "dateOfModification = " + getDateOfModification() +
                '}';
    }
}




