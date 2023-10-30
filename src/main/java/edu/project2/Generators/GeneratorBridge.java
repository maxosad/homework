package edu.project2.Generators;

import edu.project2.model.Cell;
import edu.project2.model.GeneratorType;
import edu.project2.model.Maze;

public class GeneratorBridge implements Generator {
    private Generator generator;

    public GeneratorBridge(GeneratorType type) {
        generator = switch (type) {
            case BFS -> new BFSGenerator();
            case PRIM -> new PrimsGenerator();
        };
    }

    private Maze wrapMazeWithWall(Maze maze, boolean hEven, boolean wEven) {
        int newHeight = hEven ? maze.getHeight() - 1 : maze.getHeight();
        int newWidth = wEven ? maze.getWidth() - 1 : maze.getWidth();
        Cell[][] mazeGrid = maze.getGrid();
        Cell[][] newGrid = new Cell[newHeight][newWidth];
        for (int i = 0; i < newHeight - 1; i++) {
            for (int j = 0; j < newWidth - 1; j++) {
                newGrid[i][j] = mazeGrid[i][j];
            }
        }
        for (int i = 0; i < newHeight; i++) {
            newGrid[i][newWidth - 1] = new Cell(i, newWidth - 1, Cell.Type.WALL);
        }

        for (int j = 0; j < newWidth; j++) {
            newGrid[newHeight - 1][j] = mazeGrid[newHeight - 1][j];
        }
        return new Maze(newHeight, newWidth, newGrid);
    }

    @Override
    public Maze generate(int height, int width, int seed) {
        boolean hEven = height % 2 == 0;
        boolean wEven = width % 2 == 0;
        Maze maze = generator.generate(hEven ? height + 1 : height, wEven ? width + 1 : width, seed);
        return wrapMazeWithWall(maze, hEven, wEven);
    }
}
