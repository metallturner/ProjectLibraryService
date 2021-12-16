package com.library.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.library.dao.interfaces.ClassGetAuthorAndLocationInterface;
import com.library.dao.interfaces.MagazineDaoInterface;
import com.library.domain.models.Magazine;
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
public class MagazineDao implements MagazineDaoInterface {
    private static final Logger log = Logger.getLogger(Magazine.class);
    private final String PATH = "C:\\Users\\paul\\Desktop\\NetCracker lessons\\MVCProjectLibraryNetcracker\\src\\main\\resources\\Magazines";
    private final String PATH1 = "C:\\Users\\paul\\Desktop\\NetCracker lessons\\MVCProjectLibraryNetcracker\\src\\main\\resources\\Magazines1";
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
    public Magazine getById(int id) {
        Gson gson = new Gson();
               // .registerTypeAdapter(LocalDate.class, new SerializerDate())
               // .create();
        if (isEmptyFile()) {
            System.out.println("Журналов нет, файл пустой(поиск)");
            log.error("Журналов нет, файл пустой(поиск)");
            return null;
        }
        File file = new File(PATH);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Magazine magazine = gson.fromJson(line, Magazine.class);
                if (magazine.getId()==id) {
                    return magazine;
                }
            }
            System.out.println("такого журнала нет(поиск)");
            log.error("такого журнала нет(поиск)");
        } catch (FileNotFoundException e) {
            System.out.println("Файла нет или не найден(журналы)");
            log.error("Файла нет или не найден(журналы)");
        }
        return null;
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
            Gson gson = new Gson();
                  //  .registerTypeAdapter(LocalDate.class, new SerializerDate())
                  //  .create();
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
    public List<Magazine> showContent() {
        Gson gson = new Gson();
//                .registerTypeAdapter(LocalDate.class, new SerializerDate())
//                .create();
        if (isEmptyFile()) {
            System.out.println("Журналов нет, файл пустой(показ всех журналов)");
            log.error("Журналов нет, файл пустой(показ всех журналов)");
            return null;
        }
        File file = new File(PATH);
        List<Magazine> mags = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                Magazine magazine = gson.fromJson(scanner.nextLine(), Magazine.class);
                mags.add(magazine);
            }
            return mags;
        } catch (FileNotFoundException s) {
            System.out.println("Файла нет или не найден(журналы)");
            log.error("Файла нет или не найден(журналы)");
        }
        return null;
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
            Gson gson = new Gson();
//                    .registerTypeAdapter(LocalDate.class, new SerializerDate())
//                    .create();
            while (file.hasNextLine()) {
                String line = file.nextLine();
                Magazine magazine1 = gson.fromJson(line, Magazine.class);
                if (magazine.getId() != magazine1.getId()) {
                    writer.write(line);
                    writer.write("\n");
                }
                if (magazine.getId() == magazine1.getId()) {
                    writer.write(gson.toJson(magazine));
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


    public int itemAvailability(Magazine magazine) {
        try (Scanner file = new Scanner(new File(PATH))) {
            Gson gson = new Gson();
//                    .registerTypeAdapter(LocalDate.class, new SerializerDate())
//                    .create();
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


    private void writeToFile(Magazine magazine) {
        magazine.setId(autoIncrementId());
        File file = new File(PATH);
        Gson gson = new Gson();
//                .registerTypeAdapter(LocalDate.class, new SerializerDate())
//                .create();
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
        Gson gson = new Gson();
//                .registerTypeAdapter(LocalDate.class, new SerializerDate())
//                .create();
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
