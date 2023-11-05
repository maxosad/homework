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
import static edu.hw4.Type.*;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    private static List<Animal> animals = null;
    @BeforeAll
    static void setUp() {
        Animal a1  = new Animal("BIRD 1", BIRD, Sex.M, 9,1, 3,false);
        Animal a2  = new Animal("BIRD12", BIRD, Sex.M, 1,4, 5,true);
        Animal a3  = new Animal("BIRD123", BIRD, Sex.F, 12,3, 8,true);
        Animal a4  = new Animal("BIRD1234", BIRD, Sex.M, 64,100, 45,false);
        Animal a5  = new Animal("BIRD12345", BIRD, Sex.F, 7,123, 243,false);
        Animal a6  = new Animal("BIRD123456", BIRD, Sex.M, 1,2, 72,true);
        Animal a7  = new Animal("BIRD1234567", BIRD, Sex.F, 2,2, 64,false);
        Animal a8  = new Animal("BIRD12345678", BIRD, Sex.M, 5,23, 26,true);
        Animal a9  = new Animal("CAT1", CAT, Sex.F, 3,2, 14,false);
        Animal a10 = new Animal("CAT12", CAT, Sex.F, 12,200, 9,true);
        Animal a11 = new Animal("CAT123", CAT, Sex.F, 6,2, 63,true);
        Animal a12 = new Animal("CAT1234", CAT, Sex.M, 7,5, 9,false);
        Animal a13 = new Animal("CAT12345", CAT, Sex.M, 4,2, 9,false);
        Animal a14 = new Animal("CAT123456", CAT, Sex.F, 6,253, 31,false);
        Animal a15 = new Animal("CAT1234567", CAT, Sex.M, 8,56, 9,false);
        Animal a16 = new Animal("CAT12345678", CAT, Sex.M, 3,29, 9,false);
        Animal a17 = new Animal("D OG 1", DOG, Sex.F, 9,35, 9,false);
        Animal a18 = new Animal("DOG12", DOG, Sex.M, 10,75, 1,true);
        Animal a19 = new Animal("DOG123", DOG, Sex.M, 12,888, 1,false);
        Animal a20 = new Animal("DOG1234", DOG, Sex.M, 1,456, 1,true);
        Animal a21 = new Animal("DOG12345", DOG, Sex.F, 23,147, 57,true);
        Animal a22 = new Animal("DOG123456", DOG, Sex.M, 5,390, 9,false);
        Animal a23 = new Animal("DOG1234567", DOG, Sex.M, 19,347, 9,true);
        Animal a24 = new Animal("DOG12345678", DOG, Sex.M, 4,245, 9,false);
        Animal a25 = new Animal("FISH 1", FISH, Sex.F, 8,643, 43,false);
        Animal a26 = new Animal("FISH 12", FISH, Sex.F, 51,731, 9,true);
        Animal a27 = new Animal("FISH123", FISH, Sex.M, 81,537, 9,false);
        Animal a28 = new Animal("SPIDER1", SPIDER, Sex.M, 3,326, 14,true);
        Animal a29 = new Animal("SPIDER12", SPIDER, Sex.M, 9,34, 9,false);
        Animal a30 = new Animal("SPIDER123", SPIDER, Sex.F, 7,6, 9,false);
        Animal a31 = new Animal("SPIDER1234", SPIDER, Sex.M, 8,49, 9,false);
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
    void shortToTallSort() {
        List<Animal> targetAnimals = new ArrayList<>(animals);
        targetAnimals.sort(Comparator.comparingInt(Animal::height));

        var result = Main.task1(animals);

        assertEquals(targetAnimals, result);
    }

    @Test
    void HeavyToLightSortFirstK() {
        int k = 2;
        List<Animal> targetAnimals = new ArrayList<>(animals);
        targetAnimals.sort((a1, a2) -> a2.weight() - a1.weight());
        targetAnimals = targetAnimals.subList(0, k);

        var result = Main.task2(animals, k);

        assertEquals(targetAnimals, result);
    }

    @Test
    void EachTypeCount() {
        Map<Type, Long> targetMap = new HashMap<>();
        for (Animal an : animals) {
            targetMap.put(an.type(), targetMap.getOrDefault(an.type(), 0L) + 1);
        }

        var result = Main.task3(animals);

        assertEquals(targetMap, result);
    }

    @Test
    void animalWithLongestName() {
        Animal a8  = new Animal("BIRD12345678", BIRD, Sex.M, 5,23, 26,true);

        var result = Main.task4(animals);

        assertEquals(a8, result);
    }

    @Test
    void  moreMalesOrFemales() {
        Map<Sex, Long> sexCounterMap = new HashMap<>();
        for (Animal an : animals) {
            sexCounterMap.put(an.sex(), sexCounterMap.getOrDefault(an.sex(), 0L) + 1);
        }
        Sex target = sexCounterMap.get(Sex.M).compareTo(sexCounterMap.get(Sex.F)) > 0 ? Sex.M : Sex.F;

        var result = Main.task5(animals);

        assertEquals(target, result);
    }

    @Test
    void heaviestEachType() {
        Map<Type, Optional<Animal>> mostHeavyAnimal = new HashMap<>();
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

        var result = Main.task6(animals);

        assertEquals(mostHeavyAnimal, result);
    }


    @Test
    void kthOldest() {
        int k = 1;
        List<Animal> targetAnimals = new ArrayList<>(animals);
        targetAnimals.sort((a1, a2) -> a2.age() - a1.age());

        var result = Main.task7(animals, k);

        assertEquals(targetAnimals.get(k - 1), result);
    }

    @Test
    void heaviestShorterThanK() {
        int k = 99;
        Animal heavyLessKHeight = null;
        for (var an : animals) {
            if (an.height() < k) {
                if (heavyLessKHeight == null || heavyLessKHeight.weight() < an.weight()) {
                    heavyLessKHeight = an;
                }
            }
        }

        var result = Main.task8(animals, k).orElse(null);

        assertEquals(heavyLessKHeight, result);
    }

    @Test
    void pawsSum() {
        Long pawsSum = 0L;
        for (var an : animals) {
            pawsSum += an.paws();
        }

        var result = Main.task9(animals);

        assertEquals(pawsSum, result);
    }

    @Test
    void ageNotEqualPaws() {
        List<Animal> ageNotEqPaws = new ArrayList<>();
        for (var an : animals) {
            if (an.age() != an.paws()) {
                ageNotEqPaws.add(an);
            }
        }

        var result = Main.task10(animals);

        assertEquals(ageNotEqPaws, result);
    }

    @Test
    void higher100CanBite() {
        List<Animal> ageNotEqPaws = new ArrayList<>();
        for (var an : animals) {
            if (an.height() > 100 && an.bites()) {
                ageNotEqPaws.add(an);
            }
        }
        var result = Main.task11(animals);

        assertEquals(ageNotEqPaws, result);
    }

    @Test
    void weightMoreHeight() {
        int countWeightMoreHeight = 0;
        for (var an : animals) {
            if (an.weight() > an.height()) {
                countWeightMoreHeight++;
            }
        }

        var result = Main.task12(animals);

        assertEquals(countWeightMoreHeight, result);
    }

    @Test
    void namesContainsMoreTwoWords() {
        List<Animal> nameMoreTwoWords = new ArrayList<>();
        for (var an : animals) {
            if (an.name().split(" ").length > 2) {
                nameMoreTwoWords.add(an);
            }
        }

        var result = Main.task13(animals);

        assertEquals(nameMoreTwoWords, result);
    }

    @Test
    void isDogHigherK() {
        boolean isDogeHeightMoreK = false;
        int k = 100;
        for (var an : animals) {
            if (an.type().equals(DOG) && an.height() > k) {
                isDogeHeightMoreK = true;
                break;
            }
        }

        var result = Main.task14(animals, k);

        assertEquals(isDogeHeightMoreK, result);
    }

    @Test
    void eachTypeWeight() {
        Map<Type, Integer> summingWeightMapByType = new HashMap<>();
        int k = 11;
        int l = 101;
        for (var an : animals) {
            if (k <= an.age() && an.age() <= l) {
                summingWeightMapByType.put(an.type(), summingWeightMapByType.getOrDefault(an.type(), 0) + an.weight());
            }
        }

        var result = Main.task15(animals, k, l);

        assertEquals(summingWeightMapByType, result);
    }

    @Test
    void typeSexNameSort() {
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

        var result = Main.task16(animals);

        assertEquals(targetList, result);
    }

    @Test
    void eachTypeWeightWhichFromKtoLAge() {
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

        var result = Main.task17(animals);

        assertEquals(spiderMoreDogs, result);
    }

    @Test
    void heaviestFishMoreTwoLists() {
        List<Animal> animals1 = new ArrayList<>();
        animals1.add(new Animal("1", BIRD, Sex.M, 1,1, 3,false));
        animals1.add(new Animal("12", BIRD, Sex.M, 2,4, 5,false));
        animals1.add(new Animal("123", FISH, Sex.M, 3,3, 8,false));
        animals1.add(new Animal("1234", FISH, Sex.M, 0,2, 9,false));
        List<Animal> animals2 = new ArrayList<>();
        Animal heavyFish = new Animal("123", FISH, Sex.M, 3,3, 20,false);
        animals2.add(new Animal("1", BIRD, Sex.M, 1,1, 3,false));
        animals2.add(new Animal("12", BIRD, Sex.M, 2,4, 5,false));
        animals2.add(heavyFish);
        animals2.add(new Animal("1234", FISH, Sex.M, 0,2, 9,false));
        List<List<Animal>> list = new ArrayList<>(List.of(animals1, animals2));

        var result = Main.task18(list);

        assertEquals(heavyFish, result);
    }

    @Test
    void animalsWithErrors() {
        List<Animal> errorData = new ArrayList<>();
        errorData.add(new Animal("1", null, null, 1,2, 3,false));
        errorData.add(new Animal("2", FISH, Sex.M, 0,-4, -9,false));
        Map<String, Set<ValidationError>> targetMap = new HashMap<>();
        targetMap.put("1", Set.of(
            new ValidationError("1", "type"),
            new ValidationError("1", "sex")));
        targetMap.put("2", Set.of(
            new ValidationError("2", "age"),
            new ValidationError("2", "height"),
            new ValidationError("2", "weight")));

        var result = Main.task19(errorData);

        assertEquals(targetMap, result);

    }

    @Test
    void animalsWithErrorsShouldReturnString() {
        List<Animal> errorData = new ArrayList<>();
        errorData.add(new Animal("1", null, null, 1,2, 3,false));
        errorData.add(new Animal("2", FISH, Sex.M, 0,-4, -9,false));
        Map<String, String> targetMap = new HashMap<>();
        targetMap.put("1", "type sex");
        targetMap.put("2", "age height weight");

        var result = Main.task20(errorData);

        assertEquals(targetMap, result);
    }
}
