package com.netcracker.java_concurrency_basics.computable_cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ComputableCache<K, V> {
    private final Map<K, V> cache = new ConcurrentHashMap<>();
    private final Map<K, Object> locks = new ConcurrentHashMap<>();

    public V compute(K key) {
        return cache.computeIfAbsent(key, this::computeExpensive);

        /*V res = cache.get(key);

        if (res == null) {
            synchronized (getLock(key)) {
                res = cache.get(key);
                if(res == null) {
                    res = computeExpensive(key);
                    cache.put(key, res);
                    locks.remove(key);
                } else {
                    locks.remove(key);
                }
            }
        }
        return res;*/
    }

    private Object getLock(K key) {
        Object lock = locks.get(key);
        if (lock == null) {
            lock = new Object();
            Object oldLock = locks.putIfAbsent(key, lock);
            lock = oldLock == null ? lock : oldLock;
        }
        return lock;
    }

    private V computeExpensive(K key) {
        // Very expensive computation.
        return (V) new Object();
    }
}
