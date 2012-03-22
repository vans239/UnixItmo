package ru.itmo.govnokod.geometry.io;

import ru.itmo.govnokod.geometry.model.SetOfPoints;
import ru.itmo.govnokod.geometry.model.Point;

import java.io.InputStream;
import java.util.*;

/**
 * @author Vanslov Evgeny (evans@yandex-team.ru)
 */

public class InputStreamPoints implements SetOfPoints {
    private final Set<Point> points;

    public InputStreamPoints(final InputStream is){
        final Scanner scanner = new Scanner(is);
        int count = scanner.nextInt();
        points = new HashSet<Point>(count);
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

    public Set<Point> getPoints(){
        return new HashSet<Point>(points);
    }
}
