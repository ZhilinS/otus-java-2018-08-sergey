package ru.otus.money;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;
import ru.otus.exception.WithdrawException;
import ru.otus.withdraw.Currency;

public abstract class Money {

    @Override
    public String toString() {
        return String.format(
            "%s: %d",
            this.type(),
            this.amount()
        );
    }

    public Map<Integer, Integer> withdraw(Currency currency) {
        if (currency.amount() > this.amount()) {
            throw new WithdrawException("Not enough money on a bank account");
        }
        if (currency.amount() % this.nominals().first() != 0) {
            throw new WithdrawException(
                String.format(
                    "Please enter a sum dividable by %d",
                    this.nominals().first()
                )
            );
        }
        Map<Integer, Integer> amounts = new HashMap<>();
        int retrieve = currency.amount();
        int result = this.amount();
        for (Integer nominal : nominals().descendingSet()) {
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

    public void deposit(Currency currency) {
        int result = this.amount();
        result += currency.amount();
        System.out.println(
            String.format(
                "%d put on deposit. Balance: %d",
                currency.amount(),
                result
            )
        );
        this.update(result);
    }

    public abstract Type type();

    public abstract int amount();

    public abstract int initial();

    public abstract void update(int amount);

    abstract TreeSet<Integer> nominals();
}
