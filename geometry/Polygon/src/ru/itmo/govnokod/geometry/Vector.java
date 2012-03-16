package ru.itmo.govnokod.geometry;

import ru.itmo.govnokod.geometry.model.Point;

/**
 * @author Vanslov Evgeny (evans@yandex-team.ru)
 */
public class Vector {
    private final Point point;
    
    public Vector(final Point point){
        this.point = point;
    }
    
    public Vector(final Point from, final Point to){
        point = new Point(to.x -from.x, to.y - from.y);
    }

    public long orient(final Vector b) {
        return crossProduct(b);
    }

    public Vector getNormal(){
        return new Vector(new Point(-point.y, point.x));
    }

    public Vector getNegative(){
        return new Vector(new Point(-point.x, -point.y));
    }

    public long crossProduct(final Vector b){
        return point.x * b.point.y - point.y * b.point.x;
    }
}
