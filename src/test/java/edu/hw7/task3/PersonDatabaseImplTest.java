package edu.hw7.task3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonDatabaseImplTest {
    public static PersonDatabase personDatabase;
    @BeforeEach
    void setUp() {
        personDatabase = new PersonDatabaseImpl();
    }
    @Test
    void add() {
        Thread thread1 = new Thread(() -> {
            personDatabase.add(new Person(16,"we","12", "+72092"));
            personDatabase.add(new Person(17,"we","12", "+72092"));
            personDatabase.add(new Person(18,"we","12", "+72092"));
            personDatabase.add(new Person(19,"we","12", "+72092"));
            personDatabase.add(new Person(20,"we","12", "+72092"));
            personDatabase.add(new Person(21,"we","12", "+72092"));
            personDatabase.add(new Person(22,"we","12", "+72092"));
            personDatabase.add(new Person(23,"we","12", "+72092"));
            personDatabase.add(new Person(24,"we","12", "+72092"));
            personDatabase.add(new Person(25,"we","12", "+72092"));
            personDatabase.add(new Person(26,"we","12", "+72092"));
            personDatabase.add(new Person(27,"we","12", "+72092"));
            personDatabase.add(new Person(28,"we","12", "+72092"));
            personDatabase.add(new Person(29,"we","12", "+72092"));
            personDatabase.add(new Person(30,"we","12", "+72092"));
            personDatabase.add(new Person(31,"we","12", "+72092"));
        });
        Thread thread2 = new Thread(() -> {
            personDatabase.add(new Person(0,"we","12", "+72092"));
            personDatabase.add(new Person(1,"we","12", "+72092"));
            personDatabase.add(new Person(2,"we","12", "+72092"));
            personDatabase.add(new Person(3,"we","12", "+72092"));
            personDatabase.add(new Person(4,"we","12", "+72092"));
            personDatabase.add(new Person(5,"we","12", "+72092"));
            personDatabase.add(new Person(6,"we","12", "+72092"));
            personDatabase.add(new Person(7,"we","12", "+72092"));
            personDatabase.add(new Person(8,"we","12", "+72092"));
            personDatabase.add(new Person(9,"we","12", "+72092"));
            personDatabase.add(new Person(10,"we","12", "+72092"));
            personDatabase.add(new Person(11,"we","12", "+72092"));
            personDatabase.add(new Person(12,"we","12", "+72092"));
            personDatabase.add(new Person(13,"we","12", "+72092"));
            personDatabase.add(new Person(14,"we","12", "+72092"));
            personDatabase.add(new Person(15,"we","12", "+72092"));
        });

        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            fail("Should not be exception");
        }
        var res = personDatabase.size();

        assertEquals(32, res);
    }

    @Test
    @DisplayName("should find by name")
    void findByName() {
        Person personToFind = new Person(0,"we1","123", "+720921");
        personDatabase.add(personToFind);
        personDatabase.add(new Person(1,null,"124", "+720922"));
        personDatabase.add(new Person(2,"we2",null, "+720923"));
        personDatabase.add(new Person(3,"we3","125", null));

        var res = personDatabase.findByName("we1");

        assertEquals(personToFind, res);
    }

    @Test
    @DisplayName("should not find by name")
    void notFindByName() {
        personDatabase.add(new Person(2,"we2",null, "+720923"));
        personDatabase.add(new Person(3,"we3","125", null));

        var res1 = personDatabase.findByName("we2");
        var res2 = personDatabase.findByName("we3");

        assertNull(res2);
        assertNull(res1);
    }


}
