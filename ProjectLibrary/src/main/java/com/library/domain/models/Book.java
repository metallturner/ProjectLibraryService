package com.library.domain.models;


import java.time.LocalDate;


public class Book extends Literature {
    private LocalDate dateOfPublication;
    private String isbn;
    private transient Author author;
    private int authorId;


    public Book() {
    }

    public Book(int id, String name, int locationId,
                int yearPub, int monthPub, int dayPub,
                int yearAdd, int monthAdd, int dayAdd,
                int yearMod, int monthMod, int dayMod,
                String isbn, int authorId) {
        super(id, name, locationId, yearAdd, monthAdd, dayAdd, yearMod, monthMod, dayMod);
        this.dateOfPublication = LocalDate.of(yearPub, monthPub, dayPub);
        this.isbn = isbn;
        this.authorId = authorId;

    }


    public LocalDate getDateOfPublication() {
        return dateOfPublication;
    }

    public void setDateOfPublication(int yearPub, int monthPub, int dayPub) {
        this.dateOfPublication = LocalDate.of(yearPub, monthPub, dayPub);
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




