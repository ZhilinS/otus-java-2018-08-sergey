package ru.otus.withdraw;

import ru.otus.money.Type;

public final class Aud implements Currency {

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
