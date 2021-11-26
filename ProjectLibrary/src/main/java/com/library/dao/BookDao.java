package com.library.dao;

import com.google.gson.*;
import com.library.dao.interfaces.BookDaoInterface;
import com.library.domain.models.Book;
import com.library.domain.models.messages.Messages;
import com.library.ui.Gson.SerializerDate;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class BookDao implements BookDaoInterface {
    private final String PATH = "src/main/resources/Books.txt";
    private final String PATH1 = "src/main/resources/Books1.txt";

    Scanner sc1 = new Scanner(System.in);
    Scanner sc2 = new Scanner(System.in);


    @Override
    public void createBook(Book book) {
        writeToFile(book);
        optimizeFile();

    }

    @Override
    public void searchBookName(String name) {
        if (isEmptyFile()) {
            System.out.println("Книг нет, файл пустой(поиск)");
            return;
        }
        File file = new File(PATH);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.contains(name)) {
                    System.out.println(line);
                    return;
                }
            }
            System.out.println("такой книги нет(поиск)");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void deleteBook(Book book) {
        if (isEmptyFile()) {
            System.out.println("Книг нет, файл пустой(удаление)");
            return;
        }
        if (itemAvailability(book) == -1) {
            System.out.println("Книги с таким ID нет");
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
            System.out.println("Файла нет или не найден(книги)");
        }

    }
    @Override
    public void showContent() {
        if (isEmptyFile()) {
            System.out.println("Книг нет, файл пустой(показ всех книг)");
            return;
        }
        File file = new File(PATH);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        } catch (FileNotFoundException s) {
            System.out.println("Файла нет или не найден(книги)");
        }
    }
    @Override
    public void updateBook(Book book) {
        if (isEmptyFile()) {
            System.out.println("Книг нет, файл пустой(редактирование)");
            return;
        }
        if (itemAvailability(book) == -1) {
            System.out.println("Книги с таким ID нет");
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
            e.printStackTrace();
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
                    System.out.println(Messages.AUTHOR);
                    String author = sc2.nextLine();
                    book.setAuthor(author);
                    break;
                case "4":
                    System.out.println(Messages.LOCATION);
                    String location = sc2.nextLine();
                    book.setLocation(location);
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
            System.out.println("файл не найден или не существует(книги)");
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
            e.printStackTrace();
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
            ex.printStackTrace();
        }
    }
}









