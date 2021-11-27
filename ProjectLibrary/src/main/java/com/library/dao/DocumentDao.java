package com.library.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.library.dao.interfaces.DocumentsDaoInterface;
import com.library.domain.models.Document;
import com.library.domain.models.messages.Messages;
import com.library.ui.Gson.SerializerDate;
import org.apache.log4j.Logger;

import java.io.*;
import java.time.LocalDate;
import java.util.Scanner;

public class DocumentDao implements DocumentsDaoInterface {
    private static final Logger log = Logger.getLogger(DocumentDao.class);
    private final String PATH = "src/main/resources/Documents.txt";
    private final String PATH1 = "src/main/resources/Documents1.txt";

    Scanner sc1 = new Scanner(System.in);
    Scanner sc2 = new Scanner(System.in);


    @Override
    public void createDocument(Document document) {
        writeToFile(document);
        optimizeFile();

    }

    @Override
    public void searchDocumentName(String name) {
        if (isEmptyFile()) {
            System.out.println("Документов нет, файл пустой(поиск)");
            log.error("Документов нет, файл пустой(поиск)");
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
            System.out.println("такого документа нет(поиск)");
            log.error("такого документа нет(поиск)");
        } catch (FileNotFoundException e) {
            System.out.println("Файла нет или не найден(документы)");
            log.error("Файла нет или не найден(документы)");
        }
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
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(LocalDate.class, new SerializerDate())
                    .create();
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
    public void showContent() {
        if (isEmptyFile()) {
            System.out.println("Документов нет, файл пустой(показ всех документов)");
            log.error("Документов нет, файл пустой(показ всех документов)");
            return;
        }
        File file = new File(PATH);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        } catch (FileNotFoundException s) {
            System.out.println("Файла нет или не найден(документы)");
            log.error("Файла нет или не найден(документы)");
        }
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
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(LocalDate.class, new SerializerDate())
                    .create();
            Document updateDocument;
            while (file.hasNextLine()) {
                String line = file.nextLine();
                Document document1 = gson.fromJson(line, Document.class);
                if (document.getId() != document1.getId()) {
                    writer.write(line);
                    writer.write("\n");
                }
                if (document.getId() == document1.getId()) {
                    updateDocument = gson.fromJson(line, Document.class);
                    writer.write(gson.toJson(updateDocumentFromJason(updateDocument)));
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


    private int itemAvailability(Document document) {
        try (Scanner file = new Scanner(new File(PATH))) {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(LocalDate.class, new SerializerDate())
                    .create();
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

    private Document updateDocumentFromJason(Document document) {
        boolean b = true;
        while (b) {
            System.out.println(document);
            System.out.println(Messages.UPDATE_FOR_DOCS);
            String s = sc1.nextLine();
            switch (s) {
                case "1":
                    System.out.println(Messages.NAME);
                    String name = sc2.nextLine();
                    document.setName(name);
                    break;
                case "2":
                    System.out.println(Messages.NUMBER_DOC);
                    String number = sc2.nextLine();
                    document.setDocumentNumber(number);
                    break;
                case "3":
                    System.out.println(Messages.LOCATION);
                    String location = sc2.nextLine();
                    document.setLocation(location);
                    break;
                case "4":
                    System.out.println(Messages.YEAR_CREATE);
                    int yearCre = sc2.nextInt();
                    System.out.println(Messages.MONTH_CREATE);
                    int monthCre = sc2.nextInt();
                    System.out.println(Messages.DAY_CREATE);
                    int dayCre = sc2.nextInt();
                    document.setDateOfDocumentCreation(yearCre, monthCre, dayCre);
                    break;
                case "5":
                    System.out.println(Messages.YEAR_ADD);
                    int yearAdd = sc2.nextInt();
                    System.out.println(Messages.MONTH_ADD);
                    int monthAdd = sc2.nextInt();
                    System.out.println(Messages.DAY_ADD);
                    int dayAdd = sc2.nextInt();
                    document.setDateAddedToTheLibrary(yearAdd, monthAdd, dayAdd);
                    break;
                case "6":
                    System.out.println(Messages.YEAR_MOD);
                    int yearMod = sc2.nextInt();
                    System.out.println(Messages.MONTH_MOD);
                    int monthMod = sc2.nextInt();
                    System.out.println(Messages.DAY_MOD);
                    int dayMod = sc2.nextInt();
                    document.setDateAddedToTheLibrary(yearMod, monthMod, dayMod);
                    break;
                case "exit":
                    b = false;
                    break;
            }
        }
        return document;
    }

    private void writeToFile(Document document) {
        document.setId(autoIncrementId());
        File file = new File(PATH);
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new SerializerDate())
                .create();
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
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new SerializerDate())
                .create();
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
