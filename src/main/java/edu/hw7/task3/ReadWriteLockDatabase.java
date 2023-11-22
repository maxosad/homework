package edu.hw7.task3;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.jetbrains.annotations.Nullable;

public class ReadWriteLockDatabase implements PersonDatabase {

    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    private Map<String, Person> nameCollection;
    private Map<String, Person> addressCollection;
    private Map<String, Person> phoneCollection;
    private Map<Integer, Person> idCollection;

    public ReadWriteLockDatabase() {
        this.nameCollection = new HashMap<>();
        this.addressCollection = new HashMap<>();
        this.phoneCollection = new HashMap<>();
        this.idCollection = new HashMap<>();
    }

    @Override
    public void add(Person person) {
        lock.writeLock().tryLock();
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
        lock.writeLock().unlock();
    }

    @Override
    public void delete(int id) {
        lock.writeLock().tryLock();
        Person person = idCollection.remove(id);
        nameCollection.remove(person.name());
        addressCollection.remove(person.address());
        phoneCollection.remove(person.phoneNumber());
        lock.writeLock().unlock();
    }

    @Override
    public int size() {
        return idCollection.size();
    }

    @Override
    public @Nullable Person findByName(String name) {
        lock.readLock().tryLock();
        try {
            Person person = nameCollection.get(name);
            if (person.address() != null && person.phoneNumber() != null) {
                return person;
            }
            return null;
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public @Nullable Person findByAddress(String address) {
        lock.readLock().tryLock();
        try {
            Person person = addressCollection.get(address);
            if (person.name() != null && person.phoneNumber() != null) {
                return person;
            }
            return null;
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public @Nullable Person findByPhone(String phone) {
        lock.readLock().tryLock();
        try {
            Person person = phoneCollection.get(phone);
            if (person.name() != null && person.address() != null) {
                return person;
            }
            return null;
        } finally {
            lock.readLock().unlock();
        }
    }
}
