package ru.itmo.govnokod.geometry.swing;

import ru.itmo.govnokod.geometry.model.Point;

/**
 * @author Vanslov Evgeny (evans@yandex-team.ru)
 */

public interface DataBuilder extends Drawable{
    void addPoint(final Point point);

    void dropBuilder();
}
