package edu.hw4;

import java.util.*;
import java.util.stream.Collectors;
import static edu.hw4.Animal.Type.*;
import static edu.hw4.Animal.Type;

public class Main {

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
            .skip(k-1)
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
            .filter(a -> a.bites() && a.height() > 100)
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
                } else if (a.type().equals(DOG) && a.bites()){
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

//    public static Animal task19(List<Animal> animals) {
//        return animals.stream()
//            .collect(Collectors.groupingBy())
//
//    }
//
//    public static Animal task20(List<List<Animal>> animals) {
//        return animals.stream()
//            .flatMap(List::stream)
//            .filter(a -> a.type().equals(FISH))
//            .max(Comparator.comparingInt(Animal::weight)).get();
//    }



    public static void main(String[] args) {
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("1", BIRD, Animal.Sex.M, 1,1, 3,false));
        animals.add(new Animal("12", BIRD, Animal.Sex.F, 2,4, 5,false));
        animals.add(new Animal("123", FISH, Animal.Sex.F, 3,3, 8,false));
        animals.add(new Animal("1234", FISH, Animal.Sex.F, 0,2, 9,false));
        List<Animal> animals1 = new ArrayList<>();
        animals1.add(new Animal("1", BIRD, Animal.Sex.M, 1,1, 3,false));
        animals1.add(new Animal("12", BIRD, Animal.Sex.M, 2,4, 5,false));
        animals1.add(new Animal("123", FISH, Animal.Sex.M, 3,3, 20,false));
        animals1.add(new Animal("1234", FISH, Animal.Sex.M, 0,2, 9,false));
        List<List<Animal>> list = new ArrayList<>();
        list.add(animals);
        list.add(animals1);
//        System.out.println(task1(animals).toString());
//        System.out.println(task2(animals, 2).toString());
//        System.out.println(task4(animals).toString());
//        System.out.println(task4(animals).toString());
//        System.out.println(task7(animals, 1).toString());
//        System.out.println(task9(animals));
//        System.out.println(task6(animals));
        System.out.println(task5(animals));
    }
}
