package com.library.ui;

import com.library.domain.models.messages.Messages;

import java.util.Scanner;

public class Run {
    RunInterface runInterfaceBook = new RunBook();
    RunInterface runInterfaceDoc = new RunDocument();
    RunInterface runInterfaceMag = new RunMagazine();
    RunInterface runInterfacePatDocs = new RunPatentDocuments();
    Scanner sc = new Scanner(System.in);

    void run() {
        boolean b = true;
        while (b) {
            System.out.println(Messages.BOOKS);
            System.out.println(Messages.DOCUMENTS);
            System.out.println(Messages.PATENT_DOCUMENTS);
            System.out.println(Messages.MAGAZINES);
            System.out.println(Messages.USERS);
            System.out.println(Messages.FINISH_PROGRAM);
            String s = sc.nextLine();
            switch (s) {
                case "1":
                    runInterfaceBook.show();
                    break;
                case "2":
                    runInterfaceDoc.show();
                    break;
                case "3":
                    runInterfacePatDocs.show();
                    break;
                case "4":
                    runInterfaceMag.show();
                    break;
            }
            if (s.equalsIgnoreCase("exit")){
                return;
            }
        }


    }
}



