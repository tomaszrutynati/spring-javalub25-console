package pl.sda.view;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import pl.sda.service.ApplicationService;

import java.util.Scanner;

@Component
@AllArgsConstructor
public class UpdateApplicationView {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateApplicationView.class);

    private ApplicationService applicationService;

    public void displayOnConsole() {
        Scanner scanner = new Scanner(System.in);
        LOGGER.info("Podaj id aplikacji: ");
        Long id = scanner.nextLong();
        scanner.nextLine();
        LOGGER.info("Podaj nową wersję aplikacji: ");
        String newVersion = scanner.nextLine();

        try {
            applicationService.updateVersion(id, newVersion);
        } catch (IllegalStateException ex) {
            LOGGER.error(ex.getMessage());
        }
    }
}
