package edu.hw7.task3;

import java.util.HashMap;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

public class PersonDatabaseImpl implements PersonDatabase {
    private Map<String, Person> personsByName;
    private Map<String, Person> personByAddress;
    private Map<String, Person> personByPhone;
    private Map<Integer, Person> personById;

    public PersonDatabaseImpl() {
        this.personsByName = new HashMap<>();
        this.personByAddress = new HashMap<>();
        this.personByPhone = new HashMap<>();
        this.personById = new HashMap<>();
    }

    public int size() {
        return personById.size();
    }

    @Override
    public synchronized void add(Person person) {
        if (person.name() != null) {
            personsByName.put(person.name(), person);
        }
        if (person.address() != null) {
            personByAddress.put(person.address(), person);
        }
        if (person.phoneNumber() != null) {
            personByPhone.put(person.phoneNumber(), person);
        }
        personById.put(person.id(), person);
    }

    @Override
    public synchronized void delete(int id) {
        Person person = personById.remove(id);
        personsByName.remove(person.name());
        personByAddress.remove(person.address());
        personByPhone.remove(person.phoneNumber());
    }

    @Override
    @Nullable
    public synchronized  Person findByName(String name) {
        Person person = personsByName.get(name);
        if (person.address() != null && person.phoneNumber() != null) {
            return person;
        }
        return null;
    }

    @Override
    @Nullable
    public synchronized  Person findByAddress(String address) {
        Person person = personByAddress.get(address);
        if (person.name() != null && person.phoneNumber() != null) {
            return person;
        }
        return null;
    }

    @Override
    @Nullable
    public synchronized  Person findByPhone(String phone) {
        Person person = personByPhone.get(phone);
        if (person.name() != null && person.address() != null) {
            return person;
        }
        return null;
    }
}
