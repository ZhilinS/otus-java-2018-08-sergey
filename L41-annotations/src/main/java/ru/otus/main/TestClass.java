package ru.otus.main;

import ru.otus.totus.annotations.After;
import ru.otus.totus.annotations.Before;
import ru.otus.totus.annotations.Test;

public class TestClass {

    @Before
    public void setUp() {
        System.out.println("BEFORE IN " + this.getClass().getSimpleName());
    }

    @Test
    public void testMethod() {
        System.out.println("TESTING " + this.getClass().getSimpleName());
    }

    @After
    public void clean() {
        System.out.println("AFTER IN " + this.getClass().getSimpleName());
    }
}
