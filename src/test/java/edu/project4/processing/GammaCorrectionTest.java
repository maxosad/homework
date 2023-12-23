package edu.project4.processing;

import edu.project4.model.FractalImage;
import edu.project4.model.Pixel;
import edu.project4.model.Rect;
import edu.project4.render.LinearRenderer;
import edu.project4.render.Renderer;
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

class GammaCorrectionTest {

    private FractalImage fractalImage;
    private Rect world;
    private List<Transformation> transformations;
    private int samples;
    private short nIters;
    private int seed;

    @BeforeEach
    void setUp() {
        fractalImage = FractalImage.create(1920, 1080);
        world = new Rect(X_MIN, Y_MIN, X_MAX, Y_MAX);
        transformations = new ArrayList<>();
        transformations.add(new LinearTransformation());
        transformations.add(new SinTransformation());
        transformations.add(new SphericalTransformation());
        samples = 10000;
        nIters = 50;
        seed = 0;
    }

    @Test
    void gammaCorrectionTest() {
        Renderer renderer = new LinearRenderer();
        int height = fractalImage.height();
        int width = fractalImage.width();

        FractalImage resFractalImage =
            renderer.render(fractalImage, world, transformations, samples, nIters, seed);
        ImageProcessor correction = new GammaCorrection();
        correction.process(resFractalImage);

        assertNotNull(resFractalImage);
        assertEquals(height, resFractalImage.height());
        assertEquals(width, resFractalImage.width());
        boolean isNonZeroPixel = false;

        for (int y = 0; y < fractalImage.height(); y++) {
            for (int x = 0; x < fractalImage.width(); x++) {
                Pixel pixels = fractalImage.pixel(x, y);
                if (pixels.b() != 0) {
                    isNonZeroPixel = true;
                    break;
                }
                if (pixels.r() != 0) {
                    isNonZeroPixel = true;
                    break;
                }
                if (pixels.g() != 0) {
                    isNonZeroPixel = true;
                    break;
                }

            }
            if (isNonZeroPixel) {
                break;
            }
        }

        assertTrue(isNonZeroPixel);
    }

}
