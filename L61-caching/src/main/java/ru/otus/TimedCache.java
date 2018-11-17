package ru.otus;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import static java.util.Objects.isNull;

public final class TimedCache<K, T> implements OtusCache<K, T>  {

    private final long ttl;
    private final Map<K, SoftReference<T>> cache;

    public TimedCache(final long ttl) {
        this(ttl, new HashMap<>());
    }

    public TimedCache(
        final long ttl,
        final Map<K, SoftReference<T>> cache
    ) {
        this.ttl = ttl;
        this.cache = cache;
    }

    @Override
    public void put(K key, T element) {
        final SoftReference<T> ref = new SoftReference<>(element);
        cache.put(key, ref);

        new Timer().schedule(
            new TimerTask() {
                @Override
                public void run() {
                    ref.clear();
                }
            },
            this.ttl
        );
    }

    @Override
    public T get(K key)  {
        final SoftReference<T> ref = this.cache.get(key);
        if (isNull(ref)) {
            return null;
        }
        return ref.get();
    }

    @Override
    public T getOrDefault(final K key, final T element) {
        final T cached = this.get(key);
        if (isNull(cached)) {
            this.put(key, element);
        }
        return element;
    }
}
