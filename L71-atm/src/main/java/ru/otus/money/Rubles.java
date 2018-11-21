package ru.otus.money;

import java.util.Arrays;
import java.util.TreeSet;

public final class Rubles extends Money {

    private final Integer[] nominals;
    private final int initial;
    private int amount;

    public Rubles(
        final int amount,
        final Integer... nominals
    ) {
        this.nominals = nominals;
        this.amount = amount;
        this.initial = amount;
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
    public int initial() {
        return this.initial;
    }

    @Override
    public void update(final int amount) {
        this.amount = amount;
    }

    @Override
    TreeSet<Integer> nominals() {
        return new TreeSet<>(Arrays.asList(this.nominals));
    }
}
