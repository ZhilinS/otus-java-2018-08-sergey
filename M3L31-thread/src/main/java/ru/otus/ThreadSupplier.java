/*
 * Copyright (C) 2018, SEMRUSH CY LTD or it's affiliates
 */
package ru.otus;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.cactoos.Scalar;

public class ThreadSupplier implements Scalar<List<Thread>> {

    private final int threads;

    public ThreadSupplier(
        final int threads
    ) {
        this.threads = threads;
    }

    @Override
    public List<Thread> value() throws Exception {
        return IntStream.range(0, this.threads)
            .mapToObj(number -> new Thread(String.format("Thread #%d", number)))
            .collect(Collectors.toList());
    }
}
