package ru.itmo.govnokod.geometry.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vanslov Evgeny (evans@yandex-team.ru)
 */
public class SimplePolygon implements Polygon {
    private final List<Point> points;
    
    public SimplePolygon(final List<Point> points) {
        this.points = new ArrayList<Point>(points);
    }

    public List<Point> getPoints() {
        return new ArrayList<Point>(points);
    }
}
