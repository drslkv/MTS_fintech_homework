package searchAnimal;

import createAnimal.CreateAnimalService;
import descriptionAnimal.AbstractAnimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

@Repository
public class AnimalsRepositoryImpl implements AnimalsRepository {
    private final List<AbstractAnimal> animals = new ArrayList<>();

    @Autowired
    private CreateAnimalService createAnimalService;

    @Override
    public void addAll(List<AbstractAnimal> newAnimals) {
        animals.addAll(newAnimals);
    }

    @PostConstruct
    public void postConstruct() {
        createAnimalService.addAnimalsRepository(this);
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
}
