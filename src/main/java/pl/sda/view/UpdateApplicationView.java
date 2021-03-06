package pl.sda.view;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import pl.sda.service.ApplicationService;

import java.util.Scanner;

@Component
@AllArgsConstructor
public class UpdateApplicationView implements View  {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateApplicationView.class);

    private ApplicationService applicationService;
    private Scanner scanner;

    public void displayOnConsole() {
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

    @Override
    public ViewOperation supportedOperation() {
        return ViewOperation.UPDATE;
    }
}
