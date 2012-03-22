package ru.itmo.govnokod.geometry.algorithm;

/*
*  Date: 28.02.12
*  Time: 18:31
*  Author:
*     Vanslov Evgeny
*     vans239@gmail.com
*/

import ru.itmo.govnokod.geometry.model.MonotonePolygon;
import ru.itmo.govnokod.geometry.Util;
import ru.itmo.govnokod.geometry.io.polygon.InputStreamMonotonePolygon;
import ru.itmo.govnokod.geometry.model.Point;
import ru.itmo.govnokod.geometry.model.Triangle;

import java.io.FileNotFoundException;
import java.util.*;

public class MonotneTrianglulator implements Triangulator {
    public List<Triangle> triangulate(MonotonePolygon polygon) {
        final ArrayList<Point> points = new ArrayList<Point>(polygon.getPoints());
        Collections.sort(points);

        if (points.size() < 3) {
            throw new RuntimeException("strange polygon");
        }

        final List<Triangle> triangles = new ArrayList<Triangle>();
        final LinkedList<Point> temp = new LinkedList<Point>();
        temp.addLast(points.get(0));
        temp.addLast(points.get(1));
        for (int i = 2; i < points.size(); ++i) {
            final Point lastInStack = temp.getLast();
            final Point current = points.get(i);
            if (polygon.getPointType(lastInStack) != polygon.getPointType(current)) {
                // different chains
                while (temp.size() >= 2 && Util.isAngleGood(temp.get(0), temp.get(1), current, polygon)) {
                    triangles.add(new Triangle(temp.get(0), temp.get(1), current));
                    temp.pollFirst();
                }
                temp.addLast(current);
            } else {
                //same chains
                while (temp.size() >= 2 && Util.isAngleGood(temp.getLast(), Util.getlast2(temp), current, polygon)) {
                    triangles.add(new Triangle(temp.getLast(), Util.getlast2(temp), current));
                    temp.pollLast();
                }
                temp.addLast(current);
            }
        }
        return triangles;
    }

    public static void main(String[] args) throws FileNotFoundException {
//        MonotonePolygon polygon = new InputStreamMonotonePolygon(new BufferedInputStream(new FileInputStream("tests/input3.txt")));
        final MonotonePolygon polygon = new InputStreamMonotonePolygon(System.in);

        final Map<Point,Integer> verticles = new HashMap<Point, Integer>();
        int i = 1;
        for(Point point : polygon.getPoints()){
            verticles.put(point, i++);
        }

        final MonotneTrianglulator trianglulator = new MonotneTrianglulator();
        final List<Triangle> triangles = trianglulator.triangulate(polygon);

        for(final Triangle triangle : triangles){
            System.out.println(verticles.get(triangle.points[0]) + " "
                    + verticles.get(triangle.points[1]) + " "
                    + verticles.get(triangle.points[2]));
        }
    }
}
