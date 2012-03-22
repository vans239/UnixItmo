package ru.itmo.govnokod.geometry.validator;

/**
 * @author Vanslov Evgeny (evans@yandex-team.ru)
 */

public interface Validator<T> {
    /**
     * if algorithm not valid, throws RuntimeException
     * @param algorithm - algorithm to check
     */
    void validate(final T algorithm);
}
