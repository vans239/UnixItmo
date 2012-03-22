package ru.itmo.govnokod.geometry.algorithm;

import ru.itmo.govnokod.geometry.model.Polygon;
import ru.itmo.govnokod.geometry.Vector;
import ru.itmo.govnokod.geometry.model.Point;
import ru.itmo.govnokod.geometry.model.SimplePolygon;

import java.util.*;

/**
 * @author Vanslov Evgeny (evans@yandex-team.ru)
 */
public class QuickHull implements Hull {
    private List<Point> result = new ArrayList<Point>();
    private Set<Point> added;

    public Polygon hull(final Set<Point> points) {
        added = new HashSet<Point>();
        result.clear();
        hullPart(points, new Vector(new Point(1, 0)));
        return new SimplePolygon(result);
    }

    private void hullPart(final Set<Point> points, final Vector vector) {
        if (points.isEmpty()) {
            return;
        }
        final Point left = Collections.min(points, new PointComparator(vector.getNormal()));
        final Point right = Collections.max(points, new PointComparator(vector.getNormal()));
        final Point down = Collections.max(points, new PointComparator(vector));
        final Point up = Collections.min(points, new PointComparator(vector));

        final Set<Point> upLeft = choose(points, left, up);
        final Set<Point> upRight = choose(points, up, right);
        final Set<Point> downRight = choose(points, right, down);
        final Set<Point> downLeft = choose(points, down, left);

        addToAnswer(left);
        hullPart(upLeft, new Vector(left, up));
        addToAnswer(up);
        hullPart(upRight, new Vector(up, right));
        addToAnswer(right);
        hullPart(downRight, new Vector(right, down));
        addToAnswer(down);
        hullPart(downLeft, new Vector(down, left));
/*
        System.out.println(upLeft);
        System.out.println(upRight);
        System.out.println(downRight);
        System.out.println(downLeft);

        System.out.println(left);
        System.out.println(right);
        System.out.println(up);
        System.out.println(down);
*/
    }

    private void addToAnswer(final Point point) {
        if (!added.contains(point)) {
            result.add(point);
            added.add(point);
        }
    }

    private Set<Point> choose(final Set<Point> points, final Point a, final Point b) {
        final Set<Point> result = new HashSet<Point>();
        if (a.equals(b))
            return result;
        for (final Point point : points) {
            if (new Vector(a, b).orient(new Vector(a, point)) > 0) {
                result.add(point);
            }
        }
        if(result.size() == 0){
            return result;
        }
        result.add(a);
        result.add(b);
        return result;
    }

    private class PointComparator implements Comparator<Point> {
        private final Vector vector;

        public PointComparator(final Vector vector) {
            this.vector = vector;
        }

        public int compare(final Point a, final Point b) {
            int compare = Long.valueOf(new Vector(a).crossProduct(vector)).
                    compareTo(new Vector(b).crossProduct(vector));
            if(compare == 0){
                return a.compareTo(b);
            }
            return  compare;
        }
    }
}
