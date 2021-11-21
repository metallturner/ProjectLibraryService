package com.library.dao;

import com.library.domain.models.Person;
import com.library.dao.interfaces.PersonDaoInterface;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class PersonDao implements PersonDaoInterface {
    File file = new File("src/main/resources/Persons.txt");
    PrintWriter pw;

    {
        try {
            pw = new PrintWriter(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    List <Person> persons = new ArrayList();
    @Override
    public void toCreatePerson(String name){
       int id = persons.size();
        persons.add(new Person(id,name));


        for (Person p : persons) {
            System.out.println(p);

        }





    }

}
