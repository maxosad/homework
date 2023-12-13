package edu.hw10.task1.generators;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import java.lang.annotation.Annotation;
import java.security.SecureRandom;

public class IntGenerator extends AbstractGenerator<Integer> {
    @Override
    public Integer generate() {
        return random.nextInt();
    }

    public Integer generate(Annotation[] annotations) {
        int maxVal = Integer.MAX_VALUE;
        int minVal = Integer.MIN_VALUE;
        for (var annotation : annotations) {

            if (annotation.annotationType().equals(Min.class)) {
                minVal = (int) ((Min) annotation).value();
            }
            if (annotation.annotationType().equals(Max.class)) {
                maxVal = (int) ((Max) annotation).value();
            }
        }
        return random.nextInt(minVal, maxVal);
    }
}
