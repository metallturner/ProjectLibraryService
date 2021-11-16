package com.library.domain;

import java.time.LocalDate;

public class Book extends Literature {
    private LocalDate dateOfPublication;
    private String isbn;
    private String author;

    public Book(int id, String name, String isbn, String author, String location,
                int yearPub, int monthPub, int dayPub,
                int yearAdd, int monthAdd, int dayAdd,
                int yearMod, int monthMod, int dayMod) {
        super(id, name, location, yearAdd, monthAdd, dayAdd, yearMod, monthMod, dayMod);
        this.dateOfPublication = LocalDate.of(yearPub, monthPub, dayPub);
        this.isbn = isbn;
        this.author = author;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public static Book toCreateBook(int id, String name, String isbn, String author, String location,
                                    int yearPub, int monthPub, int dayPub,
                                    int yearAdd, int monthAdd, int dayAdd,
                                    int yearMod, int monthMod, int dayMod) {

        return new Book(id, name, isbn, author, location, yearPub, monthPub,
                dayPub, yearAdd, monthAdd, dayAdd, yearMod, monthMod, dayMod);
    }

    @Override
    public String toString() {
        return "Literature{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                "isbn='" + isbn + '\'' +
                ", author='" + author + '\'' +
                ", location='" + getLocation() + '\'' +
                ", dateOfPublication=" + dateOfPublication +
                ", dateAddedToTheLibrary=" + getDateAddedToTheLibrary() +
                ", dateOfModification=" + getDateOfModification() +
                '}';
    }
}




