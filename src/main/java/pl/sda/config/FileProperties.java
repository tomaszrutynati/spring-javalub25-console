package pl.sda.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "apps")
public class FileProperties {
    private String fileName;
    private String charset;
}
