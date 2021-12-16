package com.library.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.library.dao.interfaces.ClassGetAuthorAndLocationInterface;
import com.library.dao.interfaces.DocumentsDaoInterface;
import com.library.domain.models.Document;
import com.library.domain.models.messages.Messages;
import com.library.Gson.SerializerDate;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class DocumentDao implements DocumentsDaoInterface {
    private static final Logger log = Logger.getLogger(DocumentDao.class);
    private final String PATH = "C:\\Users\\paul\\Desktop\\NetCracker lessons\\MVCProjectLibraryNetcracker\\src\\main\\resources\\Documents.txt";
    private final String PATH1 = "C:\\Users\\paul\\Desktop\\NetCracker lessons\\MVCProjectLibraryNetcracker\\src\\main\\resources\\Documents1.txt";
    ClassGetAuthorAndLocationInterface classGetAuthorAndLocationInterface;

    @Autowired
    public DocumentDao(@Qualifier("classGetAuthorAndLocation") ClassGetAuthorAndLocationInterface classGetAuthorAndLocationInterface) {
        this.classGetAuthorAndLocationInterface = classGetAuthorAndLocationInterface;
    }

    Scanner sc1 = new Scanner(System.in);
    Scanner sc2 = new Scanner(System.in);


    @Override
    public void createDocument(Document document) {
        writeToFile(document);
        optimizeFile();

    }

    @Override
    public Document getById(int id) {
        Gson gson = new Gson();
                //.registerTypeAdapter(LocalDate.class, new SerializerDate())
                //.create();
        if (isEmptyFile()) {
            System.out.println("Документов нет, файл пустой(поиск)");
            log.error("Документов нет, файл пустой(поиск)");
            return null;
        }
        File file = new File(PATH);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Document document = gson.fromJson(line, Document.class);
                if (document.getId() == id) {
                    document.setLocation(classGetAuthorAndLocationInterface.getLocation(document.getLocationId()));
                    System.out.println(document);
                    return document;
                }
            }
            System.out.println("такого документа нет(поиск)");
            log.error("такого документа нет(поиск)");
        } catch (FileNotFoundException e) {
            System.out.println("Файла нет или не найден(документы)");
            log.error("Файла нет или не найден(документы)");
        }
        return null;
    }

    @Override
    public void deleteDocument(Document document) {
        if (isEmptyFile()) {
            System.out.println("Документов нет, файл пустой(удаление)");
            log.error("Документов нет, файл пустой(удаление)");
            return;
        }
        if (itemAvailability(document) == -1) {
            System.out.println("Документа с таким ID нет");
            log.error("Документа с таким ID нет");
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
                Document document1 = gson.fromJson(line, Document.class);
                if (document.getId() != document1.getId()) {
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
            System.out.println("Файла нет или не найден(документы)");
            log.error("Файла нет или не найден(документы)");
        }

    }

    @Override
    public List<Document> showContent() {
        Gson gson = new Gson();
               // .registerTypeAdapter(LocalDate.class, new SerializerDate())
               // .create();
        if (isEmptyFile()) {
            System.out.println("Документов нет, файл пустой(показ всех документов)");
            log.error("Документов нет, файл пустой(показ всех документов)");
            return null;
        }
        File file = new File(PATH);
        List<Document> docs = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                Document document = gson.fromJson(scanner.nextLine(), Document.class);
                document.setLocation(classGetAuthorAndLocationInterface.getLocation(document.getLocationId()));
                docs.add(document);
            }
            return docs;
        } catch (FileNotFoundException s) {
            System.out.println("Файла нет или не найден(документы)");
            log.error("Файла нет или не найден(документы)");
        }
        return null;
    }

    @Override
    public void updateDocument(Document document) {
        if (isEmptyFile()) {
            System.out.println("Документов нет, файл пустой(редактирование)");
            log.error("Документов нет, файл пустой(редактирование)");
            return;
        }
        if (itemAvailability(document) == -1) {
            System.out.println("Документа с таким ID нет");
            log.error("Документа с таким ID нет");
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
                Document document1 = gson.fromJson(line, Document.class);
                if (document.getId() != document1.getId()) {
                    writer.write(line);
                    writer.write("\n");
                }
                if (document.getId() == document1.getId()) {
                    writer.write(gson.toJson(document));
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
            System.out.println("Файла нет или не найден(документы)");
            log.error("Файла нет или не найден(документы)");
        }
    }


    public int itemAvailability(Document document) {
        try (Scanner file = new Scanner(new File(PATH))) {
            Gson gson = new Gson();
                    //.registerTypeAdapter(LocalDate.class, new SerializerDate())
                   // .create();
            while (file.hasNextLine()) {
                String line = file.nextLine();
                Document document1 = gson.fromJson(line, Document.class);
                if (document.getId() == document1.getId()) {
                    return 1;
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("Файла нет или не найден(документы)");
            log.error("Файла нет или не найден(документы)");
        }
        return -1;
    }


    private void writeToFile(Document document) {
        document.setId(autoIncrementId());
        File file = new File(PATH);
        Gson gson = new Gson();
                //.registerTypeAdapter(LocalDate.class, new SerializerDate())
               // .create();
        try (FileWriter pw = new FileWriter(file, true)) {
            pw.write(gson.toJson(document));
            pw.write("\n");
        } catch (IOException e) {
            System.out.println("Файла нет или не найден(документы)");
            log.error("Файла нет или не найден(документы)");
        }
    }

    private boolean isEmptyFile() {
        File file1 = new File(PATH);
        try (BufferedReader br = new BufferedReader(new FileReader(file1))) {
            return br.readLine() == null;
        } catch (IOException e) {
            System.out.println("Файла нет или не найден(документы)");
            log.error("Файла нет или не найден(документы)");
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
                Document document = gson.fromJson(scanner.nextLine(), Document.class);
                if (document.getId() >= x) {
                    x = document.getId();
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файла нет или не найден(документы)");
            log.error("Файла нет или не найден(документы)");
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
            System.out.println("Файла нет или не найден(документы)");
            log.error("Файла нет или не найден(документы)");
        }
    }
}
