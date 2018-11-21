package ru.otus.withdraw;

import ru.otus.money.Type;

public final class Usd implements Currency {

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
