package edu.hw7.task3;

import org.jetbrains.annotations.Nullable;

public interface PersonDatabase {

    void add(Person person);

    void delete(int id);

    int size();

    @Nullable Person findByName(String name);

    @Nullable Person findByAddress(String address);

    @Nullable Person findByPhone(String phone);

}
