/*
 * Copyright (C) 2018, SEMRUSH CY LTD or it's affiliates
 */
package ru.otus;

import java.util.TreeSet;
import ru.otus.withdraw.Withdraw;

public class Rubles extends Money {

    private final Type type;
    private final TreeSet<Integer> nominals;
    private int amount;

    public Rubles(
        final Type type,
        final TreeSet<Integer> nominals,
        final int amount
    ) {
        this.type = type;
        this.nominals = nominals;
        this.amount = amount;
    }

    @Override
    Type type() {
        return this.type;
    }

    @Override
    int amount() {
        return this.amount;
    }

    @Override
    void deposit(final Withdraw withdraw) {
        if (this.type.equals(withdraw.type())) {
            this.amount += withdraw.amount();
            System.out.println(
                String.format(
                    "%d put on deposit. Balance: %d",
                    withdraw.amount(),
                    this.amount
                )
            );
        }
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
