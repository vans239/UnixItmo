package ru.itmo.govnokod.geometry.model;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (x != point.x) return false;
        if (y != point.y) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (x ^ (x >>> 32));
        result = 31 * result + (int) (y ^ (y >>> 32));
        return result;
    }

    public int compareTo(final Point o) {
        if(x < o.x)
            return -1;
        if(x == o.x){
            return new Long(y).compareTo(o.y);
        }
        return 1;
    }
}
