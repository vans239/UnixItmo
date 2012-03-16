package ru.itmo.govnokod.geometry;

/*
*  Date: 28.02.12
*  Time: 19:44
*  Author:
*     Vanslov Evgeny
*     vans239@gmail.com
*/

import ru.itmo.govnokod.geometry.model.Point;

public interface MonotonePolygon extends Polygon{
    PointType getPointType(Point point);
    enum PointType{
        UP,
        DOWN
    }
}
