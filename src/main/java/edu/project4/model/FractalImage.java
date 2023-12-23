package edu.project4.model;

public record FractalImage(Pixel[][] data, int width, int height) {
    public static FractalImage create(int width, int height) {
        Pixel[][] newData = new Pixel[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                newData[y][x] = new Pixel(0, 0, 0, 0);
            }
        }
       return new FractalImage(newData, width, height);
    }

    public boolean contains(int x, int y) {
        return (0 <= x && x < width && 0 <= y && y < height);
    }

    public Pixel pixel(int x, int y) {
        return data[y][x];
    }
}
