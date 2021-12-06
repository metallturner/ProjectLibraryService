package com.library.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.library.dao.interfaces.ClassGetAuthorAndLocationInterface;
import com.library.domain.models.Author;
import com.library.domain.models.Location;
import com.library.ui.Gson.SerializerDate;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.*;
import java.time.LocalDate;
import java.util.Scanner;
@Component
public class ClassGetAuthorAndLocation implements ClassGetAuthorAndLocationInterface {
    private static final Logger log = Logger.getLogger(ClassGetAuthorAndLocation.class);
    final static String PATH_AUTHOR = "src/main/resources/Authors";
    final static String PATH_LOCATION = "src/main/resources/Locations";



@Override
   public Author getAuthor(int id){
       Gson gson = new GsonBuilder()
               .registerTypeAdapter(LocalDate.class, new SerializerDate())
               .create();
       if (isEmptyFile(PATH_AUTHOR)) {
           System.out.println("Авторов нет, файл пустой(получение по ID)");
           log.error("Авторов нет, файл пустой(получение по ID)");
           return new Author(0,"Авторов нет, файл пустой(получение по ID)");
       }
       File file = new File(PATH_AUTHOR);
       try (Scanner scanner = new Scanner(file)) {
           while (scanner.hasNextLine()) {
               String line = scanner.nextLine();
               Author author = gson.fromJson(line, Author.class);
               int id_author = author.getId();
               if(id_author==id){
                   return author;
               }
           }
           log.error("Такого автора нет(получение по ID)");
       } catch (FileNotFoundException e) {
           log.error("Файла нет или не найден(получение по ID)");
       }
       return new Author(0,"Автора с таким ID нет");
   }

    @Override
    public Location getLocation(int id){
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new SerializerDate())
                .create();
        if (isEmptyFile(PATH_LOCATION)) {
            System.out.println("Локаций нет, файл пустой(получение по ID)");
            log.error("Локаций нет, файл пустой(получение по ID)");
            return new Location(0,"Локаций нет, файл пустой(получение по ID)","","");
        }
        File file = new File(PATH_LOCATION);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Location location = gson.fromJson(line, Location.class);
                int id_author = location.getId();
                if(id_author==id){
                    return location;
                }
            }
            log.error("Такого автора нет(получение по ID)");
        } catch (FileNotFoundException e) {
            log.error("Файла нет или не найден(Локации)");
        }
        return new Location(0,"Локации с таким ID нет","","");
    }





    private boolean isEmptyFile(String PATH) {
        File file1 = new File(PATH);
        try (BufferedReader br = new BufferedReader(new FileReader(file1))) {
            return br.readLine() == null;
        } catch (IOException e) {
            System.out.println("Файла нет или не найден(авторы или локации)");
        }
        return true;
    }
}
