package edu.project4.render;

import edu.project4.model.FractalImage;
import edu.project4.model.Pixel;
import edu.project4.model.Point;
import edu.project4.model.Rect;
import edu.project4.model.Rgb;
import edu.project4.transformation.Transformation;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

public class ParallelRenderer extends AffineCoeff implements Renderer {
    public static final int THREADS_COUNT = 10;

    @Override
    public FractalImage render(
        FractalImage canvas,
        Rect world,
        List<Transformation> variations,
        int samples,
        short iterPerSample,
        long seed
    ) {
        Runnable task = () -> {
            double newX = ThreadLocalRandom.current().nextDouble(world.xMin(), world.xMax());
            double newY = ThreadLocalRandom.current().nextDouble(world.yMin(), world.yMax());
            for (int step = CHOOSE_BLOCK; step < iterPerSample; step++) {
                int i = ThreadLocalRandom.current().nextInt(COEFF.size());
                double x = COEFF.get(i).a() * newX + COEFF.get(i).b() * newY + COEFF.get(i).c();
                double y = COEFF.get(i).d() * newX + COEFF.get(i).e() * newY + COEFF.get(i).f();
                Transformation transformation  = variations.get(ThreadLocalRandom.current().nextInt(variations.size()));
                var newPoint = transformation.apply(new Point(x, y));
                x = newPoint.x();
                y = newPoint.y();
                if (step >= 0 && world.contains(newPoint)) {
                    int x1 = canvas.width() - (int) (((world.xMax() - x) / (world.xMax() - world.xMin()))
                        * canvas.width());
                    int y1 = canvas.height() - (int) (((world.yMax() - y) / (world.yMax() - world.yMin()))
                        * canvas.height());
                    if (canvas.contains(x1, y1)) {
                        Pixel pixel = canvas.pixel(x1, y1);
                        Rgb coeffRgb = COEFF.get(i).rgb();
                        synchronized (pixel) {
                            if (pixel.hitCount() == 0) {
                                canvas.data()[y1][x1] = new Pixel(coeffRgb.red(),
                                    coeffRgb.green(),
                                    coeffRgb.blue(), 1
                                );
                            } else {
                                canvas.data()[y1][x1] = new Pixel(
                                    (pixel.r() + coeffRgb.red()) / 2,
                                    (pixel.g() + coeffRgb.green()) / 2,
                                    (pixel.b() + coeffRgb.blue()) / 2,
                                    pixel.hitCount() + 1
                                );
                            }
                        }
                    }
                }

            }
        };
        try (ExecutorService executor = Executors.newFixedThreadPool(THREADS_COUNT)) {
            for (int sample = 0; sample < samples; sample++) {
                executor.execute(task);
            }
        }
        return canvas;
    }
}
