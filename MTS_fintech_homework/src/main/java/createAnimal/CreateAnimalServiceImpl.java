package createAnimal;

import animals.Cat;
import animals.Dog;
import animals.Shark;
import animals.Wolf;
import descriptionAnimal.AbstractAnimal;
import descriptionAnimal.Animal;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class CreateAnimalServiceImpl implements CreateAnimalService {
    /**
     * Генерирует случайную дату рождения между 1 января 2010 года и 31 декабря 2023 года.
     *
     * @return Случайно сгенерированная дата рождения.
     */
    public LocalDate randBirthDate() {
        LocalDate startDate = LocalDate.of(2010, 1, 1);
        LocalDate endDate = LocalDate.of(2023, 12, 31);

        long startEpochDay = startDate.toEpochDay();
        long endEpochDay = endDate.toEpochDay();
        long randomEpochDay = ThreadLocalRandom.current().nextLong(startEpochDay, endEpochDay);

        return LocalDate.ofEpochDay(randomEpochDay);
    }

    /**
     * Выводит подробности заданного AbstractAnimal.
     *
     * @param animal AbstractAnimal, подробности которого будут выведены.
     */
    public void printAnimalDetails(AbstractAnimal animal) {
        System.out.println("Created: " + animal.getBreed() + " " + animal.getName() + " " +
                animal.getCharacter() + " " + animal.getCost() + " " + animal.getBirthDate());
    }

    /**
     * Возвращает случайное животное (Animal) на основе паттерна фабрика.
     *
     * @return Случайное животное, созданное из случайных параметров.
     */
    public Animal getRandomAnimalFactory() {
        Random random = new Random();

        int randomAnimalType = random.nextInt(4);
        int randomIndexName = random.nextInt(names.length);
        int randomIndexCharacter= random.nextInt(character.length);
        LocalDate birthDate = randBirthDate();

        switch (randomAnimalType) {
            case 0:
                return new Cat("Cat", names[randomIndexName],
                        BigDecimal.valueOf(random.nextDouble() * 1000), character[randomIndexCharacter],
                        birthDate);
            case 1:
                return new Dog("Dog", names[randomIndexName],
                        BigDecimal.valueOf(random.nextDouble() * 1000), character[randomIndexCharacter],
                        birthDate);
            case 2:
                return new Shark("Shark", names[randomIndexName],
                        BigDecimal.valueOf(random.nextDouble() * 1000), character[randomIndexCharacter],
                        birthDate);
            case 3:
                return new Wolf("Wolf", names[randomIndexName],
                        BigDecimal.valueOf(random.nextDouble() * 1000), character[randomIndexCharacter],
                        birthDate);
            default:
                throw new IllegalArgumentException("Error animal type");
        }
    }

    /**
     * Создает массив AbstractAnimal с помощью цикла for.
     *
     * @param n Размер массива животных.
     * @return Массив AbstractAnimal.
     */
    public AbstractAnimal[] createAnimals(int n) {
        AbstractAnimal[] animals = new AbstractAnimal[n];

        for (int i = 0; i < n; i++) {
            Animal animalFactory = getRandomAnimalFactory();
            AbstractAnimal animal = animalFactory.createAnimal("_" + i,
                    names[random.nextInt(names.length)],
                    BigDecimal.valueOf(random.nextDouble() * 1000),
                    character[random.nextInt(character.length)],
                    randBirthDate());

            animals[i] = animal;
        }

        return animals;
    }

    /**
     * Создает массив AbstractAnimal с помощью цикла while до тех пор,
     * пока не будет создано 10 видов животных
     * или пока не будет достигнуто максимальное количество итераций (100).
     *
     * @return Массив уникальных AbstractAnimal.
     */
    @Override
    public AbstractAnimal[] createAnimals() {
        int iterations = 0;
        List<AbstractAnimal> animalsList = new ArrayList<>();

        do {
            Animal animalFactory = getRandomAnimalFactory();
            AbstractAnimal animal = animalFactory.createAnimal("_" + uniqueAnimals.size(),
                    names[random.nextInt(names.length)],
                    BigDecimal.valueOf(random.nextDouble() * 1000),
                    character[random.nextInt(character.length)],
                    randBirthDate());

            if (uniqueAnimals.add(animal.getBreed())) {
                animalsList.add(animal);
            } else {
                System.out.println("Not added: " + animal.getBreed());
            }

            iterations++;
        } while (uniqueAnimals.size() < 10 && iterations < 100);

        return animalsList.toArray(new AbstractAnimal[0]);
    }
}
