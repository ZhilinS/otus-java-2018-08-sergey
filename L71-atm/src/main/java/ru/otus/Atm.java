/*
 * Copyright (C) 2018, SEMRUSH CY LTD or it's affiliates
 */
package ru.otus;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import ru.otus.exception.WithdrawException;
import ru.otus.withdraw.Withdraw;

public final class Atm {

    private final Set<Money> money;

    public Atm(
        final Set<Money> money
    ) {
        this.money = money;
    }

    public void deposit(final Withdraw withdraw) {
        this.money
            .stream()
            .filter(current -> current.type().equals(withdraw.type()))
            .findFirst()
            .ifPresent(current -> current.deposit(withdraw));
    }

    public Map<Integer, Integer> withdraw(final Withdraw withdraw) {
        return this.money
            .stream()
            .filter(current -> current.type().equals(withdraw.type()))
            .findFirst()
            .orElseThrow(
                () -> new WithdrawException("Couldn't find matching money type")
            )
            .withdraw(withdraw);
    }

    public Map<Type, Integer> balance() {
        return money
            .stream()
            .collect(
                Collectors.toMap(
                    Money::type,
                    Money::amount
                )
            );
    }
}
