/*
 * Copyright (C) 2018, SEMRUSH CY LTD or it's affiliates
 */
package ru.otus.money;

import java.util.TreeSet;

public final class Rubles extends Money {

    private final TreeSet<Integer> nominals;
    private int amount;

    public Rubles(
        final TreeSet<Integer> nominals,
        final int amount
    ) {
        this.nominals = nominals;
        this.amount = amount;
    }

    @Override
    public Type type() {
        return Type.RUR;
    }

    @Override
    public int amount() {
        return this.amount;
    }

    @Override
    void update(final int amount) {
        this.amount = amount;
    }

    @Override
    TreeSet<Integer> nominals() {
        return this.nominals;
    }
}
