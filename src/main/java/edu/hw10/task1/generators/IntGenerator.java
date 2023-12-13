package edu.hw10.task1.generators;

import java.security.SecureRandom;

public class IntGenerator extends AbstractGenerator<Integer> {
    @Override
    public Integer generate() {
        return random.nextInt();
    }
}
