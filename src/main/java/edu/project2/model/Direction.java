package edu.project2.model;

import java.util.ArrayList;
import java.util.List;

public enum Direction {
    TOP,
    RIGHT,
    DOWN,
    LEFT;

    public static final List<Direction> DIRECTION_SETUP = List.of(TOP, RIGHT, DOWN, LEFT);
}
