package com.library.ui;

import com.library.domain.ControllerInterfaces.MagazineControllerInterface;
import com.library.domain.models.Magazine;
import com.library.domain.models.messages.Messages;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class RunMagazine implements RunInterface {
    private static final Logger log = Logger.getLogger(RunMagazine.class);
    MagazineControllerInterface MagazineControllerInterface;

    @Autowired
    public RunMagazine(@Qualifier("magazineController") MagazineControllerInterface magazineControllerInterface) {
        MagazineControllerInterface = magazineControllerInterface;
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
                    Magazine magazineCreate = new Magazine();
                    System.out.println(Messages.NAME);
                    String name = scanner.nextLine();
                    magazineCreate.setName(name);
                    System.out.println(Messages.ID_LOCATION);
                    int locationId = scanner.nextInt();
                    magazineCreate.setLocationId(locationId);
                    System.out.println(Messages.YEAR_PUB);
                    int yearPub = scanner.nextInt();
                    System.out.println(Messages.MONTH_PUB);
                    int monthPub = scanner.nextInt();
                    System.out.println(Messages.DAY_PUB);
                    int dayPub = scanner.nextInt();
                    magazineCreate.setDateOfPublication(yearPub, monthPub, dayPub);
                    System.out.println(Messages.YEAR_ADD);
                    int yearAdd = scanner.nextInt();
                    System.out.println(Messages.MONTH_ADD);
                    int monthAdd = scanner.nextInt();
                    System.out.println(Messages.DAY_ADD);
                    int dayAdd = scanner.nextInt();
                    magazineCreate.setDateAddedToTheLibrary(yearAdd, monthAdd, dayAdd);
                    System.out.println(Messages.YEAR_MOD);
                    int yearMod = scanner.nextInt();
                    System.out.println(Messages.MONTH_MOD);
                    int monthMod = scanner.nextInt();
                    System.out.println(Messages.DAY_MOD);
                    int dayMod = scanner.nextInt();
                    magazineCreate.setDateOfModification(yearMod, monthMod, dayMod);
                    MagazineControllerInterface.createMagazine(magazineCreate);
                    log.info("?????? ???????????????? ???????????? ?? ???????????? " + magazineCreate.getName());
                    break;
                case "2":
                    Magazine magazineDelete = new Magazine();
                    System.out.println(Messages.ID);
                    int id = scanner.nextInt();
                    magazineDelete.setId(id);
                    MagazineControllerInterface.deleteMagazine(magazineDelete);
                    log.info("?????? ???????????? ???????????? ?? ID " + magazineDelete.getId());
                    break;
                case "3":
                    System.out.println(Messages.NAME);
                    String nameMagazine = scanner.nextLine();
                    MagazineControllerInterface.searchMagazineName(nameMagazine);
                    System.out.println(Messages.CREATE);
                    System.out.println(Messages.DELETE);
                    System.out.println(Messages.SEARCH);
                    System.out.println(Messages.UPDATE);
                    System.out.println(Messages.CONTENT);
                    System.out.println(Messages.EXIT);
                    break;
                case "4":
                    Magazine magazineUpdate = new Magazine();
                    System.out.println(Messages.ID);
                    int idUpdateMagazine = scanner.nextInt();
                    magazineUpdate.setId(idUpdateMagazine);
                    MagazineControllerInterface.updateMagazine(magazineUpdate);
                    log.info("???????? ?????????????????????? ?????????????????? ?? ?????????????? ?? ID " + magazineUpdate.getId());
                    break;
                case "5":
                    MagazineControllerInterface.showContent();
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
