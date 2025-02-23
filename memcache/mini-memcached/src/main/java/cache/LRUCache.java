package cache;

import java.util.*;

public class LRUCache<K, V> {
    private final int capacity;
    private final Map<K, V> cache;
    private final LinkedHashMap<K, Long> usageTracker;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.usageTracker = new LinkedHashMap<>(capacity, 0.75f, true);
    }

    public synchronized V get(K key) {
        if (!cache.containsKey(key)) return null;
        usageTracker.put(key, System.nanoTime());
        return cache.get(key);
    }

    public synchronized void put(K key, V value) {
        if (cache.size() >= capacity) {
            K lruKey = usageTracker.keySet().iterator().next();
            cache.remove(lruKey);
            usageTracker.remove(lruKey);
        }
        cache.put(key, value);
        usageTracker.put(key, System.nanoTime());
    }
}
