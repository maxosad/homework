package edu.hw10.task2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.nio.file.Path;

public class CacheInvocationHandler implements InvocationHandler {
    private final Object target;
    private final Path diskPath;
    public CacheInvocationHandler(Object target, Path diskPath) {
        this.target = target;
        this.diskPath = diskPath;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        method.setAccessible(true);

        if (method.isAnnotationPresent(Cache.class)) {
            if (method.getAnnotation(Cache.class).persist()) {
                return saveOnDisk(method, args);
            }
            return saveInMemory(method, args);
        }
        return method.invoke(proxiedObject, args);
    }

    private Object saveInMemory(Method method, Object[] args) {

    }
}
