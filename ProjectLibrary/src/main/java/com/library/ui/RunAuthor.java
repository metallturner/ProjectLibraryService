package com.library.ui;

import com.library.domain.ControllerInterfaces.AuthorControllerInterface;
import com.library.domain.ControllerInterfaces.BookControllerInterface;
import com.library.domain.models.Author;
import com.library.domain.models.Book;
import com.library.domain.models.messages.Messages;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class RunAuthor implements RunInterface {

    private static final Logger log = Logger.getLogger(RunBook.class);
    AuthorControllerInterface authorControllerInterface;

    @Autowired
    public RunAuthor(@Qualifier("authorController") AuthorControllerInterface authorControllerInterface) {
        this.authorControllerInterface = authorControllerInterface;
    }

    Scanner scanner = new Scanner(System.in);
    Scanner scanner1 = new Scanner(System.in);

    @Override
    public void show() {


        boolean b = true;
        while (b) {
            System.out.println(Messages.VARIANTS);

            String command = scanner1.nextLine();
            switch (command) {
                case "1":
                    System.out.println(Messages.AUTHOR);
                    Author authorCreate = new Author();
                    String name = scanner.nextLine();
                    authorCreate.setName(name);
                    authorControllerInterface.createAuthor(authorCreate);
                    log.info("Был добавлен автор " + authorCreate.getName());
                    break;
                case "2":
                    Author authorDelete = new Author();
                    System.out.println(Messages.ID);
                    int id = scanner.nextInt();
                    authorDelete.setId(id);
                    authorControllerInterface.deleteAuthor(authorDelete);
                    log.info("Был удален автор c ID " + authorDelete.getId());
                    break;
                case "3":
                    System.out.println(Messages.NAME);
                    String nameAuthor = scanner.nextLine();
                    authorControllerInterface.searchAuthorName(nameAuthor);

                    break;
                case "4":
                    Author authorUpdate = new Author();
                    System.out.println(Messages.ID);
                    int idUpdateAuthor = scanner.nextInt();
                    authorUpdate.setId(idUpdateAuthor);
                    authorControllerInterface.updateAuthor(authorUpdate);
                    log.info("Были произведены изменения в авторе c ID " + authorUpdate.getId());
                    break;
                case "5":
                    authorControllerInterface.showContent();
                    break;
                case "exit":
                    b = false;
                    break;


            }
        }
    }
}
