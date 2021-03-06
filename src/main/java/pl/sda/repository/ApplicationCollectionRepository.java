package pl.sda.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Profile("local")
@Repository
public class ApplicationCollectionRepository implements ApplicationRepository {

    private static Long ID = 0L;
    private final List<Application> applications = new ArrayList<>();

    public void addApplication(String name, String producer, String version) {
        applications.add(new Application(++ID, name, producer, version));
    }

    public void updateVersion(Long id, String newVersion) {
        for (Application app : applications) {
            if (app.getId() == id) {
                app.updateVersion(newVersion);
                break;
            }
        }
    }

    public void removeApplication(Long id) {
        applications.removeIf(app -> app.getId() == id);
    }

    public List<Application> getAllApplications() {
        return applications;
    }

    public Optional<Application> getApplicationById(Long id) {
        return applications.stream().filter(app -> app.getId() == id).findFirst();
    }
}
