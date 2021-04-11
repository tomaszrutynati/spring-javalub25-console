package pl.sda.view;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@AllArgsConstructor
public class MainMenu {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainMenu.class);

    private AddApplicationView addApplicationView;
    private DisplayApplicationsView displayApplicationsView;
    private RemoveApplicationView removeApplicationView;
    private UpdateApplicationView updateApplicationView;
    private Scanner scanner;

    public void displayOnConsole() {
        while (true) {
            LOGGER.info("Dostępne akcje: ");
            LOGGER.info("1. Wyświetl aplikacje ");
            LOGGER.info("2. Dodaj aplikacje ");
            LOGGER.info("3. Zmień wersje aplikacji ");
            LOGGER.info("4. Usuń ");
            LOGGER.info("5. Wyjdź ");

            boolean shouldContinue = chooseOperation();
            if (!shouldContinue) {
                return;
            }
        }
    }

    private boolean chooseOperation() {
        int operation = scanner.nextInt();
        scanner.nextLine();

        switch (operation) {
            case 1:
                displayApplicationsView.displayOnConsole();
                break;
            case 2:
                addApplicationView.displayOnConsole();
                break;
            case 3:
                updateApplicationView.displayOnConsole();
                break;
            case 4:
                removeApplicationView.displayOnConsole();
                break;
            case 5:
                return false;
            default:
                LOGGER.info("Nieznana operacja");

        }
        return true;
    }
}
