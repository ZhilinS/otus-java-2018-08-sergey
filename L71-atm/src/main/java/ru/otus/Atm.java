package ru.otus;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import ru.otus.exception.DepositException;
import ru.otus.exception.WithdrawException;
import ru.otus.money.Money;
import ru.otus.money.Type;
import ru.otus.withdraw.Currency;

public final class Atm {

    private final Set<Money> moneys;

    public Atm(
        final Set<Money> moneys
    ) {
        this.moneys = moneys;
    }

    public void deposit(final Currency currency) {
        final Optional<Money> money = this.moneys
            .stream()
            .filter(current -> current.type().equals(currency.type()))
            .findFirst();
        if (money.isPresent()) {
            money.get().deposit(currency);
        } else {
            throw new DepositException(
                "Couldn't find corresponding cell in ATM"
            );
        }
    }

    public Map<Integer, Integer> withdraw(final Currency currency) {
        return this.moneys
            .stream()
            .filter(current -> current.type().equals(currency.type()))
            .findFirst()
            .orElseThrow(
                () -> new WithdrawException("Couldn't find matching money type")
            )
            .withdraw(currency);
    }

    public Set<Money> moneys() {
        return this.moneys;
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
