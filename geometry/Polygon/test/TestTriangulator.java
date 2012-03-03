/*
*  Date: 03.03.12
*  Time: 08:43
*  Author:
*     Vanslov Evgeny
*     vans239@gmail.com
*/

import ru.itmo.govnokod.geometry.triangulator.MonotonePolygon;
import ru.itmo.govnokod.geometry.triangulator.Point;
import ru.itmo.govnokod.geometry.triangulator.Triangle;
import ru.itmo.govnokod.geometry.triangulator.Trianglulator;
import ru.itmo.govnokod.geometry.triangulator.impl.InputStreamPolygon;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestTriangulator {

    public static void main(String[] args) throws FileNotFoundException {
        File dir = new File("test");
        FileFilter filter = new FileFilter() {
            public boolean accept(File file) {
                return file.getName().matches(".*txt");
            }
        };
        for (File file : dir.listFiles(filter)) {
            MonotonePolygon polygon = new InputStreamPolygon(new BufferedInputStream(new FileInputStream(file)));

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
