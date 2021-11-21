package com.library.domain.models;

import java.time.LocalDate;

public class Magazine extends Literature {
    private LocalDate dateOfPublication;


    public Magazine(int id, String name, String location,
                    int yearPub, int monthPub, int dayPub,
                    int yearAdd, int monthAdd, int dayAdd,
                    int yearMod, int monthMod, int dayMod) {
        super(id, name, location, yearAdd, monthAdd, dayAdd, yearMod, monthMod, dayMod);
        this.dateOfPublication = LocalDate.of(yearPub, monthPub, dayPub);
    }

    public LocalDate getDateOfPublication() {
        return dateOfPublication;
    }

    public void setDateOfPublication(int yearPub, int monthPub, int dayPub) {
        this.dateOfPublication = LocalDate.of(yearPub, monthPub, dayPub);
    }

    public static Magazine toCreateMagazine(int id, String name, String location,
                                            int yearPub, int monthPub, int dayPub,
                                            int yearAdd, int monthAdd, int dayAdd,
                                            int yearMod, int monthMod, int dayMod) {

        return new Magazine(id, name, location,
                yearPub, monthPub, dayPub,
                yearAdd, monthAdd, dayAdd,
                yearMod, monthMod, dayMod);
    }

    @Override
    public String toString() {
        return "Magazine{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", location='" + getLocation() + '\'' +
                ", dateOfPublication=" + dateOfPublication +
                ", dateAddedToTheLibrary=" + getDateAddedToTheLibrary() +
                ", dateOfModification=" + getDateOfModification() +
                '}';
    }
}
