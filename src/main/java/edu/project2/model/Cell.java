package edu.project2.model;

import java.util.Objects;

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

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return row == cell.row && col == cell.col && type == cell.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col, type);
    }
}
