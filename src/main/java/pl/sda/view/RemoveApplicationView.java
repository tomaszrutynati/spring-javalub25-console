package pl.sda.view;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import pl.sda.service.ApplicationService;

import java.util.Scanner;

@Component
@AllArgsConstructor
public class RemoveApplicationView  implements View {

    private static final Logger LOGGER = LoggerFactory.getLogger(RemoveApplicationView.class);

    private ApplicationService applicationService;
    private Scanner scanner;

    public void displayOnConsole() {
        LOGGER.info("Podaj id aplikacji: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        try {
            applicationService.removeApplication(id);
        } catch (IllegalStateException ex) {
            LOGGER.error(ex.getMessage());
        }
    }

    @Override
    public ViewOperation supportedOperation() {
        return ViewOperation.REMOVE;
    }
}
