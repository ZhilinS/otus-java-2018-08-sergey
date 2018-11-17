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

    private final Set<Money> moneys;

    public Atm(
        final Set<Money> moneys
    ) {
        this.moneys = moneys;
    }

    public void deposit(final Withdraw withdraw) {
        this.moneys
            .stream()
            .filter(current -> current.type().equals(withdraw.type()))
            .findFirst()
            .ifPresent(current -> current.deposit(withdraw));
    }

    public Map<Integer, Integer> withdraw(final Withdraw withdraw) {
        return this.moneys
            .stream()
            .filter(current -> current.type().equals(withdraw.type()))
            .findFirst()
            .orElseThrow(
                () -> new WithdrawException("Couldn't find matching money type")
            )
            .withdraw(withdraw);
    }

    public Map<Type, Integer> balance() {
        return moneys
            .stream()
            .collect(
                Collectors.toMap(
                    Money::type,
                    Money::amount
                )
            );
    }
}
