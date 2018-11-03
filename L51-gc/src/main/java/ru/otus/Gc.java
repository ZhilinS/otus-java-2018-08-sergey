package ru.otus;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Gc {

    private static final Set<String> YOUNG = young();
    private static final Set<String> OLD = old();

    public static void state() {
        final List<GarbageCollectorMXBean> gcBeans
            = ManagementFactory.getGarbageCollectorMXBeans();

        long minorCount = 0;
        long minorTime = 0;
        long majorCount = 0;
        long majorTime = 0;
        long unknownCount = 0;
        long unknownTime = 0;

        for (GarbageCollectorMXBean gcBean:gcBeans) {
            final long count = gcBean.getCollectionCount();
            final long time = gcBean.getCollectionTime();

            if (YOUNG.contains(gcBean.getName())) {
                minorCount += count;
                minorTime += time;
            } else if (OLD.contains(gcBean.getName())) {
                majorCount += count;
                majorTime += time;
            } else {
                unknownCount += count;
                unknownTime += time;
            }
        }

        StringBuilder b = new StringBuilder();
        b.append(
            String.format(
                "Minor -> collected %d times for %d ms.\n" +
                    "Major -> collected %d times for %d ms.",
                minorCount,
                minorTime,
                majorCount,
                majorTime
            )
        );

        if (unknownCount > 0) {
            b.append(
                String.format(
                    "\nUnknown -> collected %d times for %d ms",
                    unknownCount,
                    unknownTime
                )
            );
        }

        System.out.println(b.toString());
    }

    private static Set<String> young() {
        Set<String> young = new HashSet<String>(3);
        young.add("PS Scavenge");
        young.add("ParNew");
        young.add("G1 Young Generation");
        return young;
    }

    private static Set<String> old() {
        Set<String> old = new HashSet<String>(3);
        old.add("PS MarkSweep");
        old.add("ConcurrentMarkSweep");
        old.add("G1 Old Generation");
        return old;
    }
}
