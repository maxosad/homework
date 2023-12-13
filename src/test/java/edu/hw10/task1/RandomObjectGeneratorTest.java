package edu.hw10.task1;

import edu.hw10.task1.model.MyClass;
import edu.hw10.task1.model.MyClassRecord;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RandomObjectGeneratorTest {
    private RandomObjectGenerator rog;
    @BeforeEach
    void setUp() {
        rog = new RandomObjectGenerator();
    }

    @Test
    void nextObject() {
        MyClass myClassObject = null;
        MyClassRecord myClassRecordObject = null;

        assertNull(myClassObject);
        assertNull(myClassRecordObject);
        MyClass finalMyClassObject = myClassObject;
        MyClassRecord finalMyClassRecordObject = myClassRecordObject;
        assertThrows(NullPointerException.class, () -> finalMyClassObject.getNumber());
        assertThrows(NullPointerException.class, () -> finalMyClassObject.getName());
        assertThrows(NullPointerException.class, () -> finalMyClassRecordObject.number());
        assertThrows(NullPointerException.class, () -> finalMyClassRecordObject.name());

        myClassObject = rog.nextObject(MyClass.class);
        myClassRecordObject = rog.nextObject(MyClassRecord.class);

        assertNotNull(myClassObject);
        assertNotNull(myClassRecordObject);
        assertNotNull( myClassObject.getNumber());
        assertNotNull(myClassObject.getName());
        assertNotNull(myClassRecordObject.number());
        assertNotNull(myClassRecordObject.name());
    }

    @Test
    void testNextObject() {
        MyClass myClassObject = null;

        assertNull(myClassObject);
        MyClass finalMyClassObject = myClassObject;
        assertThrows(NullPointerException.class, () -> finalMyClassObject.getNumber());
        assertThrows(NullPointerException.class, () -> finalMyClassObject.getName());

        myClassObject = rog.nextObject(MyClass.class, "getInstance");

        assertNotNull(myClassObject);
        assertNotNull( myClassObject.getNumber());
        assertNotNull(myClassObject.getName());
    }
}
