/*
 * Copyright (C) 2018, SEMRUSH CY LTD or it's affiliates
 */
package ru.otus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
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
        final List<Thread> runners = new ArrayList<>(this.threads.value());
        final List<int[]> parts = new ArrayList<>(this.numbers.value().length);
        for (int[] part : divided()) {
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

    private List<int[]> divided() throws Exception {
        final int[] nums = this.numbers.value();
        final int chunk = nums.length / this.threads.value();
        return IntStream.range(0, this.threads.value())
            .boxed()
            .map(i -> Arrays.copyOfRange(
                        nums,
                        chunk * i,
                        chunk * (i + 1)
                    )
            )
            .collect(Collectors.toList());
    }
}
