package ru.itmo.govnokod.geometry.io;

import ru.itmo.govnokod.geometry.model.SetOfPoints;
import ru.itmo.govnokod.geometry.model.Point;

import java.util.*;

/**
 * @author Vanslov Evgeny (evans@yandex-team.ru)
 */
public class RandomSetOfPoints implements SetOfPoints {
    private static final int MAX = 1000000;
    private final Set<Point> points;
    
    public RandomSetOfPoints(final int count){
        final Random random = new Random();
        points = new HashSet<Point>();
        for(int i = 0; i < count; ++i){
            points.add(new Point(random.nextInt(MAX), random.nextInt(MAX)));
        }
        
    }
    public Set<Point> getPoints() {
        return new HashSet<Point>(points);
    }
}
