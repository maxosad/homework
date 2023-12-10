package edu.project4;

import edu.project4.model.FractalImage;
import edu.project4.model.ImageFormat;
import edu.project4.model.Rect;
import edu.project4.render.LinearRenderer;
import edu.project4.render.Renderer;
import edu.project4.transformation.LinearTransformation;
import edu.project4.transformation.SinTransformation;
import edu.project4.transformation.SphericalTransformation;
import edu.project4.transformation.Transformation;
import edu.project4.utils.ImageUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.nio.file.Path;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private final static Logger LOGGER = LogManager.getLogger();

    private Main() {
    }

    public static void main(String[] args) {
        Rect world = new Rect(-1.0, -1.0, 2.0, 2.0);
        FractalImage fractalImage = FractalImage.create(1920, 1080);

        List<Transformation> transformations = new ArrayList<>(3);
        transformations.add(new LinearTransformation());
        transformations.add(new SinTransformation());
        transformations.add(new SphericalTransformation());

        Renderer linearRenderer = new LinearRenderer();
        fractalImage = linearRenderer.render(fractalImage, world, transformations, 1000000, (short) 50, 0);

        String path = "src/main/java/edu/project4/files/image2.png";
        ImageUtils.save(fractalImage, Path.of(path), ImageFormat.PNG);
    }
}
