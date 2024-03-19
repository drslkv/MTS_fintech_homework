package ru.mtsbank.search;

import org.springframework.stereotype.Service;
import ru.mtsbank.description.AbstractAnimal;
import ru.mtsbank.create.CreateAnimalService;
import ru.mtsbank.description.Animal;
import ru.mtsbank.exaption.InsufficientAnimalsException;
import ru.mtsbank.exaption.InvalidAgeException;

import javax.annotation.PostConstruct;
import java.beans.ConstructorProperties;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

@Service
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
        return animals.stream()
                .filter(animal -> animal.getBirthDate().isLeapYear())
                .collect(Collectors.toMap(animal -> animal.getBreed() + " " + animal.getName(),
                        AbstractAnimal::getBirthDate));
    }

    /**
     * Находит животных с именами, соответствующими году високосного дня их даты рождения.
     *
     * @return Мапа животных с именами, соответствующими году високосного дня
     */
    @Override
    public Map<AbstractAnimal, Integer> findOlderAnimal(int age) {
        if (age < 0) {
            throw new InvalidAgeException("Age cannot be negative");
        }
        LocalDate currentDate = LocalDate.now();
        return animals.stream()
                .filter(animal -> Period.between(animal.getBirthDate(), currentDate).getYears() > age)
                .collect(Collectors.toMap(animal -> animal,
                        animal -> Period.between(animal.getBirthDate(), currentDate).getYears()));
    }

    /**
     * Находит животных с именами, соответствующими году високосного дня их даты рождения.
     *
     * @return Мапа животных с именами, соответствующими году високосного дня
     */
    @Override
    public Map<String, List<Animal>> findDuplicate() {
        return animals.stream()
                .collect(Collectors.groupingBy(animal -> animal.getName() + " " + animal.getCharacter()))
                .entrySet().stream()
                .filter(entry -> entry.getValue().size() > 1)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().stream().map(animal -> (Animal) animal).collect(Collectors.toList())
                ));
    }

    /**
     * Вычисляет средний возраст всех животных в коллекции.
     *
     * @return средний возраст всех животных в коллекции
     */
    @Override
    public double findAverageAge() {
        return animals.stream()
                .mapToDouble(animal -> Period.between(animal.getBirthDate(), LocalDate.now()).getYears())
                .average()
                .orElse(0);
    }

    /**
     * Находит всех животных, которые старше 5 лет и дороже, чем средняя стоимость всех животных в коллекции.
     *
     * @return список животных, соответствующих критериям
     */
    @Override
    public List<AbstractAnimal> findOldAndExpensive() {
        double averageCost = animals.stream()
                .mapToDouble(animal -> animal.getCost().doubleValue())
                .average()
                .orElse(0);

        return animals.stream()
                .filter(animal -> Period.between(animal.getBirthDate(), LocalDate.now()).getYears() > 5 &&
                        animal.getCost().compareTo(BigDecimal.valueOf(averageCost)) > 0)
                .sorted(Comparator.comparing(AbstractAnimal::getBirthDate))
                .collect(Collectors.toList());
    }

    /**
     * Находит имена трех животных с минимальной стоимостью.
     *
     * @return список имен трех животных с минимальной стоимостью
     */
    @Override
    public List<String> findMinCostAnimals() throws InsufficientAnimalsException {
        int requiredSize = 3;
        if (animals.size() < requiredSize) {
            throw new InsufficientAnimalsException("Insufficient number of animals in the collection");
        }
        return animals.stream()
                .sorted(Comparator.comparing(AbstractAnimal::getCost))
                .limit(3)
                .map(AbstractAnimal::getName)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }
}
