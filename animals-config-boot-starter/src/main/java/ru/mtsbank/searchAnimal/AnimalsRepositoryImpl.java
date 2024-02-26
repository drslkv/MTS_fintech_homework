package ru.mtsbank.searchAnimal;

import ru.mtsbank.descriptionAnimal.AbstractAnimal;
import ru.mtsbank.createService.CreateAnimalService;
import ru.mtsbank.descriptionAnimal.Animal;

import javax.annotation.PostConstruct;
import java.beans.ConstructorProperties;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;


public class AnimalsRepositoryImpl implements AnimalsRepository {
    private final List<AbstractAnimal> animals;
    private final CreateAnimalService createAnimalService;

    /**
     * Создает экземпляр класса AnimalsRepositoryImpl.
     *
     * @param createAnimalService сервис создания животных
     */
    @ConstructorProperties({"ru/mtsbank/animals"})
    public AnimalsRepositoryImpl(CreateAnimalService createAnimalService) {
        this.animals = new ArrayList<>();
        this.createAnimalService = createAnimalService;
    }

    /**
     * Выполняет инициализацию после создания экземпляра класса.
     */
    @PostConstruct
    public void postConstruct() {
        Map<String, List<Animal>> createdAnimals = createAnimalService.createAnimals();

        System.out.println("CREATE");
        createdAnimals.forEach((type, animalList) -> {
            animalList.forEach(animal -> {
                createAnimalService.printAnimalDetails((AbstractAnimal) animal);
                animals.add((AbstractAnimal) animal);
            });
        });
    }

    /**
     * Находит животных с именами, соответствующими году високосного дня их даты рождения.
     *
     * @return Мапа животных с именами, соответствующими году високосного дня
     */
    @Override
    public Map<String, LocalDate> findLeapYearNames() {
        Map<String, LocalDate> leapYearNamesMap = new HashMap<>();

        for (AbstractAnimal animal : animals) {
            if (animal.getBirthDate().isLeapYear()) {
                leapYearNamesMap.put(animal.getBreed() + " " + animal.getName(), animal.getBirthDate());
            }
        }

        return leapYearNamesMap;
    }

    /**
     * Находит животных с именами, соответствующими году високосного дня их даты рождения.
     *
     * @return Мапа животных с именами, соответствующими году високосного дня
     */
    @Override
    public Map<AbstractAnimal, Integer> findOlderAnimal(int age) {
        Map<AbstractAnimal, Integer> olderAnimalsMap = new HashMap<>();
        LocalDate currentDate = LocalDate.now();

        for (AbstractAnimal animal : animals) {
            int ageAnimal = Period.between(animal.getBirthDate(), currentDate).getYears();
            if (ageAnimal > age) {
                olderAnimalsMap.put(animal, ageAnimal);
            }
        }

        return olderAnimalsMap;
    }

    /**
     * Находит животных с именами, соответствующими году високосного дня их даты рождения.
     *
     * @return Мапа животных с именами, соответствующими году високосного дня
     */
    @Override
    public Map<String, Integer> findDuplicate() {
        Map<String, Integer> duplicateCountMap = new HashMap<>();
        Map<String, Integer> tempMap = new HashMap<>();

        for (AbstractAnimal animal : animals) {
            String key = animal.getName() + " " + animal.getCharacter();
            tempMap.put(key, tempMap.getOrDefault(key, 0) + 1);
        }

        for (Map.Entry<String, Integer> entry : tempMap.entrySet()) {
            if (entry.getValue() > 1) {
                duplicateCountMap.put(entry.getKey(), entry.getValue());
            }
        }

        return duplicateCountMap;
    }
}
