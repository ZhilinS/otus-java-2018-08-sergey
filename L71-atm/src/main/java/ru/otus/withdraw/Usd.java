/*
 * Copyright (C) 2018, SEMRUSH CY LTD or it's affiliates
 */
package ru.otus.withdraw;

import ru.otus.money.Type;

public final class Usd implements Withdraw {

    private final int amount;

    public Usd(final int amount) {
        this.amount = amount;
    }

    @Override
    public Type type() {
        return Type.USD;
    }

    @Override
    public int amount() {
        return this.amount;
    }
}
