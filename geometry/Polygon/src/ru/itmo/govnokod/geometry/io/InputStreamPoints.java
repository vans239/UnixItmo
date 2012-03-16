package ru.itmo.govnokod.geometry.io;

import ru.itmo.govnokod.geometry.model.Point;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Vanslov Evgeny (evans@yandex-team.ru)
 */
public class InputStreamPoints {
    private final List<Point> points;

    public InputStreamPoints(final InputStream is){
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
