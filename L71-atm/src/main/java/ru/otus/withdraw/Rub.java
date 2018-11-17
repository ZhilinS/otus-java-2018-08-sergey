/*
 * Copyright (C) 2018, SEMRUSH CY LTD or it's affiliates
 */
package ru.otus.withdraw;

import ru.otus.Type;

public class Rub implements Withdraw {

    private final int amount;

    public Rub(final int amount) {
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
}
