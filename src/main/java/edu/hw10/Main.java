package edu.hw10;

import edu.hw10.task1.model.MyClass;
import edu.hw10.task1.RandomObjectGenerator;

public class Main {
    public static void main(String[] args) {
        //MyClass.class.getName() получить имя класса

        RandomObjectGenerator rog = new RandomObjectGenerator();

//        var myClass = rog.nextObject(MyClass.class, "create");
        MyClass myClassObject = rog.nextObject(MyClass.class);
        System.out.println(myClassObject.getNumber());
        System.out.println(myClassObject.getName());

//        MyClassRecord myClassRecordObject = rog.nextObject(MyClassRecord.class);
//        System.out.println(myClassRecordObject.number());
//        System.out.println(myClassRecordObject.name());
    }
}
