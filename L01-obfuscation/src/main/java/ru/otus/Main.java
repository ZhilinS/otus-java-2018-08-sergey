package ru.otus;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static java.lang.String.format;

public final class Main {

    public static void main(String[] args) throws IOException {
        final List<String> adjectives = Resources.readLines(
                Resources.getResource("adjectives.txt"),
                Charsets.UTF_8
        );
        final List<String> vegetables = Resources.readLines(
                Resources.getResource("vegetables.txt"),
                Charsets.UTF_8
        );

        final ArrayList<Person> persons = newArrayList(
                new Person(
                        adjectives,
                        vegetables
                ),
                new Person(
                        adjectives,
                        vegetables
                )
        );

        System.out.println("Names generation finished");

        persons.forEach(person ->
                System.out.println(
                        format(
                                "Yo vegetable name is: %s %s",
                                person.adjective(),
                                person.vegetable()
                        )
                ));
    }
}
