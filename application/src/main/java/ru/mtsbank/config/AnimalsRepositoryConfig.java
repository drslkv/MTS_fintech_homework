package ru.mtsbank.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.mtsbank.create.CreateAnimalServiceImpl;
import ru.mtsbank.search.AnimalsRepository;
import ru.mtsbank.search.AnimalsRepositoryImpl;

@Configuration
public class AnimalsRepositoryConfig {
    @Bean
    public AnimalsRepository animalsRepository(CreateAnimalServiceImpl createAnimalService) {
        return new AnimalsRepositoryImpl(createAnimalService);
    }
}
