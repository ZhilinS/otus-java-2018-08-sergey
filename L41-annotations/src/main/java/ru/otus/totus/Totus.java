package ru.otus.totus;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import ru.otus.totus.annotations.After;
import ru.otus.totus.annotations.Before;
import ru.otus.totus.annotations.Test;
import static java.util.Objects.nonNull;

public class Totus {

    public static void testClass(final String name) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        AtomicInteger failed = new AtomicInteger(0);
        AtomicInteger total = new AtomicInteger(0);

        final Class<?> target = Class.forName(name);
        final Method[] methods = target.getDeclaredMethods();

        if (accessible(target)) {
            Method before = null;
            Method after = null;
            List<Method> testMethods = new ArrayList<>();

            for (Method method : methods) {
                if (method.isAnnotationPresent(Before.class)) {
                    if (nonNull(before)) {
                        throw new RuntimeException(
                            "Before annotation is already present." +
                                "Couldn't be more than one in class."
                        );
                    }
                    before = method;
                }

                if (method.isAnnotationPresent(After.class)) {
                    if (nonNull(after)) {
                        throw new RuntimeException(
                            "After annotation is already present." +
                                "Couldn't be more than one in class."
                        );
                    }
                    after = method;
                }

                if (method.isAnnotationPresent(Test.class)) {
                    testMethods.add(method);
                }
            }

            for (Method test : testMethods) {
                final Object o = target.newInstance();

                try {
                    total.incrementAndGet();

                    if (before != null) {
                        before.invoke(o);
                    }

                    test.invoke(o);

                    if (after != null) {
                        after.invoke(o);
                    }
                } catch (Exception e) {
                    System.err.println("Test failed");
                    e.printStackTrace();
                    failed.incrementAndGet();
                }
            }
        }

        if (failed.get() > 0) {
            System.out.println(
                String.format(
                    "%d/%d tests failed",
                    failed.get(),
                    total.get()
                )
            );
        } else if (total.get() == 0) {
            System.out.println(
                String.format(
                    "No tests were found in %s",
                    target.getName()
                )
            );
        } else {
            System.out.println(
                String.format(
                    "%d tests passed",
                    total.get()
                )
            );
        }
    }

    public static void testClass(final Class<?> claz) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        Totus.testClass(claz.getName());
    }

    public static void testPackage(final String pack) throws ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Reflections ref = new Reflections(pack, new SubTypesScanner(false));
        final Set<Class<?>> classes = ref.getSubTypesOf(Object.class);

        for (Class clazz:classes) {
            Totus.testClass(clazz);
        }
    }

    private static boolean accessible(Class<?> clazz) {
        return !(clazz.isAnnotation() || clazz.isMemberClass());
    }
}
