package edu.hw10.task1;

import edu.hw10.task1.generators.Generator;
import edu.hw10.task1.generators.IntGenerator;
import edu.hw10.task1.generators.StringGenerator;
import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RandomObjectGenerator {

    private static final Map<Class, Generator> GENERATOR_MAP = Map.of(
        String.class, new StringGenerator(),
        int.class, new IntGenerator(),
        Integer.class, new IntGenerator()
    );

    private static final List<Class> ALLOWED_TYPES = List.of(
        String.class,
        int.class,
        Integer.class
    );

    public <T> T nextObject(Class<T> clazz) {
        try {
            Constructor constructor = clazz.getConstructors()[0];

            Class[] paramTypes = constructor.getParameterTypes();
            ArrayList<Object> params = createParams(constructor);
            return clazz.getConstructor(paramTypes).newInstance(params.toArray());
        } catch (InstantiationException | IllegalAccessException
                 | NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T nextObject(Class<T> clazz, String initMethodName) {
        try {
            Method[] mm = clazz.getDeclaredMethods();
            Method method = null;
            for (Method m : mm) {
                if (m.getName().equals(initMethodName)) {
                    method = m;
                }
            }
            ArrayList<Object> params = createParams(method);
            return (T) method.invoke(null, params.toArray());
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private ArrayList<Object> createParams(Executable constructorOrMethod) {
        var parameterAnnotations = constructorOrMethod.getParameterAnnotations();
        constructorOrMethod.setAccessible(true);
        Class[] paramTypes = constructorOrMethod.getParameterTypes();
        int paramSize = paramTypes.length;
        ArrayList<Object> params = new ArrayList<>(paramSize);

        for (int paramIndex = 0; paramIndex < paramSize; paramIndex++) {
            Class type = paramTypes[paramIndex];
            if (!ALLOWED_TYPES.contains(type)) {
                throw new RuntimeException("Unsupported type parameter");
            }
            if (parameterAnnotations[paramIndex].length != 0) {
                params.add(GENERATOR_MAP.get(type).generate(parameterAnnotations[paramIndex]));
            } else {
                params.add(GENERATOR_MAP.get(type).generate());
            }
        }
        return params;
    }

}
