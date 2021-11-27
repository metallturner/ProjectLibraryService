package com.library.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.library.dao.interfaces.PatentDocumentsDaoInterface;
import com.library.domain.models.PatentDocument;
import com.library.domain.models.messages.Messages;
import com.library.ui.Gson.SerializerDate;
import org.apache.log4j.Logger;

import java.io.*;
import java.time.LocalDate;
import java.util.Scanner;

public class PatentDocumentsDao implements PatentDocumentsDaoInterface {
    private static final Logger log = Logger.getLogger(PatentDocumentsDao.class);

    private final String PATH = "src/main/resources/PatentDocuments.txt";
    private final String PATH1 = "src/main/resources/PatentDocuments1.txt";

    Scanner sc1 = new Scanner(System.in);
    Scanner sc2 = new Scanner(System.in);


    @Override
    public void createPatentDocument(PatentDocument patentDocument) {
        writeToFile(patentDocument);
        optimizeFile();

    }

    @Override
    public void searchPatentDocumentName(String name) {
        if (isEmptyFile()) {
            System.out.println("Патентов нет, файл пустой(поиск)");
            log.error("Патентов нет, файл пустой(поиск)");
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
            System.out.println("такого патента нет(поиск)");
            log.error("такого патента нет(поиск)");
        } catch (FileNotFoundException e) {
            System.out.println("Файла нет или не найден(патенты)");
            log.error("Файла нет или не найден(патенты)");
        }
    }

    @Override
    public void deletePatentDocument(PatentDocument patentDocument) {
        if (isEmptyFile()) {
            System.out.println("Патентов нет, файл пустой(удаление)");
            log.error("Патентов нет, файл пустой(удаление)");
            return;
        }
        if (itemAvailability(patentDocument) == -1) {
            System.out.println("Патента с таким ID нет");
            log.error("Патента с таким ID нет");
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
                PatentDocument patentDocument1 = gson.fromJson(line, PatentDocument.class);
                if (patentDocument.getId() != patentDocument1.getId()) {
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
            System.out.println("Файла нет или не найден(патенты)");
            log.error("Файла нет или не найден(патенты)");
        }

    }

    @Override
    public void showContent() {
        if (isEmptyFile()) {
            System.out.println("Патентов нет, файл пустой(показ всех патентов)");
            log.error("Патентов нет, файл пустой(показ всех патентов)");
            return;
        }
        File file = new File(PATH);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        } catch (FileNotFoundException s) {
            System.out.println("Файла нет или не найден(патенты)");
            log.error("Файла нет или не найден(патенты)");
        }
    }

    @Override
    public void updatePatentDocument(PatentDocument patentDocument) {
        if (isEmptyFile()) {
            System.out.println("Патентов нет, файл пустой(редактирование)");
            log.error("Патентов нет, файл пустой(редактирование)");
            return;
        }
        if (itemAvailability(patentDocument) == -1) {
            System.out.println("Патента с таким ID нет");
            log.error("Патента с таким ID нет");
            return;
        }
        try {

            Scanner file = new Scanner(new File(PATH));
            PrintWriter writer = new PrintWriter(PATH1);
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(LocalDate.class, new SerializerDate())
                    .create();
            PatentDocument updatePatentDocument;
            while (file.hasNextLine()) {
                String line = file.nextLine();
                PatentDocument patentDocument1 = gson.fromJson(line, PatentDocument.class);
                if (patentDocument.getId() != patentDocument1.getId()) {
                    writer.write(line);
                    writer.write("\n");
                }
                if (patentDocument.getId() == patentDocument1.getId()) {
                    updatePatentDocument = gson.fromJson(line, PatentDocument.class);
                    writer.write(gson.toJson(updateDocumentFromJason(updatePatentDocument)));
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
            System.out.println("Файла нет или не найден(патенты)");
            log.error("Файла нет или не найден(патенты)");
        }
    }


    private int itemAvailability(PatentDocument patentDocument) {
        try (Scanner file = new Scanner(new File(PATH))) {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(LocalDate.class, new SerializerDate())
                    .create();
            while (file.hasNextLine()) {
                String line = file.nextLine();
                PatentDocument patentDocument1 = gson.fromJson(line, PatentDocument.class);
                if (patentDocument.getId() == patentDocument1.getId()) {
                    return 1;
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("Файла нет или не найден(патенты)");
            log.error("Файла нет или не найден(патенты)");
        }
        return -1;
    }

    private PatentDocument updateDocumentFromJason(PatentDocument patentDocument) {
        boolean b = true;
        while (b) {
            System.out.println(patentDocument);
            System.out.println(Messages.UPDATE_FOR_PATENT_DOCS);
            String s = sc1.nextLine();
            switch (s) {
                case "1":
                    System.out.println(Messages.NAME);
                    String name = sc2.nextLine();
                    patentDocument.setName(name);
                    break;
                case "2":
                    System.out.println(Messages.NUMBER_DOC);
                    String number = sc2.nextLine();
                    patentDocument.setPatentNumber(number);
                    break;
                case "3":
                    System.out.println(Messages.AUTHOR);
                    String author = sc2.nextLine();
                    patentDocument.setAuthor(author);
                    break;
                case "4":
                    System.out.println(Messages.LOCATION);
                    String location = sc2.nextLine();
                    patentDocument.setLocation(location);
                    break;
                case "5":
                    System.out.println(Messages.YEAR_ADD);
                    int yearAdd = sc2.nextInt();
                    System.out.println(Messages.MONTH_ADD);
                    int monthAdd = sc2.nextInt();
                    System.out.println(Messages.DAY_ADD);
                    int dayAdd = sc2.nextInt();
                    patentDocument.setDateAddedToTheLibrary(yearAdd, monthAdd, dayAdd);
                    break;
                case "6":
                    System.out.println(Messages.YEAR_MOD);
                    int yearMod = sc2.nextInt();
                    System.out.println(Messages.MONTH_MOD);
                    int monthMod = sc2.nextInt();
                    System.out.println(Messages.DAY_MOD);
                    int dayMod = sc2.nextInt();
                    patentDocument.setDateAddedToTheLibrary(yearMod, monthMod, dayMod);
                    break;
                case "exit":
                    b = false;
                    break;
            }
        }
        return patentDocument;
    }

    private void writeToFile(PatentDocument patentDocument) {
        patentDocument.setId(autoIncrementId());
        File file = new File(PATH);
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new SerializerDate())
                .create();
        try (FileWriter pw = new FileWriter(file, true)) {
            pw.write(gson.toJson(patentDocument));
            pw.write("\n");
        } catch (IOException e) {
            System.out.println("Файла нет или не найден(патенты)");
            log.error("Файла нет или не найден(патенты)");
        }
    }

    private boolean isEmptyFile() {
        File file1 = new File(PATH);
        try (BufferedReader br = new BufferedReader(new FileReader(file1))) {
            return br.readLine() == null;
        } catch (IOException e) {
            System.out.println("Файла нет или не найден(патенты)");
            log.error("Файла нет или не найден(патенты)");
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
                PatentDocument patentDocument = gson.fromJson(scanner.nextLine(), PatentDocument.class);
                if (patentDocument.getId() >= x) {
                    x = patentDocument.getId();
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файла нет или не найден(патенты)");
            log.error("Файла нет или не найден(патенты)");
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
            System.out.println("Файла нет или не найден(патенты)");
            log.error("Файла нет или не найден(патенты)");
        }
    }
}
