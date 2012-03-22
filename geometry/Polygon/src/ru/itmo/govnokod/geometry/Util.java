package ru.itmo.govnokod.geometry;/*
*  Date: 28.02.12
*  Time: 20:21
*  Author:
*     Vanslov Evgeny
*     vans239@gmail.com
*/

import ru.itmo.govnokod.geometry.model.MonotonePolygon;
import ru.itmo.govnokod.geometry.model.Point;

import java.util.LinkedList;

public class Util {
    public static boolean isAngleGood(Point a, Point b, Point c, MonotonePolygon polygon) {
        long orient = new Vector(a, b).orient(new Vector(a, c));
        return ((orient < 0 && polygon.getPointType(c) == MonotonePolygon.PointType.DOWN)
                ||
                (orient > 0 && polygon.getPointType(c) == MonotonePolygon.PointType.UP));
    }

    public static <T> T getlast2(LinkedList<T> list){
        T temp = list.pollLast();
        T result = list.getLast();
        list.addLast(temp);
        return result;
    }
}
