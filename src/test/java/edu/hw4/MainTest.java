package edu.hw4;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import static edu.hw4.Animal.Type.*;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    private static List<Animal> animals = null;
    @BeforeAll
    static void setUp() {
        Animal a1  = new Animal("BIRD 1", BIRD, Animal.Sex.M, 9,1, 3,false);
        Animal a2  = new Animal("BIRD12", BIRD, Animal.Sex.M, 1,4, 5,true);
        Animal a3  = new Animal("BIRD123", BIRD, Animal.Sex.F, 12,3, 8,true);
        Animal a4  = new Animal("BIRD1234", BIRD, Animal.Sex.M, 64,100, 45,false);
        Animal a5  = new Animal("BIRD12345", BIRD, Animal.Sex.F, 7,123, 243,false);
        Animal a6  = new Animal("BIRD123456", BIRD, Animal.Sex.M, 1,2, 72,true);
        Animal a7  = new Animal("BIRD1234567", BIRD, Animal.Sex.F, 2,2, 64,false);
        Animal a8  = new Animal("BIRD12345678", BIRD, Animal.Sex.M, 5,23, 26,true);
        Animal a9  = new Animal("CAT1", CAT, Animal.Sex.F, 3,2, 14,false);
        Animal a10 = new Animal("CAT12", CAT, Animal.Sex.F, 12,200, 9,true);
        Animal a11 = new Animal("CAT123", CAT, Animal.Sex.F, 6,2, 63,true);
        Animal a12 = new Animal("CAT1234", CAT, Animal.Sex.M, 7,5, 9,false);
        Animal a13 = new Animal("CAT12345", CAT, Animal.Sex.M, 4,2, 9,false);
        Animal a14 = new Animal("CAT123456", CAT, Animal.Sex.F, 6,253, 31,false);
        Animal a15 = new Animal("CAT1234567", CAT, Animal.Sex.M, 8,56, 9,false);
        Animal a16 = new Animal("CAT12345678", CAT, Animal.Sex.M, 3,29, 9,false);
        Animal a17 = new Animal("D OG 1", DOG, Animal.Sex.F, 9,35, 9,false);
        Animal a18 = new Animal("DOG12", DOG, Animal.Sex.M, 10,75, 1,true);
        Animal a19 = new Animal("DOG123", DOG, Animal.Sex.M, 12,888, 1,false);
        Animal a20 = new Animal("DOG1234", DOG, Animal.Sex.M, 1,456, 1,true);
        Animal a21 = new Animal("DOG12345", DOG, Animal.Sex.F, 23,147, 57,true);
        Animal a22 = new Animal("DOG123456", DOG, Animal.Sex.M, 5,390, 9,false);
        Animal a23 = new Animal("DOG1234567", DOG, Animal.Sex.M, 19,347, 9,true);
        Animal a24 = new Animal("DOG12345678", DOG, Animal.Sex.M, 4,245, 9,false);
        Animal a25 = new Animal("FISH 1", FISH, Animal.Sex.F, 8,643, 43,false);
        Animal a26 = new Animal("FISH 12", FISH, Animal.Sex.F, 51,731, 9,true);
        Animal a27 = new Animal("FISH123", FISH, Animal.Sex.M, 81,537, 9,false);
        Animal a28 = new Animal("SPIDER1", SPIDER, Animal.Sex.M, 3,326, 14,true);
        Animal a29 = new Animal("SPIDER12", SPIDER, Animal.Sex.M, 9,34, 9,false);
        Animal a30 = new Animal("SPIDER123", SPIDER, Animal.Sex.F, 7,6, 9,false);
        Animal a31 = new Animal("SPIDER1234", SPIDER, Animal.Sex.M, 8,49, 9,false);
        animals = new ArrayList<>(List.of(a1,
                a2,
                a3,
                a4,
                a5,
                a6,
                a7,
                a8,
                a9,
                a10,
                a11,
                a12,
                a13,
                a14,
                a15,
                a16,
                a17,
                a18,
                a19,
                a20,
                a21,
                a22,
                a23,
                a24,
                a25,
                a26,
                a27,
                a28,
                a29,
                a30,
                a31));

    }

    @Test
    void task1() {
        List<Animal> targetAnimals = new ArrayList<>(animals);
        targetAnimals.sort(Comparator.comparingInt(Animal::height));
        assertEquals(targetAnimals, Main.task1(animals));
    }

    @Test
    void task2() {
        List<Animal> targetAnimals = new ArrayList<>(animals);
        targetAnimals.sort((a1, a2) -> a2.weight() - a1.weight());
        int k = 2;
        targetAnimals = targetAnimals.subList(0, k);
        assertEquals(targetAnimals, Main.task2(animals, k));
    }

    @Test
    void task3() {
        Map<Animal.Type, Long> targetMap = new HashMap<>();
        for (Animal an : animals) {
            targetMap.put(an.type(), targetMap.getOrDefault(an.type(), 0L) + 1);
        }

        assertEquals(targetMap, Main.task3(animals));
    }

    @Test
    void task4() {
        Animal a8  = new Animal("BIRD12345678", BIRD, Animal.Sex.M, 5,23, 26,true);

        assertEquals(a8, Main.task4(animals));
    }

    @Test
    void task5() {
        Map<Animal.Sex, Long> sexCounterMap = new HashMap<>();
        for (Animal an : animals) {
            sexCounterMap.put(an.sex(), sexCounterMap.getOrDefault(an.sex(), 0L) + 1);
        }
        Animal.Sex target = sexCounterMap.get(Animal.Sex.M).compareTo(sexCounterMap.get(Animal.Sex.F)) > 0 ? Animal.Sex.M : Animal.Sex.F;
        assertEquals(target, Main.task5(animals));
    }

    @Test
    void task6() {
        Map<Animal.Type, Optional<Animal>> mostHeavyAnimal = new HashMap<>();
        for (var an : animals) {
            Optional<Animal> heavyAnimal = mostHeavyAnimal.get(an.type());
            if (heavyAnimal == null) {
                mostHeavyAnimal.put(an.type(), Optional.of(an));
                continue;
            }
            if (heavyAnimal.isPresent() && heavyAnimal.get().weight() < an.weight()) {
                mostHeavyAnimal.put(an.type(), Optional.of(an));
            }
        }

        assertEquals(mostHeavyAnimal, Main.task6(animals));
    }


    @Test
    void task7() {
        List<Animal> targetAnimals = new ArrayList<>(animals);
        targetAnimals.sort((a1, a2) -> a2.age() - a1.age());
        int k = 1;

        assertEquals(targetAnimals.get(k - 1), Main.task7(animals, k));
    }

    @Test
    void task8() {
        List<Animal> targetAnimals = new ArrayList<>(animals);
        Animal heavyLessKHeight = null;
        int k = 99;
        for (var an : animals) {
            if (an.height() < k) {
                if (heavyLessKHeight == null || heavyLessKHeight.weight() < an.weight()) {
                    heavyLessKHeight = an;
                }
            }
        }

        assertEquals(heavyLessKHeight, Main.task8(animals, k).orElse(null));
    }

    @Test
    void task9() {
        Long pawsSum = 0L;
        for (var an : animals) {
            pawsSum += an.paws();
        }
        assertEquals(pawsSum, Main.task9(animals));
    }

    @Test
    void task10() {
        List<Animal> ageNotEqPaws = new ArrayList<>();

        for (var an : animals) {
            if (an.age() != an.paws()) {
                ageNotEqPaws.add(an);
            }
        }
        assertEquals(ageNotEqPaws, Main.task10(animals));
    }

    @Test
    void task11() {
        List<Animal> ageNotEqPaws = new ArrayList<>();

        for (var an : animals) {
            if (an.height() > 100 && an.bites()) {
                ageNotEqPaws.add(an);
            }
        }
        assertEquals(ageNotEqPaws, Main.task11(animals));
    }

    @Test
    void task12() {
        int countWeightMoreHeight = 0;

        for (var an : animals) {
            if (an.weight() > an.height()) {
                countWeightMoreHeight++;
            }
        }
        assertEquals(countWeightMoreHeight, Main.task12(animals));
    }

    @Test
    void task13() {
        List<Animal> nameMoreTwoWords = new ArrayList<>();

        for (var an : animals) {
            if (an.name().split(" ").length > 2) {
                nameMoreTwoWords.add(an);
            }
        }
        assertEquals(nameMoreTwoWords, Main.task13(animals));
    }

    @Test
    void task14() {
        boolean isDogeHeightMoreK = false;
        int k = 100;
        for (var an : animals) {
            if (an.type().equals(DOG) && an.height() > k) {
                isDogeHeightMoreK = true;
                break;
            }
        }
        assertEquals(isDogeHeightMoreK, Main.task14(animals, k));
    }

    @Test
    void task15() {
        Map<Animal.Type, Integer> summingWeightMapByType = new HashMap<>();
        int k = 11;
        int l = 101;

        for (var an : animals) {
            if (k <= an.age() && an.age() <= l) {
                summingWeightMapByType.put(an.type(), summingWeightMapByType.getOrDefault(an.type(), 0) + an.weight());
            }
        }

        assertEquals(summingWeightMapByType, Main.task15(animals, k, l));
    }

    @Test
    void task16() {
        List<Animal> targetList = new ArrayList<>(animals);

        targetList.sort((a1, a2) -> {
            if (!a1.type().equals(a2.type())) {
                return a1.type().compareTo(a2.type());
            } else if (!a1.sex().equals(a2.sex())) {
                return a1.sex().compareTo(a2.sex());
            } else {
                return a1.name().compareTo(a2.name());
            }
        });

        assertEquals(targetList, Main.task16(animals));
    }

    @Test
    void task17() {
        int dogBites = 0;
        int spidersBites = 0;

        for (var an : animals) {
            if (an.type().equals(DOG) && an.bites()) {
                dogBites++;
            }
            if (an.type().equals(SPIDER) && an.bites()) {
                spidersBites++;
            }
        }
        boolean spiderMoreDogs = spidersBites > dogBites;
        assertEquals(spiderMoreDogs, Main.task17(animals));

    }

    @Test
    void task18() {
        List<Animal> animals1 = new ArrayList<>();
        animals1.add(new Animal("1", BIRD, Animal.Sex.M, 1,1, 3,false));
        animals1.add(new Animal("12", BIRD, Animal.Sex.M, 2,4, 5,false));
        animals1.add(new Animal("123", FISH, Animal.Sex.M, 3,3, 8,false));
        animals1.add(new Animal("1234", FISH, Animal.Sex.M, 0,2, 9,false));
        List<Animal> animals2 = new ArrayList<>();
        Animal heavyFish = new Animal("123", FISH, Animal.Sex.M, 3,3, 20,false);
        animals2.add(new Animal("1", BIRD, Animal.Sex.M, 1,1, 3,false));
        animals2.add(new Animal("12", BIRD, Animal.Sex.M, 2,4, 5,false));
        animals2.add(heavyFish);
        animals2.add(new Animal("1234", FISH, Animal.Sex.M, 0,2, 9,false));
        List<List<Animal>> list = new ArrayList<>(List.of(animals1, animals2));

       assertEquals(heavyFish, Main.task18(list));
    }

    @Test
    void task19() {
        List<Animal> errorData = new ArrayList<>();
        errorData.add(new Animal("1", null, null, 1,2, 3,false));
        errorData.add(new Animal("2", FISH, Animal.Sex.M, 0,-4, -9,false));

        Map<String, Set<ValidationError>> targetMap = new HashMap<>();
        targetMap.put("1", Set.of(
            new ValidationError("1", "type"),
            new ValidationError("1", "sex")));
        targetMap.put("2", Set.of(
            new ValidationError("2", "age"),
            new ValidationError("2", "height"),
            new ValidationError("2", "weight")));
        assertEquals(targetMap, Main.task19(errorData));

    }

    @Test
    void task20() {
        List<Animal> errorData = new ArrayList<>();
        errorData.add(new Animal("1", null, null, 1,2, 3,false));
        errorData.add(new Animal("2", FISH, Animal.Sex.M, 0,-4, -9,false));

        Map<String, String> targetMap = new HashMap<>();
        targetMap.put("1", "type sex");
        targetMap.put("2", "age height weight");
        assertEquals(targetMap, Main.task20(errorData));
    }
}
