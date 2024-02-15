package ru.mtsbank.searchAnimal;

import ru.mtsbank.descriptionAnimal.AbstractAnimal;
import ru.mtsbank.service.CreateAnimalService;

import javax.annotation.PostConstruct;
import java.beans.ConstructorProperties;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;


public class AnimalsRepositoryImpl implements AnimalsRepository {
    private final List<AbstractAnimal> animals;
    private final CreateAnimalService createAnimalService;

    @ConstructorProperties({"ru/mtsbank/animals"})
    public AnimalsRepositoryImpl(CreateAnimalService createAnimalService) {
        this.animals = new ArrayList<>();
        this.createAnimalService = createAnimalService;
    }

    @Override
    public void addAll(List<AbstractAnimal> newAnimals) {
        animals.addAll(newAnimals);
    }

    @PostConstruct
    public void postConstruct() {
        AbstractAnimal[] createdAnimals = createAnimalService.createAnimals();
        addAll(Arrays.asList(createdAnimals));

        System.out.println("CREATE");
        for (AbstractAnimal animal : createdAnimals) {
            createAnimalService.printAnimalDetails(animal);
        }
    }

    /**
     * Находит животных с именами, соответствующими году високосного дня их даты рождения.
     * @return Массив животных с именами, соответствующими году високосного дня.
     */
    @Override
    public List<AbstractAnimal> findLeapYearNames() {
        List<AbstractAnimal> leapYearNamesList = new ArrayList<>();

        for (AbstractAnimal animal : animals) {
            if (animal.getBirthDate().isLeapYear()) {
                leapYearNamesList.add(animal);
            }
        }

        return leapYearNamesList;
    }

    /**
     * Находит животных, которые старше заданного возраста.
     *
     * @param age     Возраст, с которым сравниваются животные.
     * @return Массив животных, старше заданного возраста.
     */
    @Override
    public List<AbstractAnimal> findOlderAnimal(int age) {
        List<AbstractAnimal> olderAnimalsList = new ArrayList<>();
        LocalDate currentDate = LocalDate.now();

        for (AbstractAnimal animal : animals) {
            int ageAnimal = Period.between(animal.getBirthDate(), currentDate).getYears();
            if (ageAnimal > age) {
                olderAnimalsList.add(animal);
            }
        }

        return olderAnimalsList;
    }

    /**
     * Находит дубликаты животных с одинаковым именем и характером.
     */
    @Override
    public Set<String> findDuplicate() {
        Set<String> uniqueAnimalsSet = new HashSet<>();
        Set<String> duplicates = new HashSet<>();

        for (AbstractAnimal animal : animals) {
            String key = animal.getName() + " " + animal.getCharacter();

            if (uniqueAnimalsSet.contains(key)) {
                duplicates.add("Duplicate name and character found: " + animal.getName()
                        + " " + animal.getCharacter());
            } else {
                uniqueAnimalsSet.add(key);
            }
        }

        return duplicates;
    }

    @Override
    public void printDuplicate() {
        Set<String> duplicates = findDuplicate();
        for (String duplicate : duplicates) {
            System.out.println(duplicate);
        }
    }

    public void addAnimals(List<AbstractAnimal> animals) {
        this.animals.addAll(animals);
    }
}
