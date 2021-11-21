package com.library.ui;

import com.library.domain.ControllerInterfaces.BookControllerInterface;
import com.library.domain.Controllers.BookController;
import com.library.domain.models.Messages;

import java.util.Scanner;

public class RunBook implements RunInterface {
    BookControllerInterface bookControllerInterface = new BookController();
    Scanner scanner = new Scanner(System.in);

    @Override
    public void show() {
        bookControllerInterface.deserializationOnCollection();//сразу перекидываем все книги с файла в коллекцию
        System.out.println(Messages.VARIANTS);

        boolean b = true;
        while (b) {

            String command = scanner.nextLine();
            switch (command) {
                case "1":
                    System.out.println(Messages.NAME);
                    String name = scanner.nextLine();
                    System.out.println(Messages.ISBN);
                    String isbn = scanner.nextLine();
                    System.out.println(Messages.AUTHOR);
                    String author = scanner.nextLine();
                    System.out.println(Messages.LOCATION);
                    String location = scanner.nextLine();
                    System.out.println(Messages.YEAR_PUB);
                    int yearPub = scanner.nextInt();
                    System.out.println(Messages.MONTH_PUB);
                    int monthPub = scanner.nextInt();
                    System.out.println(Messages.DAY_PUB);
                    int dayPub = scanner.nextInt();
                    System.out.println(Messages.YEAR_ADD);
                    int yearAdd = scanner.nextInt();
                    System.out.println(Messages.MONTH_ADD);
                    int monthAdd = scanner.nextInt();
                    System.out.println(Messages.DAY_ADD);
                    int dayAdd = scanner.nextInt();
                    System.out.println(Messages.YEAR_MOD);
                    int yearMod = scanner.nextInt();
                    System.out.println(Messages.MONTH_MOD);
                    int monthMod = scanner.nextInt();
                    System.out.println(Messages.DAY_MOD);
                    int dayMod = scanner.nextInt();
                    bookControllerInterface.createBook(name, isbn, author, location, yearPub, monthPub, dayPub,
                            yearAdd, monthAdd, dayAdd, yearMod, monthMod, dayMod);

                    break;
                case "2":
                    bookControllerInterface.deleteBook();
                    System.out.println(Messages.VARIANTS);

                    break;
                case "3":
                    bookControllerInterface.searchBookName();
                    System.out.println(Messages.CREATE);
                    System.out.println(Messages.DELETE);
                    System.out.println(Messages.SEARCH);
                    System.out.println(Messages.UPDATE);
                    System.out.println(Messages.CONTENT);
                    System.out.println(Messages.EXIT);

                    break;
                case "4":
                    bookControllerInterface.updateBook();
                    System.out.println(Messages.VARIANTS);


                    break;
                case "5":
                    bookControllerInterface.showContent();
                    System.out.println(Messages.VARIANTS);
                    break;
                case "exit":
                    b = false;
                    break;
                    default:
                    System.out.println(Messages.VARIANTS);


            }
        }
    }

}


