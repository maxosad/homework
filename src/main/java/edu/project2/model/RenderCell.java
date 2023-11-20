package edu.project2.model;

public class RenderCell {
    public enum Type { WALL, PASSAGE, PATH }

    private final int row;
    private final int col;
    private Type type;

    public RenderCell(Cell cell) {
        this.row = cell.getRow();
        this.col = cell.getRow();
        this.type = cell.getType().equals(Cell.Type.WALL) ? Type.WALL : Type.PASSAGE;
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
        return switch (type) {
            case WALL -> "w";
            case PATH -> ".";
            case PASSAGE -> "p";
        };
    }

}
