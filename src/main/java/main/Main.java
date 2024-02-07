package main;

import config.AnimalConfig;
import createAnimal.CreateAnimalService;
import descriptionAnimal.AbstractAnimal;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import searchAnimal.AnimalsRepository;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AnimalConfig.class);

        CreateAnimalService animalService = context.getBean(CreateAnimalService.class);
        AbstractAnimal[] createdAnimals = animalService.createAnimals();
        AbstractAnimal[] createdAnimals1 = animalService.createAnimals(5);

        AnimalsRepository animalsRepository = context.getBean(AnimalsRepository.class);
        animalsRepository.addAnimals(Arrays.asList(createdAnimals));
        animalsRepository.addAnimals(Arrays.asList(createdAnimals1));

        System.out.println("CREATE");
        for (AbstractAnimal animal : createdAnimals) {
            animalService.printAnimalDetails(animal);
        }
        System.out.println(" ");
        for (AbstractAnimal animal : createdAnimals1) {
            animalService.printAnimalDetails(animal);
        }

        System.out.println("\nSEARCH");
        System.out.println("leap year search");
        animalsRepository.findLeapYearNames().forEach(animalService::printAnimalDetails);

        System.out.println("\nage search");
        animalsRepository.findOlderAnimal(5).forEach(animalService::printAnimalDetails);

        System.out.println("\nduplicate search");
        animalsRepository.printDuplicate();
    }
}
