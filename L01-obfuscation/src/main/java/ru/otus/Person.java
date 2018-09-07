package ru.otus;

import java.util.List;
import java.util.Random;

public final class Person {

    private final List<String> adjectives;
    private final List<String> vegetables;

    public Person(
            List<String> adjectives,
            List<String> vegetables
    ) {
        this.adjectives = adjectives;
        this.vegetables = vegetables;
    }

    public String adjective() {
        return adjectives.get(new Random().nextInt(adjectives.size()));
    }

    public String vegetable() {
        return vegetables.get(new Random().nextInt(vegetables.size()));
    }


}
