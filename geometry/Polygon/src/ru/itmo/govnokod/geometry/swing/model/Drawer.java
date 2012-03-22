package ru.itmo.govnokod.geometry.swing.model;

import ru.itmo.govnokod.geometry.model.Polygon;
import ru.itmo.govnokod.geometry.swing.Drawable;
import ru.itmo.govnokod.geometry.model.Point;
import ru.itmo.govnokod.geometry.model.Triangle;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vanslov Evgeny (evans@yandex-team.ru)
 */
public class Drawer implements Drawable {
    private final List<Drawable> toDraw = new ArrayList<Drawable>();

    public void dropToDraw(){
        toDraw.clear();
    }

    public void addPolygon(final Polygon polygon){
        toDraw.add(new SwingPolygon(polygon));
    }

    public void addPoints(final List<Point> points){
        toDraw.add(new PointsBuilder(points));
    }

    public void addTriangle(final Triangle triangle){
        toDraw.add(new SwingTriangle(triangle));
    }

    public void paint(final Graphics2D graphics2D) {
        for(final Drawable drawable : toDraw){
            drawable.paint(graphics2D);
        }
    }
}
