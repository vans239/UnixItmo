package ru.itmo.govnokod.geometry.swing.model;

import ru.itmo.govnokod.geometry.swing.DataBuilder;
import ru.itmo.govnokod.geometry.Polygon;
import ru.itmo.govnokod.geometry.model.Point;

import java.awt.Graphics2D;
import java.util.*;

/**
 * @author Vanslov Evgeny (evans@yandex-team.ru)
 */
public class PolygonBuilder implements Polygon, DataBuilder{
    private final List<Point> points;

    public PolygonBuilder(){
        this(Collections.EMPTY_LIST);
    }
    
    public PolygonBuilder(final List<Point> points){
        this.points = new ArrayList<Point>(points);
    }

    public void addPoint(final Point point){
        if(isBadPointForThisPolygon(point)){
            throw new IllegalArgumentException("This point isn't good for this polygon");
        }
        points.add(point);
    }

    public void dropBuilder(){
        points.clear();
    }

    public List<Point> getPoints() {
        return points;
    }

    private boolean isBadPointForThisPolygon(final Point point) {
        return false;
    }

    public void paint(final Graphics2D graphics2D) {
        new SwingPolygon(this).paint(graphics2D);
    }
}
