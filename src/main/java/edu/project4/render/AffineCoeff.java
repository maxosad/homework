package edu.project4.render;

import edu.project4.model.AffineCoefficients;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("MagicNumber")
public class AffineCoeff {

    public static final int CHOOSE_BLOCK = -20;
    public static final List<AffineCoefficients> COEFF = new ArrayList<>();

    public static final int MAX_RGB_VALUE = 256;

    static {
        SecureRandom random = new SecureRandom();
        int count = 0;
        while (count < 10) {
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
                COEFF.add(new AffineCoefficients(a, b, c, d, e, f,
                    random.nextInt(MAX_RGB_VALUE), random.nextInt(MAX_RGB_VALUE), random.nextInt(MAX_RGB_VALUE)
                ));
            }
        }
    }

    protected AffineCoeff() { }
}
