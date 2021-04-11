package pl.sda.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Application {

    private Long id;
    private String name;
    private String producer;
    private String version;

    public void updateVersion(String newVersion) {
        this.version = newVersion;
    }
}
