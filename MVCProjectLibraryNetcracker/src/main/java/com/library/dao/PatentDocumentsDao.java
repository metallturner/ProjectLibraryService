package com.library.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.library.dao.interfaces.ClassGetAuthorAndLocationInterface;
import com.library.dao.interfaces.PatentDocumentsDaoInterface;
import com.library.domain.models.PatentDocument;
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
public class PatentDocumentsDao implements PatentDocumentsDaoInterface {
    private static final Logger log = Logger.getLogger(PatentDocumentsDao.class);

    private final String PATH = "C:\\Users\\paul\\Desktop\\NetCracker lessons\\MVCProjectLibraryNetcracker\\src\\main\\resources\\PatentDocuments.txt";
    private final String PATH1 = "C:\\Users\\paul\\Desktop\\NetCracker lessons\\MVCProjectLibraryNetcracker\\src\\main\\resources\\PatentDocuments1.txt.txt";

    Scanner sc1 = new Scanner(System.in);
    Scanner sc2 = new Scanner(System.in);
    ClassGetAuthorAndLocationInterface classGetAuthorAndLocationInterface;
    @Autowired
    public PatentDocumentsDao(@Qualifier("classGetAuthorAndLocation") ClassGetAuthorAndLocationInterface classGetAuthorAndLocationInterface) {
        this.classGetAuthorAndLocationInterface = classGetAuthorAndLocationInterface;
    }



    @Override
    public void createPatentDocument(PatentDocument patentDocument) {
        writeToFile(patentDocument);
        optimizeFile();

    }

    @Override
    public PatentDocument getById(int id) {
        Gson gson = new Gson();
//                .registerTypeAdapter(LocalDate.class, new SerializerDate())
//                .create();
        if (isEmptyFile()) {
            System.out.println("Патентов нет, файл пустой(поиск)");
            log.error("Патентов нет, файл пустой(поиск)");
            return null;
        }
        File file = new File(PATH);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                PatentDocument patentDocument = gson.fromJson(line, PatentDocument.class);

                if (patentDocument.getId()==id) {
                    return patentDocument;
                }
            }
            System.out.println("такого патента нет(поиск)");
            log.error("такого патента нет(поиск)");
        } catch (FileNotFoundException e) {
            System.out.println("Файла нет или не найден(патенты)");
            log.error("Файла нет или не найден(патенты)");
        }
        return null;
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
            Gson gson = new Gson();
//                    .registerTypeAdapter(LocalDate.class, new SerializerDate())
//                    .create();
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
    public List<PatentDocument> showContent() {
        Gson gson = new Gson();
//                .registerTypeAdapter(LocalDate.class, new SerializerDate())
//                .create();
        if (isEmptyFile()) {
            System.out.println("Патентов нет, файл пустой(показ всех патентов)");
            log.error("Патентов нет, файл пустой(показ всех патентов)");
            return null;
        }
        File file = new File(PATH);
        List<PatentDocument> patents = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                PatentDocument patentDocument = gson.fromJson(scanner.nextLine(), PatentDocument.class);
                patents.add(patentDocument);
            }
            return patents;
        } catch (FileNotFoundException s) {
            System.out.println("Файла нет или не найден(патенты)");
            log.error("Файла нет или не найден(патенты)");
        }
        return null;
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
            Gson gson = new Gson();
//                    .registerTypeAdapter(LocalDate.class, new SerializerDate())
//                    .create();
            while (file.hasNextLine()) {
                String line = file.nextLine();
                PatentDocument patentDocument1 = gson.fromJson(line, PatentDocument.class);
                if (patentDocument.getId() != patentDocument1.getId()) {
                    writer.write(line);
                    writer.write("\n");
                }
                if (patentDocument.getId() == patentDocument1.getId()) {
                    writer.write(gson.toJson(patentDocument));
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


    public int itemAvailability(PatentDocument patentDocument) {
        try (Scanner file = new Scanner(new File(PATH))) {
            Gson gson = new Gson();
//                    .registerTypeAdapter(LocalDate.class, new SerializerDate())
//                    .create();
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



    private void writeToFile(PatentDocument patentDocument) {
        patentDocument.setId(autoIncrementId());
        File file = new File(PATH);
        Gson gson = new Gson();
//                .registerTypeAdapter(LocalDate.class, new SerializerDate())
//                .create();
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
        Gson gson = new Gson();
//                .registerTypeAdapter(LocalDate.class, new SerializerDate())
//                .create();
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
