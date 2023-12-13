package edu.hw10.task1.generators;

import java.lang.annotation.Annotation;
import java.security.SecureRandom;

public class AbstractGenerator<T> implements Generator<T> {

    protected final SecureRandom random;
    public AbstractGenerator() {
        random = new SecureRandom();
    }

    @Override
    public T generate() {
        return null;
    }

    @Override
    public T generate(Annotation[] annotations) {
        return null;
    }
}
