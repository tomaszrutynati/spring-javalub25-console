package pl.sda;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.sda.view.MainMenu;

@SpringBootApplication
public class SpringTrainingJavalub25Application implements CommandLineRunner {

    public static final Logger LOGGER = LoggerFactory.getLogger(SpringTrainingJavalub25Application.class);

    @Autowired
    private MainMenu mainMenu;

    public static void main(String[] args) {
        SpringApplication.run(SpringTrainingJavalub25Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        mainMenu.displayOnConsole();
    }
}
