/*
 * Copyright (C) 2018, SEMRUSH CY LTD or it's affiliates
 */
package ru.otus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import org.cactoos.Scalar;

public final class Sorting implements Scalar<int[]> {

    private final Scalar<int[]> numbers;
    private final Scalar<Integer> threads;

    public Sorting(
        final Scalar<int[]> numbers,
        final Scalar<Integer> threads
    ) {
        this.numbers = numbers;
        this.threads = threads;
    }

    @Override
    public int[] value() throws Exception {
        final int chunk = this.numbers.value().length / this.threads.value();
        int start = 0;
        final List<Thread> runners = new ArrayList<>(this.threads.value());
        final List<int[]> parts = new ArrayList<>(
            this.numbers.value().length
        );
        for (int i = 0; i < this.threads.value(); i++) {
            final int[] part = Arrays.copyOfRange(
                this.numbers.value(),
                start,
                start += chunk
            );
            runners.add(
                new Thread(() -> {
                    Arrays.sort(part);
                    try {
                        Thread.sleep(new Random().nextInt(3000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                })
            );
            parts.add(part);
        }
        for (Thread runner : runners) {
            runner.start();
        }
        for (Thread runner : runners) {
            runner.join();
        }
        return parts.stream()
            .flatMapToInt(Arrays::stream)
            .toArray();


    }
}
