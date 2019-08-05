/*
 * Copyright (C) 2018, SEMRUSH CY LTD or it's affiliates
 */

import models.UserDataSet;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public final class UserDataTest {

    private final UserDBExecutor executor = new UserDBExecutor(
        new DBExecutor<>(
            "otus",
            "otus",
            "otus"
        )
    );

    @Before
    public void prepareSchema() {
        this.executor.createTable();
    }

    @Test
    public void testInsert() {
        this.executor.insert("SERGEY", 27);
        this.executor.insert("KSENIA", 26);
        final UserDataSet first = new UserDataSet(1L, "SERGEY", 27);
        final UserDataSet second = new UserDataSet(2L, "KSENIA", 26);
        assertEquals(first, this.executor.get(1L));
        assertEquals(second, this.executor.get(2L));
    }

    @After
    public void dropSchema() {
        this.executor.deleteTable();
    }
}
