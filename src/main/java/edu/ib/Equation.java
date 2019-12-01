package edu.ib;

public class Equation implements Function {

    private double finalM;
    private double e;

    public Equation(double finalM, double e) {
        this.finalM = finalM;
        this.e = e;
    }

    @Override
    public double f(double x) {
        return finalM + e * Math.sin(x) - x;
    }
}
