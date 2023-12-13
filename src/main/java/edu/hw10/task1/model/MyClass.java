package edu.hw10.task1.model;

public class MyClass {
    private int number;
    private String name = "default";
        public MyClass(int number, String name) {
        this.number = number;
        this.name = name;
    }

    public static MyClass getInstance(int number, String name) {
            return new MyClass(number, name);
    }
    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public void setName(String name) {
        this.name = name;
    }
}
