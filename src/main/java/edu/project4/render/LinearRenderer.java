package edu.project4.render;

import edu.project4.model.Affine;
import edu.project4.model.FractalImage;
import edu.project4.model.Pixel;
import edu.project4.model.Rect;
import edu.project4.transformation.Transformation;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LinearRenderer implements Renderer {
    public static final double X_MIN = -1.777;
    public static final double X_MAX = 1.777;
    public static final double Y_MIN = -1;
    public static final double Y_MAX = 1;

    public static final List<Affine> COEFF = new ArrayList<>();

    static {
        SecureRandom random = new SecureRandom();
        int count = 0;
        while (count < 3) {
            double a = random.nextDouble(-1, 1);
            double b = random.nextDouble(-1, 1);
            double c = random.nextDouble(-1, 1);
            double d = random.nextDouble(-1, 1);
            double e = random.nextDouble(-1, 1);
            double f = random.nextDouble(-1, 1);
            if (a * a + d * d < 1
                && b * b + e * e < 1
                && a * a + d * d + b * b + e * e < 1 + Math.pow(a * e - b * d, 2)) {
                count++;
                COEFF.add(new Affine(a, b, c, d, e, f,
                    random.nextInt(256), random.nextInt(256),random.nextInt(256)));
            }
        }
    }

    @Override
    public FractalImage render(
        FractalImage canvas,
        Rect world,
        List<Transformation> variations,
        int samples,
        short iterPerSample,
        long seed
    ) {
        Random random = new Random(seed);
        for (int sample = 0; sample < samples; sample++) {
            double newX = random.nextDouble(X_MIN, X_MAX);
            double newY = random.nextDouble(Y_MIN, Y_MAX);

            for (int step = -20; step < iterPerSample; step++) {
                int i = random.nextInt(COEFF.size());

                double x = COEFF.get(i).a() * newX + COEFF.get(i).b() * newY + COEFF.get(i).c();
                double y = COEFF.get(i).d() * newX + COEFF.get(i).e() * newY + COEFF.get(i).f();

                if (step >= 0 && X_MIN <= newX && newX <= X_MAX && Y_MIN <= newY && newY <= Y_MAX) {
                    int x1 = canvas.width() - (int) (((X_MAX - newX) / (X_MAX - X_MIN)) * canvas.width());
                    int y1 = canvas.height() - (int) (((Y_MAX - newY) / (Y_MAX - Y_MIN)) * canvas.height());

                    if (canvas.contains(x1, y1)) {
                        Pixel pixel = canvas.pixel(x1, y1);
                        if (pixel.hitCount() == 0) {
                            canvas.data()[y1][x1] = new Pixel(COEFF.get(i).red(),
                                COEFF.get(i).green(),
                                COEFF.get(i).blue(), 1);
                        } else {
                            canvas.data()[y1][x1] = new Pixel(
                                (pixel.r() + COEFF.get(i).red()) / 2,
                                (pixel.g() + COEFF.get(i).green()) / 2,
                                (pixel.b() + COEFF.get(i).blue()) / 2,
                                pixel.hitCount() + 1
                                );
                        }
                    }
                }
            }
        }
        return canvas;
    }
}
