package ru.itmo.govnokod.geometry.algorithm;

import ru.itmo.govnokod.geometry.model.Point;
import ru.itmo.govnokod.geometry.model.Polygon;

import java.util.List;
import java.util.Set;

/**
 * @author Vanslov Evgeny (evans@yandex-team.ru)
 */

public interface Hull extends Algorithm{
    Polygon hull(final Set<Point> points);
}
