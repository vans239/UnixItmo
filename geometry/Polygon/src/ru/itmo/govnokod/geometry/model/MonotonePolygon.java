package ru.itmo.govnokod.geometry.model;

/*
*  Date: 28.02.12
*  Time: 19:44
*  Author:
*     Vanslov Evgeny
*     vans239@gmail.com
*/

public interface MonotonePolygon extends Polygon {
    PointType getPointType(Point point);
    enum PointType{
        UP,
        DOWN
    }
}
