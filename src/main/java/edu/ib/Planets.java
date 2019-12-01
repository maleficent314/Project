package edu.ib;

public class Planets {
    private String name;
    private double eccentricity;
    private double distance;

    public Planets(String name, double eccentricity, double distance) {
        this.name = name;
        this.eccentricity = eccentricity;
        this.distance = distance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getEccentricity() {
        return eccentricity;
    }

    public void setEccentricity(double eccentricity) {
        this.eccentricity = eccentricity;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }


}
