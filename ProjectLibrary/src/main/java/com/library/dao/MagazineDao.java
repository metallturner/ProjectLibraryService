package com.library.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.library.dao.interfaces.ClassGetAuthorAndLocationInterface;
import com.library.dao.interfaces.MagazineDaoInterface;
import com.library.domain.models.Document;
import com.library.domain.models.Magazine;
import com.library.domain.models.messages.Messages;
import com.library.ui.Gson.SerializerDate;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.*;
import java.time.LocalDate;
import java.util.Scanner;
@Component
public class MagazineDao implements MagazineDaoInterface {
    private static final Logger log = Logger.getLogger(Magazine.class);
    private final String PATH = "src/main/resources/Magazines";
    private final String PATH1 = "src/main/resources/Magazines1";
    ClassGetAuthorAndLocationInterface classGetAuthorAndLocationInterface;
    @Autowired
    public MagazineDao(@Qualifier("classGetAuthorAndLocation") ClassGetAuthorAndLocationInterface classGetAuthorAndLocationInterface) {
        this.classGetAuthorAndLocationInterface = classGetAuthorAndLocationInterface;
    }

    Scanner sc1 = new Scanner(System.in);
    Scanner sc2 = new Scanner(System.in);


    @Override
    public void createMagazine(Magazine magazine) {
        writeToFile(magazine);
        optimizeFile();

    }

