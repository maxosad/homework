package edu.project4.model;

import edu.project4.transformation.LinearTransformation;
import edu.project4.transformation.SinTransformation;
import edu.project4.transformation.SphericalTransformation;
import edu.project4.transformation.Transformation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static edu.project4.Main.X_MAX;
import static edu.project4.Main.X_MIN;
import static edu.project4.Main.Y_MAX;
import static edu.project4.Main.Y_MIN;
import static org.junit.jupiter.api.Assertions.*;

class FractalImageTest {

    private int width;
    private int height;


    @BeforeEach
    void setUp() {
        width = 1920;
        height = 1080;
    }

    @Test
    void createTest() {
        FractalImage fractalImage = FractalImage.create(width, height);

        assertNotNull(fractalImage);
        assertEquals(width, fractalImage.width());
        assertEquals(height, fractalImage.height());

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Pixel pixel = fractalImage.pixel(x, y);
                assertEquals(pixel.r(), 0);
                assertEquals(pixel.g(), 0);
                assertEquals(pixel.b(), 0);
                assertEquals(pixel.hitCount(), 0);
            }
        }
    }

}
