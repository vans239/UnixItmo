package ru.itmo.govnokod.geometry.algorithm;


import ru.itmo.govnokod.geometry.model.MonotonePolygon;
import ru.itmo.govnokod.geometry.model.Triangle;

import java.util.List;

/**
 * @author Vanslov Evgeny (evans@yandex-team.ru)
 */
public interface Triangulator extends Algorithm{
    List<Triangle> triangulate(MonotonePolygon polygon);
}
