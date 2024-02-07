package config;

import createAnimal.CreateAnimalService;
import createAnimal.CreateAnimalServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import searchAnimal.AnimalsRepository;
import searchAnimal.AnimalsRepositoryImpl;

@Configuration
public class AnimalConfig {
    @Bean
    public CreateAnimalService createAnimalService() {
        return new CreateAnimalServiceImpl();
    }

    @Bean
    public AnimalsRepository animalsRepository() {
        return new AnimalsRepositoryImpl(createAnimalService());
    }
}
