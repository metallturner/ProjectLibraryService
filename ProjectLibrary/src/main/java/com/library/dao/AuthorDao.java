package com.library.dao;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.library.dao.interfaces.AuthorDaoInterface;
import com.library.domain.models.Author;
import com.library.domain.models.messages.Messages;
import com.library.ui.Gson.SerializerDate;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.*;
import java.time.LocalDate;
import java.util.Scanner;
@Component
public class AuthorDao implements AuthorDaoInterface {
    private static final Logger log = Logger.getLogger(AuthorDao.class);
    private final String PATH = "src/main/resources/Authors";
    private final String PATH1 = "src/main/resources/Authors1";

    Scanner sc1 = new Scanner(System.in);
    Scanner sc2 = new Scanner(System.in);


    @Override
    public void createAuthor(Author author) {
        writeToFile(author);
        optimizeFile();

    }

    @Override
    public void searchAuthorName(String name) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new SerializerDate())
                .create();
        if (isEmptyFile()) {
            System.out.println("Авторов нет, файл пустой(поиск)");
            log.error("Авторов нет, файл пустой(поиск)");
            return;
        }
        File file = new File(PATH);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Author author = gson.fromJson(line, Author.class);
                if (line.contains(name)) {
                    System.out.println(author);
                    return;
                }
            }
            System.out.println("Такого автора нет(поиск)");
            log.error("Такого автора нет(поиск)");
        } catch (FileNotFoundException e) {
            log.error("Файла нет или не найден(авторы)");
        }
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
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(LocalDate.class, new SerializerDate())
                    .create();
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
    public void showContent() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new SerializerDate())
                .create();
        if (isEmptyFile()) {
            System.out.println("Авторов нет, файл пустой(показ всех авторов)");
            log.error("Авторов нет, файл пустой(показ всех авторов)");
            return;
        }
        File file = new File(PATH);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                Author author = gson.fromJson(scanner.nextLine(), Author.class);
                System.out.println(author);
            }
        } catch (FileNotFoundException s) {
            System.out.println("Файла нет или не найден(авторы)");
            log.error("Файла нет или не найден(авторы)");

        }
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
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(LocalDate.class, new SerializerDate())
                    .create();
            Author updateAuthor;
            while (file.hasNextLine()) {
                String line = file.nextLine();
                Author author1 = gson.fromJson(line, Author.class);
                if (author.getId() != author1.getId()) {
                    writer.write(line);
                    writer.write("\n");
                }
                if (author.getId() == author1.getId()) {
                    updateAuthor = gson.fromJson(line, Author.class);
                    writer.write(gson.toJson(updateAuthorFromJason(updateAuthor)));
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


    private int itemAvailability(Author author) {
        try (Scanner file = new Scanner(new File(PATH))) {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(LocalDate.class, new SerializerDate())
                    .create();
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

    private Author updateAuthorFromJason(Author author) {
        boolean b = true;
        while (b) {
            System.out.println(author);
            System.out.println(Messages.UPDATE_FOR_AUTHORS);
            String s = sc1.nextLine();
            switch (s) {
                case "1":
                    System.out.println(Messages.NAME);
                    String name = sc2.nextLine();
                    author.setName(name);
                    break;
                case "exit":
                    b = false;
                    break;
            }
        }
        return author;
    }

    private void writeToFile(Author author) {
        author.setId(autoIncrementId());
        File file = new File(PATH);
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new SerializerDate())
                .create();
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
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new SerializerDate())
                .create();
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
