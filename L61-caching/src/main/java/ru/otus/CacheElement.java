package ru.otus;

import java.lang.ref.SoftReference;
import java.util.Timer;
import java.util.TimerTask;
import ru.otus.error.EmptyCacheElementException;
import static java.util.Objects.isNull;

public final class CacheElement<T> {

    private SoftReference<T> element;

    public CacheElement(
        final long ttl,
        final SoftReference<T> element
    ) {
        this.element = element;

        new Timer().schedule(
            new TimerTask() {
                @Override
                public void run() {
                    invalidate();
                }
            },
            ttl
        );
    }

    public T get(String key) throws EmptyCacheElementException {
        if (isNull(this.element.get())) {
            throw new EmptyCacheElementException(key);
        }
        return this.element.get();
    }

    private void invalidate() {
        this.element.clear();
    }
}
