package ru.otus.withdraw;

import ru.otus.money.Type;

public interface Withdraw {

    Type type();

    int amount();
}
