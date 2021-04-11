package pl.sda.view;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import pl.sda.repository.Application;
import pl.sda.service.ApplicationService;

import java.util.List;

@Component
@AllArgsConstructor
public class DisplayApplicationsView {
    private static final Logger LOGGER = LoggerFactory.getLogger(DisplayApplicationsView.class);

    private ApplicationService applicationService;

    public void displayOnConsole() {
        List<Application> applications = applicationService.getAll();

        LOGGER.info("Id | Producent | Nazwa | Wersja");
        applications.forEach(
                app -> LOGGER.info(String.format("%d | %s | %s | %s",
                        app.getId(), app.getProducer(), app.getName(), app.getVersion())));

    }

}
