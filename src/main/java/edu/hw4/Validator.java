package edu.hw4;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Validator {

    private Validator() { }

    private static boolean validateName(String name) {
        return name == null;
    }

    private static boolean validateType(Type type) {
        return type == null;
    }

    private static boolean validateSex(Sex sex) {
        return sex == null;
    }

    private static boolean validateAge(int age) {
        return age <= 0;
    }

    private static boolean validateHeight(int height) {
        return height <= 0;
    }

    private static boolean validateWeight(int weight) {
        return weight <= 0;
    }

    private static boolean validateBites(boolean bites) {
        return false;
    }

    public static Stream<ValidationError> validate(Animal animal) {
        boolean nameError = validateName(animal.name());
        boolean typeError = validateType(animal.type());
        boolean sexError = validateSex(animal.sex());
        boolean ageError = validateAge(animal.age());
        boolean heightError = validateHeight(animal.height());
        boolean weightError = validateWeight(animal.weight());
        boolean bitesError = validateBites(animal.bites());

        List<ValidationError> errors = new ArrayList<>();
        if (nameError) {
            errors.add(new ValidationError(animal.name(), "name"));
        }
        if (typeError) {
            errors.add(new ValidationError(animal.name(), "type"));
        }
        if (sexError) {
            errors.add(new ValidationError(animal.name(), "sex"));
        }
        if (ageError) {
            errors.add(new ValidationError(animal.name(), "age"));
        }
        if (heightError) {
            errors.add(new ValidationError(animal.name(), "height"));
        }
        if (weightError) {
            errors.add(new ValidationError(animal.name(), "weight"));
        }
        if (bitesError) {
            errors.add(new ValidationError(animal.name(), "bites"));
        }
        return errors.stream();
    }
}
