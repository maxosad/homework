package edu.project4.processing;

import edu.project4.model.FractalImage;
import edu.project4.model.Pixel;
import static java.lang.Math.log10;
import static java.lang.Math.pow;

public class GammaCorrection implements ImageProcessor {
    public static final double GAMMA = 5;

    @Override
    public void process(FractalImage image) {
        double max = 0.0;
        for (int y = 0; y < image.height(); y++) {
            for (int x = 0; x < image.width(); x++) {
                Pixel pixel = image.pixel(x, y);
                if (pixel.hitCount() != 0) {
                    double norm = log10(pixel.hitCount());
                    if (max < norm) {
                        max = norm;
                    }
                }
            }
        }

        for (int y = 0; y < image.height(); y++) {
            for (int x = 0; x < image.width(); x++) {
                Pixel pixel = image.pixel(x, y);
                if (pixel.hitCount() != 0) {
                    double normal = log10(pixel.hitCount()) / max;
                    image.data()[y][x] = new Pixel(
                        (int) (pixel.r() * pow(normal, (1.0 / GAMMA))),
                        (int) (pixel.g() * pow(normal, (1.0 / GAMMA))),
                        (int) (pixel.b() * pow(normal, (1.0 / GAMMA))),
                        pixel.hitCount()
                    );
                }
            }
        }
    }
}
