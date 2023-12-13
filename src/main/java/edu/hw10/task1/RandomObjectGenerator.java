package edu.hw10.task1;

import edu.hw10.task1.generators.Generator;
import edu.hw10.task1.generators.GeneratorBridge;
import edu.hw10.task1.generators.IntGenerator;
import edu.hw10.task1.generators.StringGenerator;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class RandomObjectGenerator {


    private static final Map<Class, Generator> generatorMap;

    static {
        generatorMap = new HashMap<>();
        generatorMap.put(String.class, new StringGenerator());
        generatorMap.put(int.class, new IntGenerator());
        generatorMap.put(Integer.class, new IntGenerator());
    }

    public <T> T nextObject(Class<T> clazz) {
        try {
            Constructor constructor = clazz.getConstructors()[0];
            var parameterAnnotations = constructor.getParameterAnnotations();
            constructor.setAccessible(true);
            Class[] paramTypes = constructor.getParameterTypes();
            int paramSize = paramTypes.length;
            ArrayList<Object> params = new ArrayList<>(paramSize);

            for (int paramIndex = 0; paramIndex < paramSize; paramIndex++) {
                if (parameterAnnotations[paramIndex].length != 0) {
                    params.add(generatorMap.get(paramTypes[paramIndex]).generate(parameterAnnotations[paramIndex]));
                } else {
                    params.add(generatorMap.get(paramTypes[paramIndex]).generate());
                }
            }
            return clazz.getConstructor(paramTypes).newInstance(params.toArray());
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException(e);

        }
    }

    public <T> T nextObject(Class<T> clazz, String initMethodName) {
        try {
            Method[] mm = clazz.getDeclaredMethods();
            Method method = null;
            for (Method m:
                 mm) {
                if (m.getName().equals(initMethodName)) {
                    method = m;
                }
            }
            var parameterAnnotations = method.getParameterAnnotations();
            method.setAccessible(true);
            Class[] paramTypes = method.getParameterTypes();
            int paramSize = paramTypes.length;
            ArrayList<Object> params = new ArrayList<>(paramSize);

            for (int paramIndex = 0; paramIndex < paramSize; paramIndex++) {
                if (parameterAnnotations[paramIndex].length != 0) {
                    params.add(generatorMap.get(paramTypes[paramIndex]).generate(parameterAnnotations[paramIndex]));
                } else {
                    params.add(generatorMap.get(paramTypes[paramIndex]).generate());
                }
            }
            return (T) method.invoke(null, params.toArray());
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);

        }
    }
}
