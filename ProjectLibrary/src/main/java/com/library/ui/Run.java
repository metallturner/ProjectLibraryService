package com.library.ui;

import com.library.domain.ControllerInterfaces.PersonControllerInterface;
import com.library.domain.Controllers.PersonController;
import com.library.domain.models.Messages;

import java.util.Scanner;

public class Run {
    RunInterface runInterface = new RunBook();
    PersonControllerInterface personControllerInterface = new PersonController();
    Scanner sc = new Scanner(System.in);

    void run(){

        while (true) {
            System.out.println(Messages.BOOKS);
            System.out.println(Messages.DOCUMENTS);
            System.out.println(Messages.PATENT_DOCUMENTS);
            System.out.println(Messages.MAGAZINES);
            System.out.println(Messages.USERS);
            String s = sc.nextLine();
            switch (s) {
                case "1":
                    runInterface.show();
                    break;
            }
        }


}
    }



