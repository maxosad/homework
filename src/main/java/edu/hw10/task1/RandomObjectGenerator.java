package edu.hw10.task1;

import edu.hw10.task1.generators.GeneratorBridge;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class RandomObjectGenerator {
    //    public Object nextObject(Class<?> clazz, String methodName)
    public <T> T nextObject(Class<T> clazz) {
        try {
            Constructor constructor = clazz.getConstructors()[0];
            constructor.setAccessible(true);
            Class[] paramTypes = constructor.getParameterTypes();
            int paramSize = paramTypes.length;
            ArrayList<Object> params = new ArrayList<>(paramSize);

            for (int paramIndex = 0; paramIndex < paramSize; paramIndex++) {
                params.add(GeneratorBridge.generate(paramTypes[paramIndex]));
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
            method.setAccessible(true);
            Class[] paramTypes = method.getParameterTypes();
            int paramSize = paramTypes.length;
            ArrayList<Object> params = new ArrayList<>(paramSize);

            for (int paramIndex = 0; paramIndex < paramSize; paramIndex++) {
                params.add(GeneratorBridge.generate(paramTypes[paramIndex]));
            }
            return (T) method.invoke(null, params.toArray());
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);

        }
    }
}
