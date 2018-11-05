package ru.otus;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        final OtusList<String> strings = new OtusList<String>();

        System.out.println("Check add methods");
        strings.add("First");
        strings.add("Second");
        strings.add("Third");
        System.out.println(Arrays.toString(strings.toArray()) + "\n===============");

        System.out.println("Check remove from array");
        strings.remove(1);
        strings.remove("Third");
        System.out.println(Arrays.toString(strings.toArray()) + "\n===============");

        System.out.println("Check addition in the head of an array and in the middle");
        strings.add(0, "New first");
        strings.add(0, "New middle element");
        System.out.println(Arrays.toString(strings.toArray()) + "\n===============");

        System.out.println("Check array extension");
        strings.add("Fourth");
        strings.add("Fifth");
        strings.add("Sixth");
        strings.add("Seventh");
        strings.add("Eighth");
        strings.add("Nine");
        strings.add("Nine");
        strings.add("Nine");
        System.out.println(Arrays.toString(strings.toArray()) + "\n===============");

        System.out.println("Check contains, size, addAll methods");
        System.out.println("Should contain: " + strings.contains("Fifth"));
        System.out.println("Shouldn't contain: " + strings.contains("Not this one"));
        System.out.println("Size: " + strings.size());
        strings.addAll(new ArrayList<String>(){{
            add("FIRST FROM COLLECTION");
            add("SECOND FROM COLLECTION");
        }});
        System.out.println(Arrays.toString(strings.toArray()) + "\n===============");
    }
}
