package ru.otus;

import java.util.Collection;
import java.util.Map;
import ru.otus.withdraw.Withdraw;

public abstract class Money {

    @Override
    public boolean equals(final Object obj) {
        if (!(obj instanceof Money)) {
            return false;
        }
        final Money comparing = (Money) obj;
        return this.type().equals(comparing.type());
    }

    abstract Map<Integer, Integer> withdraw(Withdraw withdraw);

    abstract Type type();

    abstract int amount();

    abstract void deposit(Withdraw withdraw);
}
