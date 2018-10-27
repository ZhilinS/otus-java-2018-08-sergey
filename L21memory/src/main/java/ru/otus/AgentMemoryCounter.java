package ru.otus;

import java.lang.instrument.Instrumentation;

public class AgentMemoryCounter {

    private static Instrumentation instrumentation;

    public static void premain(String args, Instrumentation instrumentation) {
        AgentMemoryCounter.instrumentation = instrumentation;
    }

    public static long objectSize(Object object) {
        return instrumentation.getObjectSize(object);
    }
}
