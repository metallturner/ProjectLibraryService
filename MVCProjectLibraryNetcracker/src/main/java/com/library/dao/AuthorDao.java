package com.library.dao;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.library.dao.interfaces.AuthorDaoInterface;
import com.library.domain.models.Author;
import com.library.domain.models.messages.Messages;
import com.library.Gson.SerializerDate;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
@Component
public class AuthorDao implements AuthorDaoInterface {
    private static final Logger log = Logger.getLogger(AuthorDao.class);
    private final String PATH = "C:\\Users\\paul\\Desktop\\NetCracker lessons\\MVCProjectLibraryNetcracker\\src\\main\\resources\\Authors";
    private final String PATH1 = "C:\\Users\\paul\\Desktop\\NetCracker lessons\\MVCProjectLibraryNetcracker\\src\\main\\resources\\Authors1";

    Scanner sc1 = new Scanner(System.in);
    Scanner sc2 = new Scanner(System.in);


    @Override
    public void createAuthor(Author author) {
        writeToFile(author);
        optimizeFile();

    }

    @Override
    public Author getById(int id) {
        Gson gson = new Gson();
                //.registerTypeAdapter(LocalDate.class, new SerializerDate())
                //.create();
        if (isEmptyFile()) {
            System.out.println("Авторов нет, файл пустой(поиск)");
            log.error("Авторов нет, файл пустой(поиск)");
            return null;
        }
        File file = new File(PATH);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Author author = gson.fromJson(line, Author.class);
                if (author.getId()==id) {
                    return author;
                }
            }
            System.out.println("Такого автора нет(поиск)");
            log.error("Такого автора нет(поиск)");
        } catch (FileNotFoundException e) {
            log.error("Файла нет или не найден(авторы)");
        }
        return null;
    }

    @Override
    public void deleteAuthor(Author author) {
        if (isEmptyFile()) {
            System.out.println("Авторов нет, файл пустой(удаление)");
            log.error("Авторов нет, файл пустой(удалени)");
            return;
        }
        if (itemAvailability(author) == -1) {
            System.out.println("Автора с таким ID нет");
            log.error("Автора с таким ID нет");
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
                Author author1 = gson.fromJson(line, Author.class);
                if (author.getId() != author1.getId()) {
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
    public List<Author> showContent() {
        Gson gson = new Gson();
               // .registerTypeAdapter(LocalDate.class, new SerializerDate())
               // .create();
        if (isEmptyFile()) {
            System.out.println("Авторов нет, файл пустой(показ всех авторов)");
            log.error("Авторов нет, файл пустой(показ всех авторов)");
            return null;
        }
        File file = new File(PATH);
        List<Author> authors = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                Author author = gson.fromJson(scanner.nextLine(), Author.class);
                authors.add(author);
            }
            return authors;
        } catch (FileNotFoundException s) {
            System.out.println("Файла нет или не найден(авторы)");
            log.error("Файла нет или не найден(авторы)");

        }
        return null;
    }

    @Override
    public void updateAuthor(Author author) {
        if (isEmptyFile()) {
            System.out.println("авторов нет, файл пустой(редактирование)");
            log.error("авторов нет, файл пустой(редактирование)");
            return;
        }
        if (itemAvailability(author) == -1) {
            System.out.println("автора с таким ID нет");
            log.error("автора с таким ID нет");
            return;
        }
        try {

            Scanner file = new Scanner(new File(PATH));
            PrintWriter writer = new PrintWriter(PATH1);
            Gson gson = new Gson();
                   // .registerTypeAdapter(LocalDate.class, new SerializerDate())
                   // .create();
            while (file.hasNextLine()) {
                String line = file.nextLine();
                Author author1 = gson.fromJson(line, Author.class);
                if (author.getId() != author1.getId()) {
                    writer.write(line);
                    writer.write("\n");
                }
                if (author.getId() == author1.getId()) {
                    writer.write(gson.toJson(author));
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
            System.out.println("Файла нет или не найден(авторы)");
            log.error("Файла нет или не найден(авторы)");
        }
    }


    public int itemAvailability(Author author) {
        try (Scanner file = new Scanner(new File(PATH))) {
            Gson gson = new Gson();
                  //  .registerTypeAdapter(LocalDate.class, new SerializerDate())
                  //  .create();
            while (file.hasNextLine()) {
                String line = file.nextLine();
                Author author1 = gson.fromJson(line, Author.class);
                if (author.getId() == author1.getId()) {
                    return 1;
                }
            }

        } catch (FileNotFoundException e) {
            log.error("Файла нет или не найден(авторы)");
        }
        return -1;
    }



    private void writeToFile(Author author) {
        author.setId(autoIncrementId());
        File file = new File(PATH);
        Gson gson = new Gson();
                //.registerTypeAdapter(LocalDate.class, new SerializerDate())
               // .create();
        try (FileWriter pw = new FileWriter(file, true)) {
            pw.write(gson.toJson(author));
            pw.write("\n");
        } catch (IOException e) {
            log.error("Файла нет или не найден(авторы)");
        }
    }

    private boolean isEmptyFile() {
        File file1 = new File(PATH);
        try (BufferedReader br = new BufferedReader(new FileReader(file1))) {
            return br.readLine() == null;
        } catch (IOException e) {
            System.out.println("Файла нет или не найден(авторы)");
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
                //.registerTypeAdapter(LocalDate.class, new SerializerDate())
               // .create();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                Author author = gson.fromJson(scanner.nextLine(), Author.class);
                if (author.getId() >= x) {
                    x = author.getId();
                }
            }
        } catch (FileNotFoundException e) {
            log.error("Файла нет или не найден(авторы)");
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
            log.error("Файла нет или не найден(авторы)");
        }
    }

}
