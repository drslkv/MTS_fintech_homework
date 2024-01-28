package searchAnimal;

import descriptionAnimal.AbstractAnimal;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SearchAnimalServiceImpl implements SearchAnimalService {
    /**
     * Находит животных с именами, соответствующими году високосного дня их даты рождения.
     *
     * @param animals Массив животных для поиска.
     * @return Массив животных с именами, соответствующими году високосного дня.
     */
    @Override
    public AbstractAnimal[] findLeapYearNames(AbstractAnimal[] animals) {
        List<AbstractAnimal> leapYearNamesList = new ArrayList<>();

        for (AbstractAnimal animal : animals) {
            int birthYear = animal.getBirthDate().getYear();

            if (LocalDate.of(birthYear, 1, 1).isLeapYear()) {
                leapYearNamesList.add(animal);
            }
        }

        return leapYearNamesList.toArray(new AbstractAnimal[0]);
    }

    /**
     * Находит животных, которые старше заданного возраста.
     *
     * @param animals Массив животных для поиска.
     * @param age     Возраст, с которым сравниваются животные.
     * @return Массив животных, старше заданного возраста.
     */
    @Override
    public AbstractAnimal[] findOlderAnimal(AbstractAnimal[] animals, int age) {
        List<AbstractAnimal> olderAnimalsList = new ArrayList<>();

        LocalDate currentDate = LocalDate.now();

        for (AbstractAnimal animal : animals) {
            int ageAnimal = Period.between(animal.getBirthDate(), currentDate).getYears();
            if (ageAnimal > age) {
                olderAnimalsList.add(animal);
            }
        }

        return olderAnimalsList.toArray(new AbstractAnimal[0]);
    }

    /**
     * Находит дубликаты животных с одинаковым именем и характером.
     *
     * @param animals Массив животных для проверки на дубликаты.
     */
    @Override
    public void findDuplicate(AbstractAnimal[] animals) {
        Set<String> uniqueAnimalsSet = new HashSet<>();

        for (AbstractAnimal animal : animals) {
            String key = animal.getName() + " " + animal.getCharacter();

            if (uniqueAnimalsSet.contains(key)) {
                System.out.println("Duplicate name and character found: "
                        + animal.getName() + " " + animal.getCharacter());
            } else {
                uniqueAnimalsSet.add(key);
            }
        }
    }
}
