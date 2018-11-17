package ru.otus.withdraw;

import ru.otus.money.Type;

public final class Eur implements Withdraw {

    private final int amount;

    public Eur(final int amount) {
        this.amount = amount;
    }

    @Override
    public Type type() {
        return Type.EUR;
    }

    @Override
    public int amount() {
        return this.amount;
    }
}
