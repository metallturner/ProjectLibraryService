package com.library.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.library.dao.interfaces.LocationDaoInterface;
import com.library.domain.models.Location;
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
public class LocationDao implements LocationDaoInterface {
    private static final Logger log = Logger.getLogger(AuthorDao.class);
    private final String PATH = "C:\\Users\\paul\\Desktop\\NetCracker lessons\\MVCProjectLibraryNetcracker\\src\\main\\resources\\Locations";
    private final String PATH1 = "C:\\Users\\paul\\Desktop\\NetCracker lessons\\MVCProjectLibraryNetcracker\\src\\main\\resources\\Locations1";

    Scanner sc1 = new Scanner(System.in);
    Scanner sc2 = new Scanner(System.in);


    @Override
    public void createLocation(Location location) {
        writeToFile(location);
        optimizeFile();

    }

    @Override
    public Location getById(int id) {
        Gson gson = new Gson();
               // .registerTypeAdapter(LocalDate.class, new SerializerDate())
               // .create();
        if (isEmptyFile()) {
            System.out.println("Локации нет, файл пустой(поиск)");
            log.error("Локации нет, файл пустой(поиск)");
            return null;
        }
        File file = new File(PATH);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Location location = gson.fromJson(line, Location.class);
                if (location.getId()==id) {
                    System.out.println(location);
                    return location;
                }
            }
            System.out.println("Такой Локации нет(Локации)");
            log.error("Такой Локации нет(Локации)");
        } catch (FileNotFoundException e) {
            log.error("Файла нет или не найден(Локации)");
        }
        return null;
    }

    @Override
    public void deleteLocation(Location location) {
        if (isEmptyFile()) {
            System.out.println("Локации нет, файл пустой(удаление)");
            log.error("Локации нет, файл пустой(удаление)");
            return;
        }
        if (itemAvailability(location) == -1) {
            System.out.println("Локации с таким ID нет");
            log.error("Локации с таким ID нет");
            return;
        }
        try {
            Scanner file = new Scanner(new File(PATH));
            PrintWriter writer = new PrintWriter(PATH1);
            Gson gson = new Gson();
                    //.registerTypeAdapter(LocalDate.class, new SerializerDate())
                    //.create();
            while (file.hasNextLine()) {
                String line = file.nextLine();
                Location location1 = gson.fromJson(line, Location.class);
                if (location.getId() != location1.getId()) {
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
            log.error("Файла нет или не найден(Локации)");
        }

    }

    @Override
    public List<Location> showContent() {
        Gson gson = new Gson();
               // .registerTypeAdapter(LocalDate.class, new SerializerDate())
              //  .create();
        if (isEmptyFile()) {
            System.out.println("Локации нет, файл пустой(показ всех Локации)");
            log.error("Локации нет, файл пустой(показ всех Локации)");
            return null;
        }
        File file = new File(PATH);
        List<Location> locations = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                Location location = gson.fromJson(scanner.nextLine(), Location.class);
                locations.add(location);
            }
            return locations;
        } catch (FileNotFoundException s) {
            System.out.println("Файла нет или не найден(Локации)");
            log.error("Файла нет или не найден(Локации)");

        }
        return null;
    }

    @Override
    public void updateLocation(Location location) {
        if (isEmptyFile()) {
            System.out.println("Локации нет, файл пустой(редактирование)");
            log.error("Локации нет, файл пустой(редактирование)");
            return;
        }
        if (itemAvailability(location) == -1) {
            System.out.println("Локации с таким ID нет");
            log.error("Локации с таким ID нет");
            return;
        }
        try {

            Scanner file = new Scanner(new File(PATH));
            PrintWriter writer = new PrintWriter(PATH1);
            Gson gson = new Gson();
                    //.registerTypeAdapter(LocalDate.class, new SerializerDate())
                   // .create();
            while (file.hasNextLine()) {
                String line = file.nextLine();
                Location location1 = gson.fromJson(line, Location.class);
                if (location.getId() != location1.getId()) {
                    writer.write(line);
                    writer.write("\n");
                }
                if (location.getId() == location1.getId()) {
                    writer.write(gson.toJson(location));
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
            System.out.println("Файла нет или не найден(Локации)");
            log.error("Файла нет или не найден(Локации)");
        }
    }


    public int itemAvailability(Location location) {
        try (Scanner file = new Scanner(new File(PATH))) {
            Gson gson = new Gson();
                   // .registerTypeAdapter(LocalDate.class, new SerializerDate())
                    //.create();
            while (file.hasNextLine()) {
                String line = file.nextLine();
                Location location1 = gson.fromJson(line, Location.class);
                if (location.getId() == location1.getId()) {
                    return 1;
                }
            }

        } catch (FileNotFoundException e) {
            log.error("Файла нет или не найден(Локации)");
        }
        return -1;
    }



    private void writeToFile(Location location) {
        location.setId(autoIncrementId());
        File file = new File(PATH);
        Gson gson = new Gson();
               // .registerTypeAdapter(LocalDate.class, new SerializerDate())
               // .create();
        try (FileWriter pw = new FileWriter(file, true)) {
            pw.write(gson.toJson(location));
            pw.write("\n");
        } catch (IOException e) {
            log.error("Файла нет или не найден(Локации)");
        }
    }

    private boolean isEmptyFile() {
        File file1 = new File(PATH);
        try (BufferedReader br = new BufferedReader(new FileReader(file1))) {
            return br.readLine() == null;
        } catch (IOException e) {
            System.out.println("Файла нет или не найден(Локации)");
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
              //  .registerTypeAdapter(LocalDate.class, new SerializerDate())
              //  .create();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                Location location = gson.fromJson(scanner.nextLine(), Location.class);
                if (location.getId() >= x) {
                    x = location.getId();
                }
            }
        } catch (FileNotFoundException e) {
            log.error("Файла нет или не найден(Локации)");
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
            log.error("Файла нет или не найден(Локации)");
        }
    }
}
