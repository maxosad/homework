package edu.hw4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import static edu.hw4.Animal.Type.*;
import static edu.hw4.Animal.Type;

public class Main {

    public static List<Animal> task1(List<Animal> animals) {
        return animals.stream()
            .sorted((Animal a1, Animal a2) -> a1.height() - a2.height())
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
            .max((a1, a2) -> a1.name().length() - a2.name().length()).get();
    }

//    public static Animal task5(List<Animal> animals) {
//        return animals.stream()
//            .max((a1, a2) -> a1.name().length() - a2.name().length()).get();
//    }

//    public static Animal task6(List<Animal> animals) {
//        return animals.stream()
//            .collect(Collectors.groupingBy(Animal::))
//    }

    public static Animal task7(List<Animal> animals, int k) {
        return animals.stream()
            .sorted((Animal a1, Animal a2) -> a2.age() - a1.age())
            .skip(k-1)
            .findFirst().get();
    }

    public static Animal task8(List<Animal> animals, int k) {
        return animals.stream()
            .filter(a1 -> a1.height() < k)
            .max((a1, a2) -> a1.weight() - a2.weight()).get();
    }


    public static void main(String[] args) {
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("1", BIRD, Animal.Sex.M, 1,1, 3,false));
        animals.add(new Animal("12", BIRD, Animal.Sex.M, 5,4, 5,false));
        animals.add(new Animal("123", FISH, Animal.Sex.M, 3,3, 8,false));
        animals.add(new Animal("1234", FISH, Animal.Sex.M, 9,2, 9,false));
//        System.out.println(task1(animals).toString());
//        System.out.println(task2(animals, 2).toString());
//        System.out.println(task4(animals).toString());
//        System.out.println(task4(animals).toString());
        System.out.println(task7(animals, 1).toString());
    }
}
