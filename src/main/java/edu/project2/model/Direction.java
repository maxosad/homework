package edu.project2.model;

import java.util.ArrayList;
import java.util.List;

public enum Direction {
    TOP,
    RIGHT,
    DOWN,
    LEFT;

    public static final ArrayList<Direction> DIRECTION_SETUP = new ArrayList<>(List.of(TOP, RIGHT, DOWN, LEFT));
}
