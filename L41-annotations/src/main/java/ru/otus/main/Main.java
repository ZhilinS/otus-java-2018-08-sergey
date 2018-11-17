package ru.otus.main;

import java.lang.reflect.InvocationTargetException;
import ru.otus.totus.Totus;

public class Main {

    public static void main(String[] args)
        throws IllegalAccessException,
        ClassNotFoundException,
        InstantiationException,
        InvocationTargetException
    {
        Totus.testClass(TestClass.class);
        Totus.testPackage("ru.otus");
    }
}
