package ru.mtsbank.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.mtsbank.searchAnimal.AnimalsRepository;
import ru.mtsbank.searchAnimal.AnimalsRepositoryImpl;
import ru.mtsbank.createService.CreateAnimalServiceImpl;

@Configuration
@EnableConfigurationProperties(AnimalProperties.class)
@ComponentScan("ru.mtsbank")
public class AnimalConfiguration {
    @Bean
    public CreateAnimalServiceImpl createAnimalService(AnimalProperties animalProperties) {
        return new CreateAnimalServiceImpl(animalProperties);
    }

    @Bean
    public AnimalsRepository animalsRepository(CreateAnimalServiceImpl createAnimalService) {
        return new AnimalsRepositoryImpl(createAnimalService);
    }

}

