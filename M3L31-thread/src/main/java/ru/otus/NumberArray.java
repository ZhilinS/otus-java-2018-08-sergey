/*
 * Copyright (C) 2018, SEMRUSH CY LTD or it's affiliates
 */
package ru.otus;

import java.util.Random;
import java.util.stream.IntStream;
import org.cactoos.Scalar;

public final class NumberArray implements Scalar<int[]> {

    private final int numbers;

    public NumberArray(final int numbers) {
        this.numbers = numbers;
    }

    @Override
    public int[] value() throws Exception {
        final Random random = new Random();
        return IntStream.range(0, this.numbers)
            .map(num -> random.nextInt())
            .toArray();
    }
}
