package ru.itmo.govnokod.geometry.triangulator;/*
*  Date: 28.02.12
*  Time: 20:21
*  Author:
*     Vanslov Evgeny
*     vans239@gmail.com
*/

import java.util.LinkedList;

public class Util {
    public static long orient(Point a, Point b, Point c) {
        return a.x * b.y + a.y * c.x + b.x * c.y - c.x * b.y - b.x * a.y - c.y * a.x;
    }

    public static boolean isAngleGood(Point a, Point b, Point c, MonotonePolygon polygon) {
        return ((Util.orient(a, b, c) < 0 && polygon.getPointType(c) == MonotonePolygon.PointType.DOWN)
                ||
                (Util.orient(a, b, c) > 0 && polygon.getPointType(c) == MonotonePolygon.PointType.UP));
    }

    public static <T> T getlast2(LinkedList<T> list){
        T temp = list.pollLast();
        T result = list.getLast();
        list.addLast(temp);
        return result;
    }
}
