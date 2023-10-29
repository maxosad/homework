package edu.project2.Generators;

import edu.project2.model.Maze;

public interface Generator {
    Maze generate(int height, int width, int seed);
}
