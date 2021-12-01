package com.library.ui;

import com.library.domain.ControllerInterfaces.BookControllerInterface;
import com.library.domain.Controllers.BookController;
import com.library.domain.models.Book;
import com.library.domain.models.messages.Messages;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class RunBook implements RunInterface {

    private static final Logger log = Logger.getLogger(RunBook.class);
    BookControllerInterface bookControllerInterface;

    @Autowired
    public RunBook(@Qualifier("bookController") BookControllerInterface bookControllerInterface) {
        this.bookControllerInterface = bookControllerInterface;
    }

    Scanner scanner = new Scanner(System.in);

    @Override
    public void show() {
        System.out.println(Messages.VARIANTS);

        boolean b = true;
        while (b) {

            String command = scanner.nextLine();
            switch (command) {
                case "1":
                    Book bookCreate = new Book();
                    System.out.println(Messages.NAME);
                    String name = scanner.nextLine();
                    bookCreate.setName(name);
                    System.out.println(Messages.ISBN);
                    String isbn = scanner.nextLine();
                    bookCreate.setIsbn(isbn);
                    System.out.println(Messages.AUTHOR);
                    String author = scanner.nextLine();
                    bookCreate.setAuthor(author);
                    System.out.println(Messages.LOCATION);
                    String location = scanner.nextLine();
                    bookCreate.setLocation(location);
                    System.out.println(Messages.YEAR_PUB);
                    int yearPub = scanner.nextInt();
                    System.out.println(Messages.MONTH_PUB);
                    int monthPub = scanner.nextInt();
                    System.out.println(Messages.DAY_PUB);
                    int dayPub = scanner.nextInt();
                    bookCreate.setDateOfPublication(yearPub, monthPub, dayPub);
                    System.out.println(Messages.YEAR_ADD);
                    int yearAdd = scanner.nextInt();
                    System.out.println(Messages.MONTH_ADD);
                    int monthAdd = scanner.nextInt();
                    System.out.println(Messages.DAY_ADD);
                    int dayAdd = scanner.nextInt();
                    bookCreate.setDateAddedToTheLibrary(yearAdd, monthAdd, dayAdd);
                    System.out.println(Messages.YEAR_MOD);
                    int yearMod = scanner.nextInt();
                    System.out.println(Messages.MONTH_MOD);
                    int monthMod = scanner.nextInt();
                    System.out.println(Messages.DAY_MOD);
                    int dayMod = scanner.nextInt();
                    bookCreate.setDateOfModification(yearMod, monthMod, dayMod);
                    bookControllerInterface.createBook(bookCreate);
                    log.info("Была добавлена книга " + bookCreate.getName());
                    break;
                case "2":
                    Book bookDelete = new Book();
                    System.out.println(Messages.ID);
                    int id = scanner.nextInt();
                    bookDelete.setId(id);
                    bookControllerInterface.deleteBook(bookDelete);
                    log.info("Была удалена книга c ID " + bookDelete.getId());
                    break;
                case "3":
                    System.out.println(Messages.NAME);
                    String nameBook = scanner.nextLine();
                    bookControllerInterface.searchBookName(nameBook);
                    System.out.println(Messages.CREATE);
                    System.out.println(Messages.DELETE);
                    System.out.println(Messages.SEARCH);
                    System.out.println(Messages.UPDATE);
                    System.out.println(Messages.CONTENT);
                    System.out.println(Messages.EXIT);
                    break;
                case "4":
                    Book bookUpdate = new Book();
                    System.out.println(Messages.ID);
                    int idUpdateBook = scanner.nextInt();
                    bookUpdate.setId(idUpdateBook);
                    bookControllerInterface.updateBook(bookUpdate);
                    log.info("Были произведены изменения в книге c ID " + bookUpdate.getId());
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


