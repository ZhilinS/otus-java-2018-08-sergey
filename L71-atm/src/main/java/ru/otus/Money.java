package ru.otus;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;
import ru.otus.exception.WithdrawException;
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

    public Map<Integer, Integer> withdraw(Withdraw withdraw) {
        if (withdraw.amount() > this.amount()) {
            throw new WithdrawException("Not enough money on a bank account");
        }
        if (withdraw.amount() % this.nominals().first() != 0) {
            throw new WithdrawException(
                String.format(
                    "Please enter a sum dividable by %d",
                    this.nominals().first()
                )
            );
        }
        Map<Integer, Integer> amounts = new HashMap<>();
        int retrieve = withdraw.amount();
        int result = this.amount();
        for (Integer nominal:nominals().descendingSet()) {
            final int banknotes = retrieve / nominal;
            if (banknotes > 0) {
                amounts.put(nominal, banknotes);
                result -= banknotes * nominal;
                retrieve -= banknotes * nominal;
            }
        }
        this.update(result);
        return amounts;
    }

    abstract Type type();

    abstract int amount();

    abstract void deposit(Withdraw withdraw);

    abstract void update(int amount);

    abstract TreeSet<Integer> nominals();
}
