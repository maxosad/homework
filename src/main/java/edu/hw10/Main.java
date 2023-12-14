package edu.hw10;

import edu.hw10.task1.model.MyClass;
import edu.hw10.task1.RandomObjectGenerator;
import edu.hw10.task1.model.MyClassWithAnnotations;
import edu.hw10.task2.CacheProxy;
import edu.hw10.task2.FibCalculator;
import edu.hw10.task2.FibImpl;

public class Main {
    public static void main(String[] args) {
        FibCalculator c = new FibImpl();
//        FibCalculator proxy = CacheProxy.create(c, c.getClass());
        c.fib(3);

    }
}
