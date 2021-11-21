package com.library.domain.Controllers;

import com.library.dao.PersonDao;
import com.library.dao.interfaces.PersonDaoInterface;
import com.library.domain.ControllerInterfaces.PersonControllerInterface;

public class PersonController implements PersonControllerInterface {
    PersonDaoInterface personDaoInterface = new PersonDao();

   // public PersonController(PersonDaoInterface personDaoInterface) {
       // this.personDaoInterface = personDaoInterface;
   // }

    @Override
    public void toCreatePerson(String name) {
        personDaoInterface.toCreatePerson(name);
    }
}
