package ru.otus;

public interface OtusCache<K, T> {

    void put(K key, T element);

    T get(K key);

    T getOrDefault(K key, T element);
}
