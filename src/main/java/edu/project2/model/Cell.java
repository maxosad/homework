package edu.project2.model;

public class Cell {
    public enum Type { WALL, PASSAGE }

    private final int row;
    private final int col;
    private Type type;

    public Cell(int row, int col, Type type) {
        this.row = row;
        this.col = col;
        this.type = type;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String toString() {
        return type.equals(Type.WALL) ? "w" : "p";
    }
}
