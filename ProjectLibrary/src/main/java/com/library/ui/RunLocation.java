package com.library.ui;


import com.library.domain.ControllerInterfaces.LocationControllerInterface;
import com.library.domain.models.Location;
import com.library.domain.models.messages.Messages;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class RunLocation implements RunInterface {
    private static final Logger log = Logger.getLogger(RunBook.class);
    LocationControllerInterface locationControllerInterface;

    @Autowired
    public RunLocation(@Qualifier("locationController") LocationControllerInterface locationControllerInterface) {
        this.locationControllerInterface = locationControllerInterface;
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
                    System.out.println(Messages.LOCATION_NAME);
                    Location locationCreate = new Location();
                    String name = scanner.nextLine();
                    locationCreate.setName(name);
                    System.out.println(Messages.LOCATION_CITY);
                    String city = scanner.nextLine();
                    locationCreate.setCity(city);
                    System.out.println(Messages.LOCATION_STATE);
                    String state = scanner.nextLine();
                    locationCreate.setState(state);
                    locationControllerInterface.createLocation(locationCreate);
                    log.info("Была добавлена Локация " + locationCreate.getName());
                    break;
                case "2":
                    Location locationDelete = new Location();
                    System.out.println(Messages.ID);
                    int id = scanner.nextInt();
                    locationDelete.setId(id);
                    locationControllerInterface.deleteLocation(locationDelete);
                    log.info("Был удален автор c ID " + locationDelete.getId());
                    break;
                case "3":
                    System.out.println(Messages.LOCATION_NAME);
                    String nameLocation = scanner.nextLine();
                    locationControllerInterface.searchLocationName(nameLocation);

                    break;
                case "4":
                    Location locationUpdate = new Location();
                    System.out.println(Messages.ID);
                    int idUpdateAuthor = scanner.nextInt();
                    locationUpdate.setId(idUpdateAuthor);
                    locationControllerInterface.updateLocation(locationUpdate);
                    log.info("Были произведены изменения в авторе c ID " + locationUpdate.getId());
                    break;
                case "5":
                    locationControllerInterface.showContent();
                    break;
                case "exit":
                    b = false;
                    break;


            }
        }
    }
}
