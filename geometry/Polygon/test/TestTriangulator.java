/*
*  Date: 03.03.12
*  Time: 08:43
*  Author:
*     Vanslov Evgeny
*     vans239@gmail.com
*/

import ru.itmo.govnokod.geometry.MonotonePolygon;
import ru.itmo.govnokod.geometry.io.polygon.InputStreamMonotonePolygon;
import ru.itmo.govnokod.geometry.model.Point;
import ru.itmo.govnokod.geometry.model.Triangle;
import ru.itmo.govnokod.geometry.Trianglulator;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestTriangulator {

    public static void main(String[] args) throws FileNotFoundException {
        File dir = new File("resources/monotone_triangulator");
        FileFilter filter = new FileFilter() {
            public boolean accept(File file) {
                return file.getName().matches(".*txt");
            }
        };
        for (File file : dir.listFiles(filter)) {
            MonotonePolygon polygon = new InputStreamMonotonePolygon(new BufferedInputStream(new FileInputStream(file)));

            Map<Point, Integer> verticles = new HashMap<Point, Integer>();
            int i = 1;
            for (Point point : polygon.getPoints()) {
                verticles.put(point, i++);
            }

            Trianglulator trianglulator = new Trianglulator();
            List<Triangle> triangles = trianglulator.triangulate(polygon);

            for (Triangle triangle : triangles) {
                System.out.println(verticles.get(triangle.points[0]) + " "
                        + verticles.get(triangle.points[1]) + " "
                        + verticles.get(triangle.points[2]));
            }
            System.out.println();
        }
    }
}

/*
1 2 7
2 3 7
3 7 4
7 4 8
4 8 5
8 5 9
5 9 6

2 1 3
3 1 4

2 1 3
3 1 4
4 1 5
*/
