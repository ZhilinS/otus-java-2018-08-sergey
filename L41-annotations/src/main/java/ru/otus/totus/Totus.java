package ru.otus.totus;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import ru.otus.totus.annotations.After;
import ru.otus.totus.annotations.Before;
import ru.otus.totus.annotations.Test;

public class Totus {

    public static void testClass(final String name) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        AtomicInteger failed = new AtomicInteger(0);
        AtomicInteger total = new AtomicInteger(0);

        final Class<?> target = Class.forName(name);
        final Method[] methods = target.getDeclaredMethods();

        if (accessible(target)) {
            final Object o = target.newInstance();

            Method before = null;
            Method after = null;

            for (Method method : methods) {
                if (method.isAnnotationPresent(Before.class)) {
                    before = method;
                }

                if (method.isAnnotationPresent(After.class)) {
                    after = method;
                }

                if (method.isAnnotationPresent(Test.class)) {
                    try {
                        total.incrementAndGet();

                        if (before != null) {
                            before.invoke(o);
                        }

                        method.invoke(o);

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
