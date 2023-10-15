package edu.hw2.task2;

public class Square extends Rectangle {

    public Square(int edge) {
        super(edge, edge);
    }
    @Override
    public Square setWidth(int width) {
       return new Square(width);
    }

    @Override
    public Square setHeight(int height) {
        return new Square(height);
    }
}
