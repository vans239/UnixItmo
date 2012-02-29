/*
*  Date: 28.02.12
*  Time: 19:44
*  Author:
*     Vanslov Evgeny
*     vans239@gmail.com
*/

import java.util.List;

public interface MonotonePolygon extends Polygon{
    PointType getPointType(Point point);
    enum PointType{
        UP,
        DOWN
    }
}
