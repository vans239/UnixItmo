package ru.itmo.govnokod.geometry.swing.model;

import ru.itmo.govnokod.geometry.swing.DataBuilder;
import ru.itmo.govnokod.geometry.model.Point;

import java.awt.Graphics2D;
import java.util.*;

/**
 * @author Vanslov Evgeny (evans@yandex-team.ru)
 */
public class PointsBuilder implements DataBuilder{
    private static final int RADIUS = 4;
    private final Set<Point> points;

    public PointsBuilder(){
        this(Collections.EMPTY_LIST);
    }

    public PointsBuilder(final List<Point> points){
        this.points = new HashSet<Point>(points);
    }
    public void dropBuilder() {
        points.clear();
    }

    public void addPoint(final Point point) {
        points.add(point);
    }

    public void paint(final Graphics2D graphics2D) {
        for(final Point point : points){
            graphics2D.fillOval((int) point.x, (int) point.y, RADIUS, RADIUS);
        }
    }
    
    public Set<Point> getPoints(){
        return new HashSet<Point>(points);
    }
}
