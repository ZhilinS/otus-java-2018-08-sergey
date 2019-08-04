/*
 * Copyright (C) 2018, SEMRUSH CY LTD or it's affiliates
 */

import java.util.Arrays;
import org.cactoos.Scalar;
import org.cactoos.scalar.Sticky;
import org.junit.Test;
import ru.otus.NumberArray;
import ru.otus.Sorting;
import static org.junit.Assert.assertArrayEquals;

public class SortingTest {

    @Test
    public void shouldSort() throws Exception {
        final Scalar<int[]> numbers = new Sticky<>(new NumberArray(20));
        final Scalar<int[]> sorting = new Sticky<>(
            new Sorting(
                numbers,
                () -> 4
            )
        );
        final int[] sorted = numbers.value();
        Arrays.sort(sorted);
        assertArrayEquals(sorted, sorting.value());
    }
}
