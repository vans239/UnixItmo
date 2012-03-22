import ru.itmo.govnokod.geometry.algorithm.Hull;
import ru.itmo.govnokod.geometry.model.Polygon;
import ru.itmo.govnokod.geometry.algorithm.QuickHull;
import ru.itmo.govnokod.geometry.io.InputStreamPoints;
import ru.itmo.govnokod.geometry.model.Point;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Vanslov Evgeny (evans@yandex-team.ru)
 */
public class TestHull {
    public static void main(String[] args) throws FileNotFoundException {
            final Set<Point> points = new InputStreamPoints(
                    new BufferedInputStream(new FileInputStream("resources/hull/input2.txt"))).getPoints();

            final Map<Point, Integer> verticles = new HashMap<Point, Integer>();
            int i = 1;
            for (final Point point : points) {
                verticles.put(point, i++);
            }

            final Hull hull= new QuickHull();
            final Polygon polygon = hull.hull(points);

            for (final Point point: polygon.getPoints()) {
                System.out.print(verticles.get(point) + " ");
            }
            System.out.println();
    }
}
