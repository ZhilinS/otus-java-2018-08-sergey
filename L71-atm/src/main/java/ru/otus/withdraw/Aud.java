/*
 * Copyright (C) 2018, SEMRUSH CY LTD or it's affiliates
 */
package ru.otus.withdraw;

import ru.otus.money.Type;

public final class Aud implements Withdraw {

    private final int amount;

    public Aud(
        final int amount
    ) {
        this.amount = amount;
    }

    @Override
    public Type type() {
        return Type.AUD;
    }

    @Override
    public int amount() {
        return this.amount;
    }
}
