package com.library.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.library.dao.interfaces.BookDaoInterface;
import com.library.dao.interfaces.ClassGetAuthorAndLocationInterface;
import com.library.domain.models.Book;
import com.library.domain.models.messages.Messages;
import com.library.Gson.SerializerDate;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class BookDao implements BookDaoInterface {
    private static final Logger log = Logger.getLogger(BookDao.class);
    private final String PATH = "C:\\Users\\paul\\Desktop\\NetCracker lessons\\MVCProjectLibraryNetcracker\\src\\main\\resources\\Books.txt";
    private final String PATH1 = "C:\\Users\\paul\\Desktop\\NetCracker lessons\\MVCProjectLibraryNetcracker\\src\\main\\resources\\Books1.txt";





    Scanner sc1 = new Scanner(System.in);
    Scanner sc2 = new Scanner(System.in);



    @Override
    public void createBook(Book book) {
        writeToFile(book);
        optimizeFile();

    }

    @Override
    public Book getById(int id) {
        Gson gson = new Gson();
//                .registerTypeAdapter(LocalDate.class, new SerializerDate())
//                .create();
        if (isEmptyFile()) {
            System.out.println("Книг нет, файл пустой(поиск)");
            log.error("Книг нет, файл пустой(поиск)");
            return null;
        }
        File file = new File(PATH);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Book book = gson.fromJson(line, Book.class);
                if (book.getId()==id) {
                    System.out.println(book);
                    return book;
                }
            }
            System.out.println("такой книги нет(поиск)");
            log.error("такой книги нет(поиск)");
        } catch (FileNotFoundException e) {
            log.error("Файла нет или не найден(книги)");
        }
        return null;
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
            Gson gson = new Gson();
                  //  .registerTypeAdapter(LocalDate.class, new SerializerDate())
                   // .create();
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
    public List<Book> showContent() {
        Gson gson = new Gson(); //new GsonBuilder()
//                .registerTypeAdapter(LocalDate.class, new SerializerDate())
//                .create();
        if (isEmptyFile()) {
            System.out.println("Книг нет, файл пустой(показ всех книг)");
            log.error("Книг нет, файл пустой(показ всех книг)");
            return null;
        }
        File file = new File(PATH);
        List<Book> list = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                Book book = gson.fromJson(scanner.nextLine(), Book.class);
                list.add(book);
            }
            System.out.println(list);
            return list;
        } catch (FileNotFoundException s) {
            System.out.println("Файла нет или не найден(книги)");
            log.error("Файла нет или не найден(книги)");

        }
        return null;
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
            Gson gson = new Gson();
//                    .registerTypeAdapter(LocalDate.class, new SerializerDate())
//                    .create();
            while (file.hasNextLine()) {
                String line = file.nextLine();
                Book book1 = gson.fromJson(line, Book.class);
                if (book.getId() != book1.getId()) {
                    writer.write(line);
                    writer.write("\n");
                }
                if (book.getId() == book1.getId()) {
                    writer.write(gson.toJson(book));
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

@Override
    public int itemAvailability(Book book) {
        try (Scanner file = new Scanner(new File(PATH))) {
            Gson gson = new Gson();
                  //  .registerTypeAdapter(LocalDate.class, new SerializerDate())
                   // .create();
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


    private void writeToFile(Book book) {
        book.setId(autoIncrementId());
        File file = new File(PATH);
        Gson gson = new Gson();
              //  .registerTypeAdapter(LocalDate.class, new SerializerDate())
               // .create();
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
        Gson gson = new Gson();
               // .registerTypeAdapter(LocalDate.class, new SerializerDate())
               // .create();
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









