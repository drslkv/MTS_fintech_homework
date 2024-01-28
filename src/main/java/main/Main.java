package main;

import createAnimal.CreateAnimalService;
import createAnimal.CreateAnimalServiceImpl;
import descriptionAnimal.AbstractAnimal;
import searchAnimal.SearchAnimalService;
import searchAnimal.SearchAnimalServiceImpl;

public class Main {
    public static void main(String[] args) {
        CreateAnimalService animalService = new CreateAnimalServiceImpl();
        AbstractAnimal[] createdAnimals = animalService.createAnimals();
        AbstractAnimal[] createdAnimals1 = animalService.createAnimals(5);

        SearchAnimalService searchService = new SearchAnimalServiceImpl();
        AbstractAnimal[] leapYearNames = searchService.findLeapYearNames(createdAnimals);
        AbstractAnimal[] olderAnimals = searchService.findOlderAnimal(createdAnimals, 5);

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
        for (AbstractAnimal animal : leapYearNames) {
            animalService.printAnimalDetails(animal);
        }

        System.out.println("\nage search");
        for (AbstractAnimal animal : olderAnimals) {
            animalService.printAnimalDetails(animal);
        }

        System.out.println("\nduplicate search");
        searchService.findDuplicate(createdAnimals);
    }
}
