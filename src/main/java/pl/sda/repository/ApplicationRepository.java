package pl.sda.repository;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ApplicationRepository {

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

}
