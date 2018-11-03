package ru.otus;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Benchmark {

    public static void test() {
        List<Cargo> list = new ArrayList<>();
        Random rand = new Random();

        while (true) {
            int count = 5 * 10000;

            for (int i = 0; i < count; i++) {
                list.add(new Cargo(rand.nextLong()));
            }

            System.out.println(
                String.format(
                    "Created %d local objects",
                    count
                )
            );

            Gc.state();
            for (int i = list.size() - count/2; i < list.size(); i++) {
                list.remove(i);
            }
        }
    }
}
