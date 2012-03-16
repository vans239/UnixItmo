package ru.itmo.govnokod.geometry.io.polygon;


import ru.itmo.govnokod.geometry.Polygon;
import ru.itmo.govnokod.geometry.io.InputStreamPoints;
import ru.itmo.govnokod.geometry.model.Point;

import java.io.InputStream;
import java.util.*;

/**
 * @author Vanslov Evgeny (evans@yandex-team.ru)
 */
public class InputStreamPolygon implements Polygon {
    private final List<Point> points;

    public InputStreamPolygon(final InputStream is){
        points = new InputStreamPoints(is).getPoints();
    }

    public List<Point> getPoints(){
        return new ArrayList<Point>(points);
    }
}
