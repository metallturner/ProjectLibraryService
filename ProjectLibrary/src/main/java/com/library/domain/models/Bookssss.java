package com.library.domain.models;

import java.time.LocalDate;
import java.util.Date;

public class Bookssss {
    LocalDate dateOfPublication;
    String isbn;
    String author;
    int id;
    String name;
    String location;
    LocalDate dateAddedToTheLibrary;
    LocalDate dateOfModification;
    LocalDate year;


    public Bookssss(LocalDate dateOfPublication, String isbn, String author, int id, String name, String location, LocalDate dateAddedToTheLibrary, LocalDate dateOfModification, LocalDate year) {
        this.dateOfPublication = dateOfPublication;
        this.isbn = isbn;
        this.author = author;
        this.id = id;
        this.name = name;
        this.location = location;
        this.dateAddedToTheLibrary = dateAddedToTheLibrary;
        this.dateOfModification = dateOfModification;
        this.year = year;
    }

    public LocalDate getDateOfPublication() {
        return dateOfPublication;
    }

    public void setDateOfPublication(LocalDate dateOfPublication) {
        this.dateOfPublication = dateOfPublication;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDate getDateAddedToTheLibrary() {
        return dateAddedToTheLibrary;
    }

    public void setDateAddedToTheLibrary(LocalDate dateAddedToTheLibrary) {
        this.dateAddedToTheLibrary = dateAddedToTheLibrary;
    }

    public LocalDate getDateOfModification() {
        return dateOfModification;
    }

    public void setDateOfModification(LocalDate dateOfModification) {
        this.dateOfModification = dateOfModification;
    }

    public LocalDate getYear() {
        return year;
    }

    public void setYear(LocalDate year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Bookssss{" +
                "dateOfPublication=" + dateOfPublication +
                ", isbn='" + isbn + '\'' +
                ", author='" + author + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", dateAddedToTheLibrary=" + dateAddedToTheLibrary +
                ", dateOfModification=" + dateOfModification +
                ", year=" + year +
                '}';
    }
}
