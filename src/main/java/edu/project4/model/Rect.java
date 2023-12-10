package edu.project4.model;

public record Rect(double xMin, double yMin,  double xMax, double yMax) {
    public boolean contains(Point p) {
        return xMin <= p.x() && p.x() <= xMax && yMin <= p.y() && p.y() <= yMax;
    }
}
