package edu.project4;

import edu.project4.model.FractalImage;
import edu.project4.model.ImageFormat;
import edu.project4.model.Rect;
import edu.project4.processing.GammaCorrection;
import edu.project4.processing.ImageProcessor;
import edu.project4.render.LinearRenderer;
import edu.project4.render.ParallelRenderer;
import edu.project4.render.Renderer;
import edu.project4.transformation.DiskTransformation;
import edu.project4.transformation.LinearTransformation;
import edu.project4.transformation.PolarTransformation;
import edu.project4.transformation.SinTransformation;
import edu.project4.transformation.SphericalTransformation;
import edu.project4.transformation.Transformation;
import edu.project4.utils.ImageUtils;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("MagicNumber")
public class Main {
    public static final double X_MIN = -1.777;
    public static final double X_MAX = 1.777;
    public static final double Y_MIN = -1;
    public static final double Y_MAX = 1;

    private final static Logger LOGGER = LogManager.getLogger();

    private Main() {
    }

    public static void main(String[] args) {
        Rect world = new Rect(X_MIN, Y_MIN, X_MAX, Y_MAX);

        List<Transformation> transformations = new ArrayList<>(3);
        transformations.add(new LinearTransformation());
        transformations.add(new SinTransformation());
        transformations.add(new SphericalTransformation());
        transformations.add(new PolarTransformation());
        transformations.add(new DiskTransformation());

        Renderer linearRenderer = new LinearRenderer();
        Renderer parallelRenderer = new ParallelRenderer();

        ImageProcessor gammaCorrection = new GammaCorrection();

        String pathLinear = "src/main/resources/files/image11.png";
        FractalImage fractalImage = FractalImage.create(1920, 1080);
        long startLinear = System.nanoTime();
        fractalImage = linearRenderer.render(fractalImage, world, transformations, 100000, (short) 50, 0);
        long linearTime = System.nanoTime() - startLinear;
        gammaCorrection.process(fractalImage);
        ImageUtils.save(fractalImage, Path.of(pathLinear), ImageFormat.PNG);

        String pathParallel = "src/main/resources/files/image10.png";
        fractalImage = FractalImage.create(1920, 1080);
        long startParallel = System.nanoTime();
        fractalImage = parallelRenderer.render(fractalImage, world, transformations, 100000, (short) 50, 0);
        long parallelTime = System.nanoTime() - startParallel;
        gammaCorrection.process(fractalImage);
        ImageUtils.save(fractalImage, Path.of(pathParallel), ImageFormat.PNG);

        LOGGER.info("linear is {}, parallel is {}", linearTime, parallelTime);
    }
}
