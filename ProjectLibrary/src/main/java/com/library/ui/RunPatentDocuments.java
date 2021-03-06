package com.library.ui;

import com.library.domain.ControllerInterfaces.PatentDocumentsControllerInterface;
import com.library.domain.Controllers.PatentDocumentsController;
import com.library.domain.models.PatentDocument;
import com.library.domain.models.messages.Messages;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class RunPatentDocuments implements RunInterface {
    private static final Logger log = Logger.getLogger(RunPatentDocuments.class);
    PatentDocumentsControllerInterface PatentDocumentsControllerInterface;

    @Autowired
    public RunPatentDocuments(@Qualifier("patentDocumentsController") PatentDocumentsControllerInterface patentDocumentsControllerInterface) {
        PatentDocumentsControllerInterface = patentDocumentsControllerInterface;
    }

    @Override
    public void show() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(Messages.VARIANTS);

        boolean b = true;
        while (b) {

            String command = scanner.nextLine();
            switch (command) {
                case "1":
                    PatentDocument patentDocumentCreate = new PatentDocument();
                    System.out.println(Messages.NAME);
                    String name = scanner.nextLine();
                    patentDocumentCreate.setName(name);
                    System.out.println(Messages.NUMBER_DOC);
                    String docNumber = scanner.nextLine();
                    patentDocumentCreate.setPatentNumber(docNumber);
                    System.out.println(Messages.ID_LOCATION);
                    int locationID = scanner.nextInt();
                    patentDocumentCreate.setLocationId(locationID);
                    System.out.println(Messages.ID_AUTHOR);
                    int authorID = scanner.nextInt();
                    patentDocumentCreate.setAuthorId(authorID);
                    System.out.println(Messages.YEAR_ADD);
                    int yearAdd = scanner.nextInt();
                    System.out.println(Messages.MONTH_ADD);
                    int monthAdd = scanner.nextInt();
                    System.out.println(Messages.DAY_ADD);
                    int dayAdd = scanner.nextInt();
                    patentDocumentCreate.setDateAddedToTheLibrary(yearAdd, monthAdd, dayAdd);
                    System.out.println(Messages.YEAR_MOD);
                    int yearMod = scanner.nextInt();
                    System.out.println(Messages.MONTH_MOD);
                    int monthMod = scanner.nextInt();
                    System.out.println(Messages.DAY_MOD);
                    int dayMod = scanner.nextInt();
                    patentDocumentCreate.setDateOfModification(yearMod, monthMod, dayMod);
                    PatentDocumentsControllerInterface.createPatentDocument(patentDocumentCreate);
                    log.info("?????? ???????????? ???????????? ?? ???????????? " + patentDocumentCreate.getName());
                    break;
                case "2":
                    PatentDocument patentDocumentDelete = new PatentDocument();
                    System.out.println(Messages.ID);
                    int id = scanner.nextInt();
                    patentDocumentDelete.setId(id);
                    PatentDocumentsControllerInterface.deletePatentDocument(patentDocumentDelete);
                    log.info("?????? ???????????? ???????????? ?? ID " + patentDocumentDelete.getId());
                    break;
                case "3":
                    System.out.println(Messages.NAME);
                    String namePatentDocument = scanner.nextLine();
                    PatentDocumentsControllerInterface.searchPatentDocumentName(namePatentDocument);
                    System.out.println(Messages.CREATE);
                    System.out.println(Messages.DELETE);
                    System.out.println(Messages.SEARCH);
                    System.out.println(Messages.UPDATE);
                    System.out.println(Messages.CONTENT);
                    System.out.println(Messages.EXIT);
                    break;
                case "4":
                    PatentDocument patentDocumentUpdate = new PatentDocument();
                    System.out.println(Messages.ID);
                    int idUpdateBook = scanner.nextInt();
                    patentDocumentUpdate.setId(idUpdateBook);
                    PatentDocumentsControllerInterface.updatePatentDocument(patentDocumentUpdate);
                    log.info("???????? ?????????????????????? ?????????????????????? ?? ?????????????? ?? ID " + patentDocumentUpdate.getId());
                    break;
                case "5":
                    PatentDocumentsControllerInterface.showContent();
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
