package com.library.dao;

import com.google.gson.*;
import com.library.dao.interfaces.BookDaoInterface;
import com.library.dao.interfaces.ClassGetAuthorAndLocationInterface;
import com.library.domain.models.Book;
import com.library.domain.models.messages.Messages;
import com.library.ui.Gson.SerializerDate;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

@Component
public class BookDao implements BookDaoInterface {
    private static final Logger log = Logger.getLogger(BookDao.class);
    private final String PATH = "src/main/resources/Books.txt";
    private final String PATH1 = "src/main/resources/Books1.txt";
    ClassGetAuthorAndLocationInterface classGetAuthorAndLocationInterface;

    @Autowired
    public BookDao(@Qualifier("classGetAuthorAndLocation") ClassGetAuthorAndLocationInterface classGetAuthorAndLocationInterface) {
        this.classGetAuthorAndLocationInterface = classGetAuthorAndLocationInterface;
    }

    Scanner sc1 = new Scanner(System.in);
    Scanner sc2 = new Scanner(System.in);


    @Override
    public void createBook(Book book) {
        writeToFile(book);
        optimizeFile();

    }

    @Override
    public void searchBookName(String name) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new SerializerDate())
                .create();
        if (isEmptyFile()) {
            System.out.println("Книг нет, файл пустой(поиск)");
            log.error("Книг нет, файл пустой(поиск)");
            return;
        }
        File file = new File(PATH);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Book book = gson.fromJson(line, Book.class);

                if (line.contains(name)) {
                    book.setAuthor(classGetAuthorAndLocationInterface.getAuthor(book.getAuthorId()));
                    book.setLocation(classGetAuthorAndLocationInterface.getLocation(book.getLocationId()));
                    System.out.println(book);
                    return;
                }
            }
            System.out.println("такой книги нет(поиск)");
            log.error("такой книги нет(поиск)");
        } catch (FileNotFoundException e) {
            log.error("Файла нет или не найден(книги)");
        }
    }

    @Override
    public void deleteBook(Book book) {
        if (isEmptyFile()) {
            System.out.println("Книг нет, файл пустой(удаление)");
            log.error("Книг нет, файл пустой(поиск)");
            return;
        }
        if (itemAvailability(book) == -1) {
            System.out.println("Книги с таким ID нет");
            log.error("Книги с таким ID нет");
            return;
        }
        try {
            Scanner file = new Scanner(new File(PATH));
            PrintWriter writer = new PrintWriter(PATH1);
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(LocalDate.class, new SerializerDate())
                    .create();
            while (file.hasNextLine()) {
                String line = file.nextLine();
                Book book1 = gson.fromJson(line, Book.class);
                if (book.getId() != book1.getId()) {
                    writer.write(line);
                    writer.write("\n");
                }
            }
            file.close();
            writer.close();
            File file1 = new File(PATH);
            File file2 = new File(PATH1);
            file1.delete();
            file2.renameTo(file1);

        } catch (FileNotFoundException ex) {
            log.error("Файла нет или не найден(книги)");
        }

    }

    @Override
    public void showContent() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new SerializerDate())
                .create();
        if (isEmptyFile()) {
            System.out.println("Книг нет, файл пустой(показ всех книг)");
            log.error("Книг нет, файл пустой(показ всех книг)");
            return;
        }
        File file = new File(PATH);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                Book book = gson.fromJson(scanner.nextLine(), Book.class);
                book.setAuthor(classGetAuthorAndLocationInterface.getAuthor(book.getAuthorId()));
                book.setLocation(classGetAuthorAndLocationInterface.getLocation(book.getLocationId()));
                System.out.println(book);
            }
        } catch (FileNotFoundException s) {
            System.out.println("Файла нет или не найден(книги)");
            log.error("Файла нет или не найден(книги)");

        }
    }

    @Override
    public void updateBook(Book book) {
        if (isEmptyFile()) {
            System.out.println("Книг нет, файл пустой(редактирование)");
            log.error("Книг нет, файл пустой(редактирование)");
            return;
        }
        if (itemAvailability(book) == -1) {
            System.out.println("Книги с таким ID нет");
            log.error("Книги с таким ID нет");
            return;
        }
        try {

            Scanner file = new Scanner(new File(PATH));
            PrintWriter writer = new PrintWriter(PATH1);
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(LocalDate.class, new SerializerDate())
                    .create();
            Book updateBook;
            while (file.hasNextLine()) {
                String line = file.nextLine();
                Book book1 = gson.fromJson(line, Book.class);
                if (book.getId() != book1.getId()) {
                    writer.write(line);
                    writer.write("\n");
                }
                if (book.getId() == book1.getId()) {
                    updateBook = gson.fromJson(line, Book.class);
                    writer.write(gson.toJson(updateBookFromJason(updateBook)));
                    writer.write("\n");
                }
            }
            file.close();
            writer.close();
            File file1 = new File(PATH);
            File file2 = new File(PATH1);
            file1.delete();
            file2.renameTo(file1);

        } catch (FileNotFoundException ex) {
            System.out.println("Файла нет или не найден(книги)");
            log.error("Файла нет или не найден(книги)");
        }
    }


    private int itemAvailability(Book book) {
        try (Scanner file = new Scanner(new File(PATH))) {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(LocalDate.class, new SerializerDate())
                    .create();
            while (file.hasNextLine()) {
                String line = file.nextLine();
                Book book1 = gson.fromJson(line, Book.class);
                if (book.getId() == book1.getId()) {
                    return 1;
                }
            }

        } catch (FileNotFoundException e) {
            log.error("Файла нет или не найден(книги)");
        }
        return -1;
    }

    private Book updateBookFromJason(Book book) {
        boolean b = true;
        while (b) {
            System.out.println(book);
            System.out.println(Messages.UPDATE_FOR_BOOKS);
            String s = sc1.nextLine();
            switch (s) {
                case "1":
                    System.out.println(Messages.NAME);
                    String name = sc2.nextLine();
                    book.setName(name);
                    break;
                case "2":
                    System.out.println(Messages.ISBN);
                    String isbn = sc2.nextLine();
                    book.setIsbn(isbn);
                    break;
                case "3":
                    System.out.println(Messages.ID_AUTHOR);
                    int authorId = sc2.nextInt();
                    book.setAuthorId(authorId);
                    break;
                case "4":
                    System.out.println(Messages.ID_LOCATION);
                    int locationId = sc2.nextInt();
                    book.setLocationId(locationId);
                    break;
                case "5":
                    System.out.println(Messages.YEAR_PUB);
                    int yearPub = sc2.nextInt();
                    System.out.println(Messages.MONTH_PUB);
                    int monthPub = sc2.nextInt();
                    System.out.println(Messages.DAY_PUB);
                    int dayPub = sc2.nextInt();
                    book.setDateOfPublication(yearPub, monthPub, dayPub);
                    break;
                case "6":
                    System.out.println(Messages.YEAR_ADD);
                    int yearAdd = sc2.nextInt();
                    System.out.println(Messages.MONTH_ADD);
                    int monthAdd = sc2.nextInt();
                    System.out.println(Messages.DAY_ADD);
                    int dayAdd = sc2.nextInt();
                    book.setDateAddedToTheLibrary(yearAdd, monthAdd, dayAdd);
                    break;
                case "7":
                    System.out.println(Messages.YEAR_MOD);
                    int yearMod = sc2.nextInt();
                    System.out.println(Messages.MONTH_MOD);
                    int monthMod = sc2.nextInt();
                    System.out.println(Messages.DAY_MOD);
                    int dayMod = sc2.nextInt();
                    book.setDateAddedToTheLibrary(yearMod, monthMod, dayMod);
                    break;
                case "exit":
                    b = false;
                    break;
            }
        }
        return book;
    }

    private void writeToFile(Book book) {
        book.setId(autoIncrementId());
        File file = new File(PATH);
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new SerializerDate())
                .create();
        try (FileWriter pw = new FileWriter(file, true)) {
            pw.write(gson.toJson(book));
            pw.write("\n");
        } catch (IOException e) {
            log.error("Файла нет или не найден(книги)");
        }
    }

    private boolean isEmptyFile() {
        File file1 = new File(PATH);
        try (BufferedReader br = new BufferedReader(new FileReader(file1))) {
            return br.readLine() == null;
        } catch (IOException e) {
            System.out.println("Файла нет или не найден(книги)");
        }
        return true;
    }

    private int autoIncrementId() {
        if (isEmptyFile()) {
            return 1;
        }
        int x = 0;
        File file = new File(PATH);
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new SerializerDate())
                .create();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                Book book = gson.fromJson(scanner.nextLine(), Book.class);
                if (book.getId() >= x) {
                    x = book.getId();
                }
            }
        } catch (FileNotFoundException e) {
            log.error("Файла нет или не найден(книги)");
        }
        return x + 1;
    }

    private void optimizeFile() {
        try {

            Scanner file = new Scanner(new File(PATH));
            PrintWriter writer = new PrintWriter(PATH1);

            while (file.hasNext()) {
                String line = file.nextLine();
                if (!line.isEmpty()) {
                    writer.write(line);
                    writer.write("\n");
                }
            }
            file.close();
            writer.close();
            File file1 = new File(PATH);
            File file2 = new File(PATH1);
            file1.delete();
            file2.renameTo(file1);

        } catch (FileNotFoundException ex) {
            log.error("Файла нет или не найден(книги)");
        }
    }
}









