package ru.otus;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;
import ru.otus.error.EmptyCacheElementException;

public class OtusCache<T> {

    private final long ttl;
    private final Map<String, CacheElement<T>> cache;

    public OtusCache(final long ttl) {
        this(ttl, new HashMap<>());
    }

    public OtusCache(
        final long ttl,
        final Map<String, CacheElement<T>> cache
    ) {
        this.ttl = ttl;
        this.cache = cache;
    }

    public void put(String key, T element) {
        cache.putIfAbsent(
            key,
            new CacheElement(this.ttl, new SoftReference<>(element))
        );
    }

    public T get(String key) throws EmptyCacheElementException {
        final CacheElement<T> ref = cache.get(key);
        return ref.get(key);
    }

    public T getOrDefault(String key, T element) {
        try {
            return this.get(key);
        } catch (EmptyCacheElementException | NullPointerException error) {
            this.put(key, element);
            return element;
        }
    }
}
