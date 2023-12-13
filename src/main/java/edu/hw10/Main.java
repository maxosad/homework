package edu.hw10;

import edu.hw10.task1.model.MyClass;
import edu.hw10.task1.RandomObjectGenerator;
import edu.hw10.task1.model.MyClassWithAnnotations;

public class Main {
    public static void main(String[] args) {
        //MyClass.class.getName() получить имя класса

        RandomObjectGenerator rog = new RandomObjectGenerator();

//        var myClass = rog.nextObject(MyClass.class);
        MyClassWithAnnotations myClassObject = rog.nextObject(MyClassWithAnnotations.class);

    }
}
