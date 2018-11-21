package ru.otus;

import java.util.Collection;

public class Pojo {

    private final String name;
    private final int count;
    private final Collection<Long> collection;

    public Pojo(
        final String name,
        final int count,
        final Collection<Long> collection
    ) {
        this.name = name;
        this.count = count;
        this.collection = collection;
    }
}
