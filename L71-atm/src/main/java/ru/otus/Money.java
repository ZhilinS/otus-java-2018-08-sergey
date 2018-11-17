package ru.otus;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;
import ru.otus.exception.WithdrawException;
import ru.otus.withdraw.Withdraw;

abstract class Money {

    Map<Integer, Integer> withdraw(Withdraw withdraw) {
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

    abstract Type type();

    abstract int amount();

    void deposit(Withdraw withdraw) {
        int result = this.amount();
        result += withdraw.amount();
        System.out.println(
            String.format(
                "%d put on deposit. Balance: %d",
                withdraw.amount(),
                result
            )
        );
        this.update(result);
    }

    abstract void update(int amount);

    abstract TreeSet<Integer> nominals();
}
