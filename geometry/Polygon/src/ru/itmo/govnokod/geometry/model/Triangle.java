package ru.itmo.govnokod.geometry.model;

public class Triangle {
    public final Point points[] = new Point[3];
    public Triangle(Point a, Point b, Point c) {
        points[0] = a;
        points[1] = b;
        points[2] = c;
    }

    @Override
    public String toString() {
        return points[0] + " " + points[1] + " " + points[2];
    }
}
