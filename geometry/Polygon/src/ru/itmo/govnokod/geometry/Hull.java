package ru.itmo.govnokod.geometry;

import ru.itmo.govnokod.geometry.model.Point;

import java.util.List;

/**
 * @author Vanslov Evgeny (evans@yandex-team.ru)
 */

public interface Hull {
    Polygon hull(List<Point> points);
}
