package ru.otus;

import ru.otus.error.EmptyCacheElementException;

public interface OtusCache<K, T> {

    void put(K key, T element);

    T get(K key) throws EmptyCacheElementException;

    T getOrDefault(K key, T element);
}
