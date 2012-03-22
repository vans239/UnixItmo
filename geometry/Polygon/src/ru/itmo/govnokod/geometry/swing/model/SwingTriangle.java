package ru.itmo.govnokod.geometry.swing.model;

import ru.itmo.govnokod.geometry.model.Polygon;
import ru.itmo.govnokod.geometry.swing.Drawable;
import ru.itmo.govnokod.geometry.model.Point;
import ru.itmo.govnokod.geometry.model.Triangle;

import java.awt.Graphics2D;
import java.util.Arrays;
import java.util.List;

/**
 * @author Vanslov Evgeny (evans@yandex-team.ru)
 */
public class SwingTriangle implements Drawable{
    private java.awt.Polygon polygon;

    public SwingTriangle(final Triangle triangle) {
        this.polygon = asAwtShape(new PolygonBuilder(Arrays.asList(triangle.points)));
    }

    public void paint(final Graphics2D graphics2D) {
        graphics2D.draw(polygon);
    }

    public java.awt.Polygon asAwtShape(final Polygon polygon){
        List<Point> points = polygon.getPoints();
        int xPoints[] = new int[points.size()];
        int yPoints[] = new int[points.size()];
        int i = 0;
        for(final ru.itmo.govnokod.geometry.model.Point point : points){
            xPoints[i] = (int) point.x;
            yPoints[i] = (int) point.y;
            ++i;
        }
        return new java.awt.Polygon(xPoints,yPoints, points.size());
    }
}
