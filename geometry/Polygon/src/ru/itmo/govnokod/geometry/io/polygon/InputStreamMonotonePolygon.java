package ru.itmo.govnokod.geometry.io.polygon;

/*
*  Date: 28.02.12
*  Time: 18:42
*  Author:
*     Vanslov Evgeny
*     vans239@gmail.com
*/

import ru.itmo.govnokod.geometry.MonotonePolygon;
import ru.itmo.govnokod.geometry.model.Point;

import java.io.InputStream;
import java.util.*;

public class InputStreamMonotonePolygon implements MonotonePolygon {
    private final List<Point> points;
    private final Map<Point, MonotonePolygon.PointType> pointTypes = new HashMap<Point, MonotonePolygon.PointType>();

    public InputStreamMonotonePolygon(final InputStream is){
        points = new InputStreamPolygon(is).getPoints();
        initPointTypes();
    }

    private void initPointTypes() {
        Point start = Collections.min(points);
        int startIndex = points.indexOf(start);
        pointTypes.put(start, MonotonePolygon.PointType.UP);
        int index = startIndex + 1;
        MonotonePolygon.PointType type= MonotonePolygon.PointType.UP;
        while(index != startIndex){
            pointTypes.put(points.get(index), type);
            int nextIndex = (index + 1) % points.size();
            if(points.get(index).compareTo(points.get(nextIndex)) == 1){
                type = MonotonePolygon.PointType.DOWN;
            }
            index = nextIndex;
        }
    }

    public List<Point> getPoints(){
        return new ArrayList<Point>(points);
    }

    public MonotonePolygon.PointType getPointType(Point point) {
        return pointTypes.get(point);
    }
}
