package com.library.ui;

import com.library.domain.*;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Map<String, Book> books = new HashMap<>();
        Map<String, Document> documents = new HashMap<>();
        Map<String, PatentDocument> patentDocuments = new HashMap<>();
        Map<String, Magazine> magazine = new HashMap<>();
        books.put("The Lord Of The Rings", Book.toCreateBook(0, "The Lord Of The Rings", "q123", "Tolkien",
                "England", 1954, 10, 10, 2020, 11, 11,
                2021, 12, 12));
        System.out.println(books.get("The Lord Of The Rings"));
        documents.put("constitution", Document.toCreateDocument(1, "constitution", "001",
                "Russian Federation", 1993, 12, 12, 2020, 10, 10,
                2021, 11, 11));
        System.out.println(documents.get("constitution"));
        patentDocuments.put("seat belts", PatentDocument.toCreatePatentDocument(2, "seat belts",
                "002", "Nils Ivar Bohlin", "Sweden", 2020, 1, 1, 2021,
                2, 2));
        System.out.println(patentDocuments.get("seat belts"));
        magazine.put("playboy", Magazine.toCreateMagazine(3, "playboy", "USA", 1953, 10,
                1, 2020, 10, 2, 2021, 11, 20));
        System.out.println(magazine.get("playboy"));



    }
}

