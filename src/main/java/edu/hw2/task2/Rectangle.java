package edu.hw2.task2;

public class Rectangle {
    private final int width;
    private final int height;

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Rectangle setWidth(int width) {
        return new Rectangle(width, height);
    }

    public Rectangle setHeight(int height) {
        return new Rectangle(width, height);
    }

    double area() {
        return width * height;
    }
}
