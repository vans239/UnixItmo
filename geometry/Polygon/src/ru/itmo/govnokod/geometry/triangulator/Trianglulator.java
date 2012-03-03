package ru.itmo.govnokod.geometry.triangulator;/*
*  Date: 28.02.12
*  Time: 18:31
*  Author:
*     Vanslov Evgeny
*     vans239@gmail.com
*/

import ru.itmo.govnokod.geometry.triangulator.impl.InputStreamPolygon;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class Trianglulator {
    public List<Triangle> triangulate(MonotonePolygon polygon) {
        ArrayList<Point> points = new ArrayList<Point>(polygon.getPoints());
        Collections.sort(points);

        if (points.size() < 3) {
            throw new RuntimeException("strange polygon");
        }

        List<Triangle> triangles = new ArrayList<Triangle>();
        LinkedList<Point> temp = new LinkedList<Point>();
        temp.addLast(points.get(0));
        temp.addLast(points.get(1));
        for (int i = 2; i < points.size(); ++i) {
            Point lastInStack = temp.getLast();
            Point current = points.get(i);
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
//        MonotonePolygon polygon = new InputStreamPolygon(new BufferedInputStream(new FileInputStream("tests/input3.txt")));
        MonotonePolygon polygon = new ru.itmo.govnokod.geometry.triangulator.impl.InputStreamPolygon(System.in);

        Map<Point,Integer> verticles = new HashMap<Point, Integer>();
        int i = 1;
        for(Point point : polygon.getPoints()){
            verticles.put(point, i++);
        }

        Trianglulator trianglulator = new Trianglulator();
        List<Triangle> triangles = trianglulator.triangulate(polygon);

        for(Triangle triangle : triangles){
            System.out.println(verticles.get(triangle.points[0]) + " "
                    + verticles.get(triangle.points[1]) + " "
                    + verticles.get(triangle.points[2]));
        }
    }
}
