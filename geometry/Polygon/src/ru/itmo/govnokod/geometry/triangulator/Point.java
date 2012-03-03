package ru.itmo.govnokod.geometry.triangulator;/*
*  Date: 28.02.12
*  Time: 18:31
*  Author:
*     Vanslov Evgeny
*     vans239@gmail.com
*/

public class Point implements Comparable<Point>{
    public final long x;
    public final long y;
    public Point(final long x, final long y){
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString(){
        return String.format("(%d, %d)", x, y);
    }


    public int compareTo(Point o) {
        if(x < o.x)
            return -1;
        if(x == o.x){
            return new Long(y).compareTo(o.y);
        }
        return 1;
    }
}
