package ru.otus;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        final OtusCache<String, String> timed = new TimedCache<>(1000L);
        timed.put("one", "First element");

        System.out.println(timed.get("one"));
        Thread.sleep(1000);
        System.out.println(timed.getOrDefault("two", "Second"));
        System.out.println(timed.getOrDefault("one", "Third"));

        System.out.println("=======Done with timed cache========");

        final OtusCache<String, String> access = new AccessCache<>(1000L);
        access.put("one", "First access element");
        Thread.sleep(1000);
        System.out.println(access.get("one"));
        Thread.sleep(1000);
        System.out.println(access.get("one"));

        access.getOrDefault("two", "Second access element");
        Thread.sleep(1000);
        System.out.println(access.get("two"));
    }
}
