package main;

import config.animalConfig;
import createAnimal.CreateAnimalService;
import createAnimal.CreateAnimalServiceImpl;
import descriptionAnimal.AbstractAnimal;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import searchAnimal.AnimalsRepository;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(animalConfig.class);

        AnimalsRepository animalsRepository = context.getBean(AnimalsRepository.class);

        CreateAnimalService animalService = new CreateAnimalServiceImpl();
        AbstractAnimal[] createdAnimals = animalService.createAnimals();
        AbstractAnimal[] createdAnimals1 = animalService.createAnimals(5);

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
