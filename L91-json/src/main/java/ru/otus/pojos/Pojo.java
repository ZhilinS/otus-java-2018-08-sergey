package ru.otus.pojos;

import java.util.Collection;

public final class Pojo<T> {

    private final String name;
    private final int count;
    private final Collection<Long> collection;
    private final double[] doubleArray;
    private final T[] objects;

    public Pojo(
        final String name,
        final int count,
        final Collection<Long> collection,
        final double[] doubleArray,
        final T[] objects
    ) {
        this.name = name;
        this.count = count;
        this.collection = collection;
        this.doubleArray = doubleArray;
        this.objects = objects;
    }
}
