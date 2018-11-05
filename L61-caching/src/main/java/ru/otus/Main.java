package ru.otus;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        final OtusCache<String> cache = new OtusCache<>(1000L);
        cache.put("one", "First element");

        Thread.sleep(1000);
        System.out.println(cache.get("one"));
        System.out.println(cache.getOrDefault("two", "Second"));
        System.out.println(cache.getOrDefault("one", "Third"));
    }
}
