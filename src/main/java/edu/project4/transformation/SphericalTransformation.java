package edu.project4.transformation;

import edu.project4.model.Point;

public class SphericalTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double xSqrPlusYSqr = point.x() * point.x() + point.y() * point.y();
        return new Point(point.x() / xSqrPlusYSqr, point.y() / xSqrPlusYSqr);
    }
}
