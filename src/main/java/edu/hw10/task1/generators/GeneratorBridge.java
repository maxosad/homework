package edu.hw10.task1.generators;

import java.util.HashMap;
import java.util.Map;

public class GeneratorBridge {
    private static final Map<Class, Generator> generatorMap;

    static {
        generatorMap = new HashMap<>();
        generatorMap.put(String.class, new StringGenerator());
        generatorMap.put(int.class, new IntGenerator());
        generatorMap.put(Integer.class, new IntGenerator());
    }

    public static <T> T generate(Class<T> t) {
        return (T) generatorMap.get(t).generate();
    }
}
