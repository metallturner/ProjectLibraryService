package com.library.ui;

import com.library.domain.models.messages.Messages;

import java.util.Scanner;

public class Run {
    RunInterface runInterfaceBook = new RunBook();
    RunInterface runInterfaceDoc = new RunDocument();
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
                    runInterfaceBook.show();
                    break;
                case "2":
                    runInterfaceDoc.show();
            }
        }


}
    }



