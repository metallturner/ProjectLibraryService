package com.library.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.library.dao.interfaces.MagazineDaoInterface;
import com.library.domain.models.Magazine;
import com.library.domain.models.messages.Messages;
import com.library.ui.Gson.SerializerDate;

import java.io.*;
import java.time.LocalDate;
import java.util.Scanner;

public class MagazineDao implements MagazineDaoInterface {
    private final String PATH = "src/main/resources/Magazines";
    private final String PATH1 = "src/main/resources/Magazines1";

    Scanner sc1 = new Scanner(System.in);
    Scanner sc2 = new Scanner(System.in);


    @Override
    public void createMagazine(Magazine magazine) {
        writeToFile(magazine);
        optimizeFile();

    }

    @Override
    public void searchMagazineName(String name) {
        if (isEmptyFile()) {
            System.out.println("Журналов нет, файл пустой(поиск)");
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
            System.out.println("такого журнала нет(поиск)");
        } catch (FileNotFoundException e) {
            System.out.println("Файла нет или не найден(журналы)");
        }
    }

    @Override
    public void deleteMagazine(Magazine magazine) {
        if (isEmptyFile()) {
            System.out.println("Журналов нет, файл пустой(удаление)");
            return;
        }
        if (itemAvailability(magazine) == -1) {
            System.out.println("Журнала с таким ID нет");
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
        }

    }

    @Override
    public void showContent() {
        if (isEmptyFile()) {
            System.out.println("Журналов нет, файл пустой(показ всех журналов)");
            return;
        }
        File file = new File(PATH);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        } catch (FileNotFoundException s) {
            System.out.println("Файла нет или не найден(журналы)");
        }
    }

    @Override
    public void updateMagazine(Magazine magazine) {
        if (isEmptyFile()) {
            System.out.println("Журналов нет, файл пустой(редактирование)");
            return;
        }
        if (itemAvailability(magazine) == -1) {
            System.out.println("Журнала с таким ID нет");
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
                    System.out.println(Messages.LOCATION);
                    String location = sc2.nextLine();
                    magazine.setLocation(location);
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
        }
    }

    private boolean isEmptyFile() {
        File file1 = new File(PATH);
        try (BufferedReader br = new BufferedReader(new FileReader(file1))) {
            return br.readLine() == null;
        } catch (IOException e) {
            System.out.println("Файла нет или не найден(журналы)");
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
        }
    }
}
