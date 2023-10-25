package edu.project2;

import java.util.Map;

public record Cell(int row, int col, Type type) {
    public enum Type { WALL, PASSAGE }
}
