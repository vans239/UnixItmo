package ru.itmo.govnokod.geometry.validator;

import ru.itmo.govnokod.geometry.Vector;
import ru.itmo.govnokod.geometry.algorithm.Hull;
import ru.itmo.govnokod.geometry.algorithm.QuickHull;
import ru.itmo.govnokod.geometry.io.RandomSetOfPoints;
import ru.itmo.govnokod.geometry.model.Intercept;
import ru.itmo.govnokod.geometry.model.Point;
import ru.itmo.govnokod.geometry.model.Polygon;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Vanslov Evgeny (evans@yandex-team.ru)
 */
public class CheckPointsHullValidator implements Validator<Hull> {
    private static int COUNT_OF_POINTS = 1000000;
    private final RandomSetOfPoints setOfPoints = new RandomSetOfPoints(COUNT_OF_POINTS);
    private ArrayList<Point> polygonPoints;
    private Set<Point> polygonPointsSet;

    public void validate(final Hull algorithm) {
        final Set<Point> points = setOfPoints.getPoints();
        final Polygon polygon = algorithm.hull(points);
        polygonPoints = new ArrayList<Point>(polygon.getPoints());
        polygonPointsSet = new HashSet<Point>(polygonPoints);

//        System.out.println(polygon.getPoints());
        for (final Point point : points) {
            if (!isPointInHullPolygon(point)) {
                System.out.println("Point isn't in polygon: " + point + " " + polygon);
                throw new RuntimeException("Point isn't in polygon: " + point + " " + polygon);
            }
        }
        polygonPoints = null;
        polygonPointsSet = null;
    }

    private boolean isPointInPolygon(final Point point) {
        int countOfIntersects = 0;
        final Intercept baseIntercept = new Intercept(new Point(Integer.MIN_VALUE, point.y), point);
        for (int i = 0; i < polygonPoints.size(); ++i) {
            final Point a = polygonPoints.get(i);
            final Point b = polygonPoints.get((i + 1) % polygonPoints.size());
            final Intercept intercept = new Intercept(a, b);
            if (point.equals(a) || point.equals(b)) {
                return true;
            }
            //todo get point intercept
            if (baseIntercept.isIntercept(intercept)) {
                System.out.println("Intersects " + point + " || " + baseIntercept + " " + intercept);
                final Point interceptPoint = baseIntercept.getInterceptPoint(intercept);
                if (!polygonPointsSet.contains(interceptPoint)) {
                    ++countOfIntersects;
                }
            }

        }
        return countOfIntersects % 2 == 1;
    }

    private boolean isPointInHullPolygon(final Point point) {
        Integer orient = null;
        for (int i = 0; i < polygonPoints.size(); ++i) {
            final Point a = polygonPoints.get(i);
            final Point b = polygonPoints.get((i + 1) % polygonPoints.size());
            final int currentOrient = Long.signum(new Vector(b, a).orient(new Vector(point, a)));
            if (!point.equals(a) || !point.equals(b) || currentOrient == 0) {
                break;
            }
            if (orient == null) {
                orient = currentOrient;
            } else if (orient != currentOrient) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        final int COUNT_OF_TESTS = 10;
        for (int i = 1; i <= COUNT_OF_TESTS; ++i) {
            new CheckPointsHullValidator().validate(new QuickHull());
            System.out.println(String.format("Test %d ok", i));
        }
    }
}
