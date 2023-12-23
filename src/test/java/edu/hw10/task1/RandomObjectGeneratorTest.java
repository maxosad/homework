package edu.hw10.task1;

import edu.hw10.task1.model.MyClass;
import edu.hw10.task1.model.MyClassRecord;
import edu.hw10.task1.model.MyClassWithAnnotations;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RandomObjectGeneratorTest {
    private RandomObjectGenerator rog;

    @BeforeEach
    void setUp() {
        rog = new RandomObjectGenerator();
    }

    @Test
    @DisplayName("creation through constructor")
    void nextObject() {
        MyClass myClassObject = null;
        MyClassRecord myClassRecordObject = null;
        MyClassWithAnnotations myClassWithAnnotations = null;

        assertNull(myClassObject);
        assertNull(myClassRecordObject);
        assertNull(myClassWithAnnotations);

        MyClass finalMyClassObject = myClassObject;
        MyClassRecord finalMyClassRecordObject = myClassRecordObject;
        MyClassWithAnnotations finalMyClassWithAnnotations = myClassWithAnnotations;

        assertThrows(NullPointerException.class, () -> finalMyClassObject.getNumber());
        assertThrows(NullPointerException.class, () -> finalMyClassObject.getName());
        assertThrows(NullPointerException.class, () -> finalMyClassRecordObject.number());
        assertThrows(NullPointerException.class, () -> finalMyClassRecordObject.name());
        assertThrows(NullPointerException.class, () -> finalMyClassWithAnnotations.getMinNumber());
        assertThrows(NullPointerException.class, () -> finalMyClassWithAnnotations.getMaxNumber());

        myClassObject = rog.nextObject(MyClass.class);
        myClassRecordObject = rog.nextObject(MyClassRecord.class);
        myClassWithAnnotations = rog.nextObject(MyClassWithAnnotations.class);

        assertNotNull(myClassObject);
        assertNotNull(myClassRecordObject);
        assertNotNull(myClassWithAnnotations);
        assertNotNull(myClassObject.getNumber());
        assertNotNull(myClassObject.getName());
        assertNotNull(myClassRecordObject.number());
        assertNotNull(myClassRecordObject.name());
        assertTrue(10 <= myClassWithAnnotations.getMinNumber() && myClassWithAnnotations.getMinNumber() <= 11);
        assertEquals(20, myClassWithAnnotations.getMaxNumber());

    }

    @Test
    @DisplayName("creation through method")
    void testNextObject() {
        MyClass myClassObject = null;
        MyClassWithAnnotations myClassWithAnnotations = null;

        assertNull(myClassObject);
        assertNull(myClassWithAnnotations);

        MyClass finalMyClassObject = myClassObject;
        MyClassWithAnnotations finalMyClassWithAnnotations = myClassWithAnnotations;

        assertThrows(NullPointerException.class, () -> finalMyClassObject.getNumber());
        assertThrows(NullPointerException.class, () -> finalMyClassObject.getName());
        assertThrows(NullPointerException.class, () -> finalMyClassWithAnnotations.getMinNumber());
        assertThrows(NullPointerException.class, () -> finalMyClassWithAnnotations.getMaxNumber());

        myClassObject = rog.nextObject(MyClass.class, "getInstance");
        myClassWithAnnotations = rog.nextObject(MyClassWithAnnotations.class, "getInstance");

        assertNotNull(myClassObject);
        assertNotNull(myClassWithAnnotations);
        assertNotNull(myClassObject.getNumber());
        assertNotNull(myClassObject.getName());
        assertTrue(10 <= myClassWithAnnotations.getMinNumber() && myClassWithAnnotations.getMinNumber() <= 11);
        assertEquals(20, myClassWithAnnotations.getMaxNumber());
    }
}
