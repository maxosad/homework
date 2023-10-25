package edu.hw4;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import static edu.hw4.Animal.Type.BIRD;
import static edu.hw4.Animal.Type.FISH;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void task1() {
        List<Animal> animals = new ArrayList<>();
        Animal a1 = new Animal("1", BIRD, Animal.Sex.M, 1,1, 3,false);
        Animal a2 = new Animal("1", BIRD, Animal.Sex.M, 1,4, 5,false);
        Animal a3 = new Animal("1", BIRD, Animal.Sex.M, 1,3, 8,false);
        Animal a4 = new Animal("1", BIRD, Animal.Sex.M, 1,2, 9,false);
        animals.add(a1);
        animals.add(a2);
        animals.add(a3);
        animals.add(a4);
        List<Animal> targetAnimals = new ArrayList<>(List.of(new Animal[]{
            a1, a4, a3, a2
        }));
        assertEquals(targetAnimals, Main.task1(animals));
    }

    @Test
    void task2() {
//        List<Animal> animals = new ArrayList<>();
//        animals.add(new Animal("1", BIRD, Animal.Sex.M, 1,1, 3,false));
//        animals.add(new Animal("1", BIRD, Animal.Sex.M, 1,4, 5,false));
//        animals.add(new Animal("1", BIRD, Animal.Sex.M, 1,3, 8,false));
//        animals.add(new Animal("1", BIRD, Animal.Sex.M, 1,2, 9,false));
//        System.out.println(task1(animals, 2).toString());
    }

    @Test
    void task3() {
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("1", BIRD, Animal.Sex.M, 1,1, 3,false));
        animals.add(new Animal("12", BIRD, Animal.Sex.M, 1,4, 5,false));
        animals.add(new Animal("123", FISH, Animal.Sex.M, 1,3, 8,false));
        animals.add(new Animal("1234", FISH, Animal.Sex.M, 1,2, 9,false));
        System.out.println(Main.task4(animals).toString());
    }

    @Test
    void task4() {
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("1", BIRD, Animal.Sex.M, 1,1, 3,false));
        animals.add(new Animal("12", BIRD, Animal.Sex.M, 1,4, 5,false));
        animals.add(new Animal("123", BIRD, Animal.Sex.M, 1,3, 8,false));
        animals.add(new Animal("1234", BIRD, Animal.Sex.M, 1,2, 9,false));
//        System.out.println(task1(animals).toString());
//        System.out.println(task2(animals, 2).toString());
        System.out.println(Main.task4(animals).toString());
    }

    @Test
    void task7() {
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("1", BIRD, Animal.Sex.M, 1,1, 3,false));
        animals.add(new Animal("12", BIRD, Animal.Sex.M, 5,4, 5,false));
        animals.add(new Animal("123", FISH, Animal.Sex.M, 3,3, 8,false));
        animals.add(new Animal("1234", FISH, Animal.Sex.M, 9,2, 9,false));
//        System.out.println(task1(animals).toString());
//        System.out.println(task2(animals, 2).toString());
//        System.out.println(task4(animals).toString());
//        System.out.println(task4(animals).toString());
        System.out.println(Main.task7(animals, 1).toString());
    }

    @Test
    void task8() {
    }

    @Test
    void task9() {
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("1", BIRD, Animal.Sex.M, 1,1, 3,false));
        animals.add(new Animal("12", BIRD, Animal.Sex.M, 5,4, 5,false));
        animals.add(new Animal("123", FISH, Animal.Sex.M, 3,3, 8,false));
        animals.add(new Animal("1234", FISH, Animal.Sex.M, 9,2, 9,false));
        System.out.println(Main.task9(animals));
    }

    @Test
    void task10() {
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("1", BIRD, Animal.Sex.M, 1,1, 3,false));
        animals.add(new Animal("12", BIRD, Animal.Sex.M, 2,4, 5,false));
        animals.add(new Animal("123", FISH, Animal.Sex.M, 3,3, 8,false));
        animals.add(new Animal("1234", FISH, Animal.Sex.M, 0,2, 9,false));
//        System.out.println(task1(animals).toString());
//        System.out.println(task2(animals, 2).toString());
//        System.out.println(task4(animals).toString());
//        System.out.println(task4(animals).toString());
//        System.out.println(task7(animals, 1).toString());
//        System.out.println(task9(animals));
        System.out.println(Main.task10(animals));
    }

    @Test
    void task11() {
    }

    @Test
    void task12() {
    }

    @Test
    void task13() {
    }

    @Test
    void task14() {
    }

    @Test
    void task15() {
    }

    @Test
    void task16() {
    }

    @Test
    void task17() {
    }

    @Test
    void task18() {
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("1", BIRD, Animal.Sex.M, 1,1, 3,false));
        animals.add(new Animal("12", BIRD, Animal.Sex.M, 2,4, 5,false));
        animals.add(new Animal("123", FISH, Animal.Sex.M, 3,3, 8,false));
        animals.add(new Animal("1234", FISH, Animal.Sex.M, 0,2, 9,false));
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
        System.out.println(Main.task18(list));
    }
}
