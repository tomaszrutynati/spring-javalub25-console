package pl.sda.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class MainMenu {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainMenu.class);

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
        Scanner scanner = new Scanner(System.in);
        int operation = scanner.nextInt();
        scanner.nextLine();

        switch (operation) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                return false;
            default:
                LOGGER.info("Nieznana operacja");

        }
        return true;
    }
}
