package thorium;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import thorium.web.io.CSVImporter;
import thorium.web.repositories.RenderRepository;

@SpringBootApplication
public class WebApplication {

    private static final Logger log = LogManager.getLogger(WebApplication.class.getName());

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(RenderRepository repository) {
        return (args) -> CSVImporter.importToRepository("test.csv", repository);
    }

}
