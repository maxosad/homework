package edu.hw4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static edu.hw4.Animal.Type.BIRD;

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

//    public static List<Animal> task3(List<Animal> animals) {
//        return animals.stream()
//            .sorted((Animal a1, Animal a2) -> a2.weight() - a1.weight())
//            .limit(k)
//            .toList();
//    }
    public static void main(String[] args) {
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("1", BIRD, Animal.Sex.M, 1,1, 3,false));
        animals.add(new Animal("1", BIRD, Animal.Sex.M, 1,4, 5,false));
        animals.add(new Animal("1", BIRD, Animal.Sex.M, 1,3, 8,false));
        animals.add(new Animal("1", BIRD, Animal.Sex.M, 1,2, 9,false));
        System.out.println(task1(animals).toString());
        System.out.println(task2(animals, 2).toString());
    }
}
