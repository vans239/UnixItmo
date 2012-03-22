package ru.itmo.govnokod.geometry.model;

import ru.itmo.govnokod.geometry.Vector;

/**
 * @author Vanslov Evgeny (evans@yandex-team.ru)
 */
public class Intercept {
    private final Point a;
    private final Point b;

    public Intercept(final Point a, final Point b) {
        this.a = a;
        this.b = b;
    }

    public boolean isIntercept(final Intercept obj) {
        return isInterceptPart(obj) && obj.isInterceptPart(this);
    }

    public Point getInterceptPoint(final Intercept obj){
        return null;
    }
    private boolean isInterceptPart(final Intercept obj) {
        long orient1 = new Vector(a, b).orient(new Vector(a, obj.a));
        long orient2 = new Vector(a, b).orient(new Vector(a, obj.b));
        if (orient1 == 0) {
            return isBetween(obj.a);
        }
        if (orient2 == 0) {
            return isBetween(obj.b);
        }
        return Math.signum(orient1) != Math.signum(orient2);
    }

    private boolean isBetween(final Point obj) {
        if (((a.x <= obj.x && obj.x <= b.x) || (a.x >= obj.x && obj.x >= b.x))
                && ((a.y <= obj.y && obj.y <= b.y) || (a.y >= obj.y && obj.y >= b.y))) {
            return true;
        }
        return false;
    }

    public String toString() {
        return a + " " + b;
    }

    public static void main(String[] args) {
        System.out.println(
                new Intercept(
                        new Point(0, 2), new Point(5, 6))
                        .isIntercept(new Intercept(
                                new Point(-5, 4), new Point(7, 4))));
    }
}
