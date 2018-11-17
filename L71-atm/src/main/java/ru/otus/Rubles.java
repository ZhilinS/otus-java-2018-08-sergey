/*
 * Copyright (C) 2018, SEMRUSH CY LTD or it's affiliates
 */
package ru.otus;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;
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
    public Map<Integer, Integer> withdraw(final Withdraw withdraw) {
        Map<Integer, Integer> amounts = new HashMap<>();
        int retrieve = withdraw.amount();
        for (Integer nominal:nominals.descendingSet()) {
            final int banknotes = retrieve / nominal;
            if (banknotes > 0) {
                amounts.put(nominal, banknotes);
                amount -= banknotes * nominal;
                retrieve -= banknotes * nominal;
            }
        }
        return amounts;
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
}
