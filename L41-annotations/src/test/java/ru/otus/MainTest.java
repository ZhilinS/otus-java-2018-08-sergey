package ru.otus;

import java.lang.reflect.InvocationTargetException;
import ru.otus.totus.Totus;

public class MainTest {

    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Totus.testPackage("ru.otus");
    }
}
