package pl.sda.view;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import pl.sda.service.ApplicationService;

import java.util.Scanner;

@Component
@AllArgsConstructor
public class AddApplicationView {

    private static final Logger LOGGER = LoggerFactory.getLogger(AddApplicationView.class);

    private ApplicationService applicationService;
    private Scanner scanner;

    public void displayOnConsole() {
        LOGGER.info("Podaj nazwę aplikacji:");
        String name = scanner.nextLine();
        LOGGER.info("Podaj producenta aplikacji:");
        String producer = scanner.nextLine();
        LOGGER.info("Podaj wersję aplikacji:");
        String version = scanner.nextLine();

        applicationService.addApplication(producer, name, version);
    }
}