    @Override
    public void searchMagazineName(String name) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new SerializerDate())
                .create();
        if (isEmptyFile()) {
            System.out.println("Журналов нет, файл пустой(поиск)");
            log.error("Журналов нет, файл пустой(поиск)");
            return;
        }
        File file = new File(PATH);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Magazine magazine = gson.fromJson(line, Magazine.class);
                if (line.contains(name)) {
                    magazine.setLocation(classGetAuthorAndLocationInterface.getLocation(magazine.getLocationId()));
                    System.out.println(magazine);
                    return;
                }
            }
            System.out.println("такого журнала нет(поиск)");
            log.error("такого журнала нет(поиск)");
        } catch (FileNotFoundException e) {
            System.out.println("Файла нет или не найден(журналы)");
            log.error("Файла нет или не найден(журналы)");
        }
    }

    @Override
    public void deleteMagazine(Magazine magazine) {
        if (isEmptyFile()) {
            System.out.println("Журналов нет, файл пустой(удаление)");
            log.error("Журналов нет, файл пустой(удаление)");
            return;
        }
        if (itemAvailability(magazine) == -1) {
            System.out.println("Журнала с таким ID нет");
            log.error("Журнала с таким ID нет");
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
                Magazine magazine1 = gson.fromJson(line, Magazine.class);
                if (magazine.getId() != magazine1.getId()) {
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
            System.out.println("Файла нет или не найден(журналы)");
            log.error("Файла нет или не найден(журналы)");
        }

    }

    @Override
    public void showContent() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new SerializerDate())
                .create();
        if (isEmptyFile()) {
            System.out.println("Журналов нет, файл пустой(показ всех журналов)");
            log.error("Журналов нет, файл пустой(показ всех журналов)");
            return;
        }
        File file = new File(PATH);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                Magazine magazine = gson.fromJson(scanner.nextLine(), Magazine.class);
                magazine.setLocation(classGetAuthorAndLocationInterface.getLocation(magazine.getLocationId()));
                System.out.println(magazine);
            }
        } catch (FileNotFoundException s) {
            System.out.println("Файла нет или не найден(журналы)");
            log.error("Файла нет или не найден(журналы)");
        }
    }

    @Override
    public void updateMagazine(Magazine magazine) {
        if (isEmptyFile()) {
            System.out.println("Журналов нет, файл пустой(редактирование)");
            log.error("Журналов нет, файл пустой(редактирование)");
            return;
        }
        if (itemAvailability(magazine) == -1) {
            System.out.println("Журнала с таким ID нет");
            log.error("Журнала с таким ID нет");
            return;
        }
        try {

            Scanner file = new Scanner(new File(PATH));
            PrintWriter writer = new PrintWriter(PATH1);
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(LocalDate.class, new SerializerDate())
                    .create();
            Magazine updateMagazine;
            while (file.hasNextLine()) {
                String line = file.nextLine();
                Magazine magazine1 = gson.fromJson(line, Magazine.class);
                if (magazine.getId() != magazine1.getId()) {
                    writer.write(line);
                    writer.write("\n");
                }
                if (magazine.getId() == magazine1.getId()) {
                    updateMagazine = gson.fromJson(line, Magazine.class);
                    writer.write(gson.toJson(updateDocumentFromJason(updateMagazine)));
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
            System.out.println("Файла нет или не найден(журналы)");
            log.error("Файла нет или не найден(журналы)");
        }
    }


    private int itemAvailability(Magazine magazine) {
        try (Scanner file = new Scanner(new File(PATH))) {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(LocalDate.class, new SerializerDate())
                    .create();
            while (file.hasNextLine()) {
                String line = file.nextLine();
                Magazine magazine1 = gson.fromJson(line, Magazine.class);
                if (magazine.getId() == magazine1.getId()) {
                    return 1;
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("Файла нет или не найден(журналы)");
            log.error("Файла нет или не найден(журналы)");
        }
        return -1;
    }

    private Magazine updateDocumentFromJason(Magazine magazine) {
        boolean b = true;
        while (b) {
            System.out.println(magazine);
            System.out.println(Messages.UPDATE_FOR_MAGAZINES);
            String s = sc1.nextLine();
            switch (s) {
                case "1":
                    System.out.println(Messages.NAME);
                    String name = sc2.nextLine();
                    magazine.setName(name);
                    break;
                case "2":
                    System.out.println(Messages.ID_LOCATION);
                    int locationId = sc2.nextInt();
                    magazine.setLocationId(locationId);
                    break;
                case "3":
                    System.out.println(Messages.MONTH_PUB);
                    int yearPub = sc2.nextInt();
                    System.out.println(Messages.MONTH_PUB);
                    int monthPub = sc2.nextInt();
                    System.out.println(Messages.DAY_PUB);
                    int dayPub = sc2.nextInt();
                    magazine.setDateOfPublication(yearPub, monthPub, dayPub);
                    break;
                case "4":
                    System.out.println(Messages.YEAR_ADD);
                    int yearAdd = sc2.nextInt();
                    System.out.println(Messages.MONTH_ADD);
                    int monthAdd = sc2.nextInt();
                    System.out.println(Messages.DAY_ADD);
                    int dayAdd = sc2.nextInt();
                    magazine.setDateAddedToTheLibrary(yearAdd, monthAdd, dayAdd);
                    break;
                case "5":
                    System.out.println(Messages.YEAR_MOD);
                    int yearMod = sc2.nextInt();
                    System.out.println(Messages.MONTH_MOD);
                    int monthMod = sc2.nextInt();
                    System.out.println(Messages.DAY_MOD);
                    int dayMod = sc2.nextInt();
                    magazine.setDateAddedToTheLibrary(yearMod, monthMod, dayMod);
                    break;
                case "exit":
                    b = false;
                    break;
            }
        }
        return magazine;
    }

    private void writeToFile(Magazine magazine) {
        magazine.setId(autoIncrementId());
        File file = new File(PATH);
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new SerializerDate())
                .create();
        try (FileWriter pw = new FileWriter(file, true)) {
            pw.write(gson.toJson(magazine));
            pw.write("\n");
        } catch (IOException e) {
            System.out.println("Файла нет или не найден(журналы)");
            log.error("Файла нет или не найден(журналы)");
        }
    }

    private boolean isEmptyFile() {
        File file1 = new File(PATH);
        try (BufferedReader br = new BufferedReader(new FileReader(file1))) {
            return br.readLine() == null;
        } catch (IOException e) {
            System.out.println("Файла нет или не найден(журналы)");
            log.error("Файла нет или не найден(журналы)");
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
                Magazine magazine = gson.fromJson(scanner.nextLine(), Magazine.class);
                if (magazine.getId() >= x) {
                    x = magazine.getId();
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файла нет или не найден(журналы)");
            log.error("Файла нет или не найден(журналы)");
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
            System.out.println("Файла нет или не найден(журналы)");
            log.error("Файла нет или не найден(журналы)");
        }
    }
}
