package starterTest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.mtsbank.config.AnimalProperties;
import ru.mtsbank.createService.CreateAnimalServiceImpl;

@Configuration
public class TestConfig {
    @Bean
    public AnimalProperties animalProperties() {
        return new AnimalProperties();
    }

    @Bean
    public CreateAnimalServiceImpl createAnimalService(AnimalProperties animalProperties) {
        return new CreateAnimalServiceImpl(animalProperties);
    }
}
