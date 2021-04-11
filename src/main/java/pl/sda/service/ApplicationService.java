package pl.sda.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.sda.repository.Application;
import pl.sda.repository.ApplicationCollectionRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ApplicationService {

    private ApplicationCollectionRepository repository;

    //Ten konstruktor zostanie zastapiony przez Lombok
    //Jesli klasa bedaca Beanem ma tylko jeden konstruktor to nie musimy pisac nad nim Autowired
    // @Autowired
    //public ApplicationService(ApplicationRepository repository) {
    //    this.repository = repository;
    //}

    public void addApplication(String producer, String name, String version) {
        if (producer == null || name == null || version == null) {
            throw new IllegalStateException("Brakujace wymagane dane");
        }

        List<Application> existingApplications = repository.getAllApplications();
        Optional<Application> sameApp = existingApplications.stream()
                .filter(app -> app.getProducer().equalsIgnoreCase(producer))
                .filter(app -> app.getName().equalsIgnoreCase(name))
                .findFirst();

        if (sameApp.isPresent()) {
            repository.updateVersion(sameApp.get().getId(), version);
        } else {
            repository.addApplication(name, producer, version);
        }
    }

    public void updateVersion(Long id, String newVersion) {
        Optional<Application> existingApp = repository.getApplicationById(id);
        if (existingApp.isPresent()) {
            repository.updateVersion(id, newVersion);
        } else {
            throw new IllegalStateException("Podana aplikacja nie istnieje");
        }
    }

    public void removeApplication(Long id) {
        Optional<Application> existingApp = repository.getApplicationById(id);
        if (existingApp.isPresent()) {
            repository.removeApplication(id);
        } else {
            throw new IllegalStateException("Podana aplikacja nie istnieje");
        }
    }

    public List<Application> getAll() {
        return repository.getAllApplications();
    }

}
