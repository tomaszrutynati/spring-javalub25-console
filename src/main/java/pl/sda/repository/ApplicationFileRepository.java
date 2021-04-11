package pl.sda.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Profile("!local")
@Repository
public class ApplicationFileRepository implements ApplicationRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationFileRepository.class);

    private static Long ID = 0L;
    private final List<Application> applications = new ArrayList<>();

    private String filePath;

    public ApplicationFileRepository(@Value("${apps.file}") String filePath) {
        this.filePath = filePath;
    }

    @PostConstruct
    public void postConstruct() {
        Path path = Paths.get(filePath);
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            List<Application> appsFromFile = reader.lines()
                    .map(line -> line.split(";"))
                    .map(fields -> new Application(Long.valueOf(fields[0]), fields[1], fields[2], fields[3]))
                    .collect(Collectors.toList());
            applications.addAll(appsFromFile);
        } catch (IOException e) {
            LOGGER.error("Błąd odczytu z pliku", e);
        }
    }

    private void storeChangesInFile() {
        Path path = Paths.get(filePath);
        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardOpenOption.TRUNCATE_EXISTING)) {
            for (Application app : applications) {
                writer.write(app.getId() + ";" + app.getName() + ";" + app.getProducer() + ";" + app.getVersion());
                writer.newLine();
            }
        } catch (IOException e) {
            LOGGER.error("Błąd zapisu do pliku", e);
        }
    }

    public void addApplication(String name, String producer, String version) {
        applications.add(new Application(++ID, name, producer, version));
        storeChangesInFile();
    }

    public void updateVersion(Long id, String newVersion) {
        for (Application app : applications) {
            if (app.getId() == id) {
                app.updateVersion(newVersion);
                storeChangesInFile();
                break;
            }
        }
    }

    public void removeApplication(Long id) {
        applications.removeIf(app -> app.getId() == id);
        storeChangesInFile();
    }

    public List<Application> getAllApplications() {
        return applications;
    }

    public Optional<Application> getApplicationById(Long id) {
        return applications.stream().filter(app -> app.getId() == id).findFirst();
    }
}
