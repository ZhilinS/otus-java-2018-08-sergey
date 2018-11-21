package ru.otus.withdraw;

import ru.otus.money.Type;

public interface Currency {

    Type type();

    int amount();
}
