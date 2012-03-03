package ru.itmo.govnokod.geometry.triangulator.impl;

/*
*  Date: 28.02.12
*  Time: 18:42
*  Author:
*     Vanslov Evgeny
*     vans239@gmail.com
*/

import ru.itmo.govnokod.geometry.triangulator.MonotonePolygon;
import ru.itmo.govnokod.geometry.triangulator.Point;

import java.io.InputStream;
import java.util.*;

public class InputStreamPolygon implements MonotonePolygon {
    private final List<Point> points;
    private final Map<Point, MonotonePolygon.PointType> pointTypes = new HashMap<Point, MonotonePolygon.PointType>();

    public InputStreamPolygon(InputStream is){
        Scanner scanner = new Scanner(is);
        int count = scanner.nextInt();
        points = new ArrayList<Point>(count);
        scanner.nextLine();
        for(int i = 0; i < count; ++i){
            String line = scanner.nextLine();
            line = line.replaceAll("[(),]", " ");
            line = line.replaceAll(" +", " ");
            line = line.replaceAll("^ ", "");
            String values[] = line.split(" ");
            Point point = new Point(Long.valueOf(values[0]), Long.valueOf(values[1]));
            points.add(point);
        }
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
        return points;
    }


    public MonotonePolygon.PointType getPointType(Point point) {
        return pointTypes.get(point);  //To change body of implemented methods use File | Settings | File Templates.
    }
}
