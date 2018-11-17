package ru.otus.main.testpackage;

import ru.otus.totus.annotations.After;
import ru.otus.totus.annotations.Before;
import ru.otus.totus.annotations.Test;

public class TestFailInPackage {

    @Before
    public void setUp() {
        System.out.println("BEFORE IN " + this.getClass().getSimpleName());
    }

    @Test
    public void testMethod() {
        System.out.println("TESTING NORMAL METHOD " + this.getClass().getSimpleName());
    }

    @After
    public void clean() {
        System.out.println("AFTER IN " + this.getClass().getSimpleName());
    }

    @Test
    public void testMethodFail() {
        System.out.println("TESTING FAIL METHOD " + this.getClass().getSimpleName());
        throw new RuntimeException("Cannot test method");
    }
}
