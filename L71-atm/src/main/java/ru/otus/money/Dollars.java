package ru.otus.money;

import java.util.TreeSet;

public final class Dollars extends Money {

    private final TreeSet<Integer> nominals;
    private final int initial;
    private int amount;

    public Dollars(
        final TreeSet<Integer> nominals,
        final int amount
    ) {
        this.nominals = nominals;
        this.amount = amount;
        this.initial = amount;
    }

    @Override
    public Type type() {
        return Type.USD;
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
        return this.nominals;
    }
}
