package edu.hw10.task2;

import java.util.Arrays;

public class CacheProxy {
    public static <T> T create(T t, Class<? extends T> c) {
        var methods = c.getDeclaredMethods();
        int methodsSize = methods.length;
        for (int methodIndex = 0; methodIndex < methodsSize; methodIndex++) {
            if (methods[methodIndex].isAnnotationPresent(Cache.class)) {

                System.out.println("sdfads");
            }
        }
        System.out.println(Arrays.toString(c.getDeclaredMethods()[0].getDeclaredAnnotations()));
        return null;
    }
}
