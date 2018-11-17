package ru.otus;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import static java.util.Objects.isNull;

public final class AccessCache<K, T> implements OtusCache<K, T> {

    private final long ttl;
    private final Map<K, SoftReference<T>> cache;

    public AccessCache(
        final long ttl
    ) {
        this(ttl, new HashMap<>());
    }

    public AccessCache(
        final long ttl,
        final Map<K, SoftReference<T>> cache
    ) {
        this.ttl = ttl;
        this.cache = cache;
    }

    @Override
    public void put(final K key, final T element) {
        final SoftReference<T> ref = new SoftReference<>(element);
        cache.put(key, ref);
    }

    @Override
    public T get(final K key) {
        final SoftReference<T> ref = this.cache.get(key);
        if (isNull(ref)) {
            return null;
        }
        new Timer().schedule(
            new TimerTask() {
                @Override
                public void run() {
                    ref.clear();
                }
            },
            this.ttl
        );
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
