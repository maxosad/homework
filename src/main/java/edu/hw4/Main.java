package edu.hw4;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import static edu.hw4.Animal.Type;
import static edu.hw4.Animal.Type.DOG;
import static edu.hw4.Animal.Type.FISH;
import static edu.hw4.Animal.Type.SPIDER;

public class Main {

    public static final int TASK_REQUIRE_HEIGHT = 100;

    private Main() { }

    public static List<Animal> task1(List<Animal> animals) {
        return animals.stream()
            .sorted(Comparator.comparingInt(Animal::height))
            .toList();
    }

    public static List<Animal> task2(List<Animal> animals, int k) {
        return animals.stream()
            .sorted((Animal a1, Animal a2) -> a2.weight() - a1.weight())
            .limit(k)
            .toList();
    }

    public static Map<Type, Long> task3(List<Animal> animals) {
        return animals.stream()
            .collect(Collectors.groupingBy(Animal::type, Collectors.counting()));
    }

    public static Animal task4(List<Animal> animals) {
        return animals.stream()
            .max(Comparator.comparingInt(a -> a.name().length())).orElse(null);
    }

    public static Animal.Sex task5(List<Animal> animals) {
        Map<Animal.Sex, Long> m = animals.stream()
            .collect(Collectors.groupingBy(Animal::sex, Collectors.counting()));
        return m.get(Animal.Sex.M).compareTo(m.get(Animal.Sex.F)) > 0 ? Animal.Sex.M : Animal.Sex.F;
    }

    public static Map<Type, Optional<Animal>> task6(List<Animal> animals) {
        return animals.stream()
            .collect(Collectors.groupingBy(Animal::type, Collectors.maxBy(Comparator.comparingInt(Animal::weight))));
    }

    public static Animal task7(List<Animal> animals, int k) {
        return animals.stream()
            .sorted((Animal a1, Animal a2) -> a2.age() - a1.age())
            .skip(k - 1)
            .findFirst().orElse(null);
    }

    public static Optional<Animal> task8(List<Animal> animals, int k) {
        return animals.stream()
            .filter(a1 -> a1.height() < k)
            .max(Comparator.comparingInt(Animal::weight));
    }

    public static int task9(List<Animal> animals) {
        return animals.stream()
            .map(Animal::paws)
            .reduce(0, Integer::sum);
    }

    public static List<Animal> task10(List<Animal> animals) {
        return animals.stream()
            .filter(a -> a.paws() != a.age())
            .toList();
    }

    public static List<Animal> task11(List<Animal> animals) {
        return animals.stream()
            .filter(a -> a.bites() && a.height() > TASK_REQUIRE_HEIGHT)
            .toList();
    }

    public static int task12(List<Animal> animals) {
        return animals.stream()
            .map(a -> {
                if (a.weight() > a.height()) {
                    return 1;
                } else {
                    return 0;
                }
            })
            .reduce(0, Integer::sum);
    }

    public static List<Animal> task13(List<Animal> animals) {
        return animals.stream()
            .filter(a -> {
                String[] animalName = a.name().split(" ");
                return animalName.length > 2;
            })
            .toList();
    }

    public static boolean task14(List<Animal> animals, int k) {
        return animals.stream()
            .anyMatch(a -> a.height() > k);
    }

    public static Map<Animal.Type, Integer> task15(List<Animal> animals, int k, int l) {
        return animals.stream()
            .filter(a -> a.age() >= k && a.age() <= l)
            .collect(Collectors.groupingBy(Animal::type, Collectors.summingInt(Animal::weight)));
    }

    public static List<Animal> task16(List<Animal> animals) {
        return animals.stream()
            .sorted((a1, a2) -> {
                if (!a1.type().equals(a2.type())) {
                    return a1.type().compareTo(a2.type());
                } else if (!a1.sex().equals(a2.sex())) {
                    return a1.sex().compareTo(a2.sex());
                } else {
                    return a1.name().compareTo(a2.name());
                }
            })
            .toList();
    }

    public static boolean task17(List<Animal> animals) {
        return animals.stream()
            .map(a -> {
                if (a.type().equals(SPIDER) && a.bites()) {
                    return 1;
                } else if (a.type().equals(DOG) && a.bites()) {
                    return -1;
                } else {
                    return 0;
                }
            })
            .reduce(0, Integer::sum) > 0;
    }

    public static Animal task18(List<List<Animal>> animals) {
        return animals.stream()
            .flatMap(List::stream)
            .filter(a -> a.type().equals(FISH))
            .max(Comparator.comparingInt(Animal::weight)).orElse(null);
    }

    public static Map<String, Set<ValidationError>> task19(List<Animal> animals) {
        return animals.stream()
            .flatMap(Validator::validate)
            .collect(Collectors.groupingBy(ValidationError::name, Collectors.toSet()));

    }

    public static Map<String, String> task20(List<Animal> animals) {
        return animals.stream()
            .flatMap(Validator::validate)
            .collect(Collectors.groupingBy(ValidationError::name,
                Collectors.mapping(ValidationError::mistakeField, Collectors.joining(" "))));
    }




}
