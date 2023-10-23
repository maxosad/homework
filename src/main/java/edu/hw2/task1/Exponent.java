package edu.hw2.task1;

public record Exponent(Expr expr, double power) implements Expr {
    @Override
    public double evaluate() {
        return Math.pow(expr.evaluate(), power);
    }
}
