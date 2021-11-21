package com.library.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.library.dao.interfaces.BookDaoInterface;
import com.library.domain.models.Book;
import com.library.domain.models.Messages;
import com.library.ui.Gson.Serializer;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.*;

public class BookDao implements BookDaoInterface {
    List<Book> books = new ArrayList<>();


//    {
//        books.add(new Book(1, "paul", "12", "paul", "paul", 12,
//                12, 12, 12, 12, 12, 12, 12, 12));
//        books.add(new Book(2, "paul", "12", "paul", "paul", 12,
//                12, 12, 12, 12, 12, 12, 12, 12));
//        books.add(new Book(3, "paul", "12", "paul", "paul", 12,
//                12, 12, 12, 12, 12, 12, 12, 12));
//    }

    Scanner sc = new Scanner(System.in);
    Scanner sc1 = new Scanner(System.in);
    Scanner sc2 = new Scanner(System.in);
    Scanner sc3 = new Scanner(System.in);
    Scanner sc4 = new Scanner(System.in);


    @Override
    public void createBook(String name, String isbn, String author, String location,
                           int yearPub, int monthPub, int dayPub,
                           int yearAdd, int monthAdd, int dayAdd,
                           int yearMod, int monthMod, int dayMod) {

        int x = autoIncrementId();
        books.add(new Book(x, name, isbn, author, location, yearPub, monthPub, dayPub,
                yearAdd, monthAdd, dayAdd,
                yearMod, monthMod, dayMod));
        writeToFile();
        for (Book book : books) {
            System.out.println(book);

        }
    }

    @Override
    public Book searchBookName() {
        if (books.isEmpty()) {
            System.out.println("Книг нет");
            return null;
        }
        System.out.println(Messages.NAME);
        String name = sc3.nextLine();
        for (Book book : books) {
            if (book.getName().equalsIgnoreCase(name)) {
                System.out.println(book);
                return book;
            } else {
                System.out.println("Книги с таким названием нет(поиск)");
            }
        }
        return null;
    }

    @Override
    public void deleteBook() {
        if (books.isEmpty()) {
            System.out.println("Книг нет");
            return;
        }
        System.out.println(Messages.ID);
        int id = sc4.nextInt();
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId() == id) {
                System.out.println("Книга Name = " + books.get(i).getName() + " ID = " + books.get(i).getId() + " удалена");
                books.remove(books.get(i));
                writeToFile();
                return;
            } else {
                System.out.println("Книги с таким ID нет в библиотеке(удаление)");
            }
        }
    }

    @Override
    public void showContent() {
        if (books.isEmpty()) {
            System.out.println("Книг нет");
            return;
        }
        for (Book book : books) {
            System.out.println(book);
        }
    }

    @Override
    public void updateBook() {
        if (books.isEmpty()) {
            System.out.println("Книг нет");
            return;
        }
        System.out.println(Messages.ID);
        int id = sc.nextInt();
        boolean b = true;
        while (b) {
            if (itemAvailability(id)) {
                System.out.println(Messages.UPDATE_FOR_BOOKS);
                String s = sc1.nextLine();
                switch (s) {
                    case "1":
                        System.out.println(Messages.NAME);
                        String name = sc2.nextLine();
                        returnBook(id).setName(name);
                        break;
                    case "2":
                        System.out.println(Messages.ISBN);
                        String isbn = sc2.nextLine();
                        returnBook(id).setIsbn(isbn);
                        break;
                    case "3":
                        System.out.println(Messages.AUTHOR);
                        String author = sc2.nextLine();
                        returnBook(id).setAuthor(author);
                        break;
                    case "4":
                        System.out.println(Messages.LOCATION);
                        String location = sc2.nextLine();
                        returnBook(id).setLocation(location);
                        break;
                    case "5":
                        System.out.println(Messages.YEAR_PUB);
                        int yearPub = sc2.nextInt();
                        System.out.println(Messages.MONTH_PUB);
                        int monthPub = sc2.nextInt();
                        System.out.println(Messages.DAY_PUB);
                        int dayPub = sc2.nextInt();
                        returnBook(id).setDateOfPublication(yearPub, monthPub, dayPub);
                        break;
                    case "6":
                        System.out.println(Messages.YEAR_ADD);
                        int yearAdd = sc2.nextInt();
                        System.out.println(Messages.MONTH_ADD);
                        int monthAdd = sc2.nextInt();
                        System.out.println(Messages.DAY_ADD);
                        int dayAdd = sc2.nextInt();
                        returnBook(id).setDateAddedToTheLibrary(yearAdd, monthAdd, dayAdd);
                        break;
                    case "7":
                        System.out.println(Messages.YEAR_MOD);
                        int yearMod = sc2.nextInt();
                        System.out.println(Messages.MONTH_MOD);
                        int monthMod = sc2.nextInt();
                        System.out.println(Messages.DAY_MOD);
                        int dayMod = sc2.nextInt();
                        returnBook(id).setDateAddedToTheLibrary(yearMod, monthMod, dayMod);
                        break;
                    case "exit":
                        b = false;
                        break;
                }
                writeToFile();
            } else {
                System.out.println("Книги с таким ID нет(редактирование)");
                break;
            }
        }
    }


    boolean itemAvailability(int id) {
        for (Book book :
                books) {
            if (book.getId() == id) {
                return true;
            }
        }
        return false;
    }

    Book returnBook(int id) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId() == id) {
                return books.get(i);
            }
        }
        return null;
    }

    public void writeToFile() {
        File file = new File("src/main/resources/Books.txt");
        Gson gson = new GsonBuilder().setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new Serializer())
                .create();
        try {
            PrintWriter pw = new PrintWriter(file);
            pw.println(gson.toJson(books));
            pw.close();
        } catch (FileNotFoundException e) {
            System.out.println("файл не найден или не существует");
        }
    }


    public void deserializationOnCollection() {
        File file = new File("src/main/resources/Books.txt");
        Gson gson = new GsonBuilder().setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new Serializer())
                .create();
        if (!isEmptyFile()) {
            try {
                Reader reader = new FileReader(file);
                Type listType = new TypeToken<List<Book>>() {
                }.getType();
                books = gson.fromJson(reader, listType);
            } catch (FileNotFoundException e) {
                System.out.println("файл не найден или не существует");
            }
        }
    }

    public boolean isEmptyFile() {
        File file = new File("src/main/resources/Books.txt");
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            return br.readLine() == null;
        } catch (IOException e) {
            System.out.println("Файла нет или не найден");
        }
        return true;
    }


    public int autoIncrementId() {
        if (books.isEmpty()) {
            return 1;
        }
        Book book = Collections.max(books, new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return o1.getId() - o2.getId();
            }
        });

        return book.getId() + 1;
    }
}









