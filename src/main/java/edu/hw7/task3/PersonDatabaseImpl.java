package edu.hw7.task3;

import org.jetbrains.annotations.Nullable;
import java.util.HashMap;
import java.util.Map;

public class PersonDatabaseImpl implements PersonDatabase {
    private Map<String, Person> nameCollection;
    private Map<String, Person> addressCollection;
    private Map<String, Person> phoneCollection;
    private Map<Integer, Person> idCollection;

    public PersonDatabaseImpl() {
        this.nameCollection = new HashMap<>();
        this.addressCollection = new HashMap<>();
        this.phoneCollection = new HashMap<>();
        this.idCollection = new HashMap<>();
    }

    public int size() {
        return idCollection.size();
    }

    @Override
    public synchronized void add(Person person) {
        if (person.name() != null) {
            nameCollection.put(person.name(), person);
        }
        if (person.address() != null) {
            addressCollection.put(person.address(), person);
        }
        if (person.phoneNumber() != null) {
            phoneCollection.put(person.phoneNumber(), person);
        }
        idCollection.put(person.id(), person);

    }

    @Override
    public synchronized void delete(int id) {
        Person person = idCollection.remove(id);
        nameCollection.remove(person.name());
        addressCollection.remove(person.address());
        phoneCollection.remove(person.phoneNumber());
    }

    public @Nullable Person findByName(String name) {
        Person person = nameCollection.get(name);
        if (addressCollection.get(person.address()) != null
            && phoneCollection.get(person.phoneNumber()) != null) {
            return person;
        }
        return null;
    }

    @Override
    public @Nullable Person findByAddress(String address) {
        Person person = addressCollection.get(address);
        if (nameCollection.get(person.name()) != null
            && phoneCollection.get(person.phoneNumber()) != null) {
            return person;
        }
        return null;
    }

    @Override
    public @Nullable Person findByPhone(String phone) {
        Person person = phoneCollection.get(phone);
        if (nameCollection.get(person.name()) != null
            && addressCollection.get(person.address()) != null) {
            return person;
        }
        return null;
    }

}
