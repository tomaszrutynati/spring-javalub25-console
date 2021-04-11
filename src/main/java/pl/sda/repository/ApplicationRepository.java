package pl.sda.repository;

import java.util.List;
import java.util.Optional;

public interface ApplicationRepository {
    void addApplication(String name, String producer, String version);
    void updateVersion(Long id, String newVersion);
    void removeApplication(Long id);
    List<Application> getAllApplications();
    Optional<Application> getApplicationById(Long id);
}
