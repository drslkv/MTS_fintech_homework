package ru.mtsbank.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.mtsbank.createService.CreateAnimalServiceImpl;
import ru.mtsbank.searchAnimal.AnimalsRepository;
import ru.mtsbank.searchAnimal.AnimalsRepositoryImpl;

@Configuration
public class AnimalsRepositoryConfig {
    @Bean
    public AnimalsRepository animalsRepository(CreateAnimalServiceImpl createAnimalService) {
        return new AnimalsRepositoryImpl(createAnimalService);
    }
}
