package ru.mtsbank.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.mtsbank.service.CreateAnimalService;
import ru.mtsbank.service.CreateAnimalServiceImpl;
import searchAnimal.AnimalsRepository;
import searchAnimal.AnimalsRepositoryImpl;

@Configuration
@EnableConfigurationProperties(AnimalProperties.class)
public class AnimalConfiguration {
    @Bean
    public CreateAnimalServiceImpl createAnimalService(AnimalProperties animalProperties) {
        return new CreateAnimalServiceImpl(animalProperties);
    }

    @Bean
    public AnimalsRepository animalsRepository(CreateAnimalService createAnimalService) {
        return new AnimalsRepositoryImpl(createAnimalService);
    }
}
