import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import ru.mtsbank.config.AnimalProperties;
import ru.mtsbank.createService.CreateAnimalServiceImpl;

@TestConfiguration
public class TestConfig {
    @Bean
    public AnimalProperties animalProperties() {
        AnimalProperties animalProperties = new AnimalProperties();
        animalProperties.setDogName(new String[]{"${animals.dog.names}"});
        animalProperties.setCatName(new String[]{"${animals.cat.names}"});
        animalProperties.setSharkName(new String[]{"${animals.shark.names}"});
        animalProperties.setWolfName(new String[]{"${animals.wolf.names}"});

        return animalProperties;
    }

    @Bean
    public CreateAnimalServiceImpl createAnimalService(AnimalProperties animalProperties) {
        return new CreateAnimalServiceImpl(animalProperties);
    }
}
