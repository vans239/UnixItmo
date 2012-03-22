package ru.itmo.govnokod.geometry.io.polygon;


import ru.itmo.govnokod.geometry.model.Polygon;
import ru.itmo.govnokod.geometry.io.InputStreamPoints;
import ru.itmo.govnokod.geometry.model.Point;

import java.io.InputStream;
import java.util.*;

/**
 * @author Vanslov Evgeny (evans@yandex-team.ru)
 */
public class InputStreamPolygon implements Polygon {
    private final List<Point> points;

    public InputStreamPolygon(final InputStream is){
        final Scanner scanner = new Scanner(is);
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
    }

    public List<Point> getPoints(){
        return new ArrayList<Point>(points);
    }
}
