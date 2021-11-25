package com.library.ui;

import com.library.domain.ControllerInterfaces.DocumentsControllerInterface;
import com.library.domain.Controllers.DocumentController;
import com.library.domain.models.Document;
import com.library.domain.models.messages.Messages;

import java.util.Scanner;

public class RunDocument implements RunInterface {
    @Override
    public void show() {
        DocumentsControllerInterface documentsControllerInterface = new DocumentController();
        Scanner scanner = new Scanner(System.in);
        System.out.println(Messages.VARIANTS);

        boolean b = true;
        while (b) {

            String command = scanner.nextLine();
            switch (command) {
                case "1":
                    Document documentCreate = new Document();
                    System.out.println(Messages.NAME);
                    String name = scanner.nextLine();
                    documentCreate.setName(name);
                    System.out.println(Messages.NUMBER_DOC);
                    String docNumber = scanner.nextLine();
                    documentCreate.setDocumentNumber(docNumber);
                    System.out.println(Messages.LOCATION);
                    String location = scanner.nextLine();
                    documentCreate.setLocation(location);
                    System.out.println(Messages.YEAR_CREATE);
                    int yearCre = scanner.nextInt();
                    System.out.println(Messages.MONTH_CREATE);
                    int monthCre = scanner.nextInt();
                    System.out.println(Messages.DAY_CREATE);
                    int dayCre = scanner.nextInt();
                    documentCreate.setDateOfDocumentCreation(yearCre, monthCre, dayCre);
                    System.out.println(Messages.YEAR_ADD);
                    int yearAdd = scanner.nextInt();
                    System.out.println(Messages.MONTH_ADD);
                    int monthAdd = scanner.nextInt();
                    System.out.println(Messages.DAY_ADD);
                    int dayAdd = scanner.nextInt();
                    documentCreate.setDateAddedToTheLibrary(yearAdd, monthAdd, dayAdd);
                    System.out.println(Messages.YEAR_MOD);
                    int yearMod = scanner.nextInt();
                    System.out.println(Messages.MONTH_MOD);
                    int monthMod = scanner.nextInt();
                    System.out.println(Messages.DAY_MOD);
                    int dayMod = scanner.nextInt();
                    documentCreate.setDateOfModification(yearMod, monthMod, dayMod);
                    documentsControllerInterface.createDocument(documentCreate);
                    break;
                case "2":
                    Document DocDelete = new Document();
                    System.out.println(Messages.ID);
                    int id = scanner.nextInt();
                    DocDelete.setId(id);
                    documentsControllerInterface.deleteDocument(DocDelete);
                    break;
                case "3":
                    System.out.println(Messages.NAME);
                    String nameDoc = scanner.nextLine();
                    documentsControllerInterface.searchDocumentName(nameDoc);
                    System.out.println(Messages.CREATE);
                    System.out.println(Messages.DELETE);
                    System.out.println(Messages.SEARCH);
                    System.out.println(Messages.UPDATE);
                    System.out.println(Messages.CONTENT);
                    System.out.println(Messages.EXIT);
                    break;
                case "4":
                    Document docUpdate = new Document();
                    System.out.println(Messages.ID);
                    int idUpdateBook = scanner.nextInt();
                    docUpdate.setId(idUpdateBook);
                    documentsControllerInterface.updateDocument(docUpdate);
                    break;
                case "5":
                    documentsControllerInterface.showContent();
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
