package gosk.szymon.io;

import lombok.experimental.UtilityClass;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@UtilityClass
public class PropertiesLoader {

    public Properties from(String filepath) throws IOException {
        Properties properties = new Properties();
        try (InputStream inputStream = new ClassPathResource(filepath).getInputStream()) {
            properties.load(inputStream);
        }
        return properties;
    }

}
