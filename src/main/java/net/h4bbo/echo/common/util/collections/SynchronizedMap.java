package net.h4bbo.echo.common.util.collections;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Predicate; /**
 * Thread-safe wrapper for Map/Dictionary operations
 */
public class SynchronizedMap<K, V> {
    private final Map<K, V> map;
    private final Object lock = new Object();

    public SynchronizedMap() {
        this.map = new HashMap<>();
    }

    public SynchronizedMap(Map<K, V> initialMap) {
        this.map = new HashMap<>(initialMap);
    }

    /**
     * Add or update a key-value pair
     */
    public void put(K key, V value) {
        synchronized (lock) {
            map.put(key, value);
        }
    }

    /**
     * Add all entries from another map
     */
    public void putAll(Map<K, V> entries) {
        synchronized (lock) {
            map.putAll(entries);
        }
    }

    /**
     * Get value by key
     */
    public V get(K key) {
        synchronized (lock) {
            return map.get(key);
        }
    }

    /**
     * Get value or default if key doesn't exist
     */
    public V getOrDefault(K key, V defaultValue) {
        synchronized (lock) {
            return map.getOrDefault(key, defaultValue);
        }
    }

    /**
     * Remove entry by key
     */
    public V remove(K key) {
        synchronized (lock) {
            return map.remove(key);
        }
    }

    /**
     * Remove entries that match the predicate
     */
    public void removeIf(Predicate<Map.Entry<K, V>> filter) {
        synchronized (lock) {
            map.entrySet().removeIf(filter);
        }
    }

    /**
     * Check if key exists
     */
    public boolean containsKey(K key) {
        synchronized (lock) {
            return map.containsKey(key);
        }
    }

    /**
     * Check if value exists
     */
    public boolean containsValue(V value) {
        synchronized (lock) {
            return map.containsValue(value);
        }
    }

    /**
     * Get the size of the dictionary
     */
    public int size() {
        synchronized (lock) {
            return map.size();
        }
    }

    /**
     * Check if empty
     */
    public boolean isEmpty() {
        synchronized (lock) {
            return map.isEmpty();
        }
    }

    /**
     * Clear all entries
     */
    public void clear() {
        synchronized (lock) {
            map.clear();
        }
    }

    /**
     * Iterate over all entries (thread-safe)
     */
    public void forEach(BiConsumer<K, V> action) {
        synchronized (lock) {
            map.forEach(action);
        }
    }

    /**
     * Get a snapshot copy of all keys
     */
    public Set<K> keySet() {
        synchronized (lock) {
            return new HashSet<>(map.keySet());
        }
    }

    /**
     * Get a snapshot copy of all values
     */
    public Collection<V> values() {
        synchronized (lock) {
            return new ArrayList<>(map.values());
        }
    }

    /**
     * Get a snapshot copy of all entries
     */
    public Set<Map.Entry<K, V>> entrySet() {
        synchronized (lock) {
            return new HashSet<>(map.entrySet());
        }
    }

    /**
     * Put if absent and return the value
     */
    public V putIfAbsent(K key, V value) {
        synchronized (lock) {
            return map.putIfAbsent(key, value);
        }
    }

    /**
     * Replace value if key exists
     */
    public V replace(K key, V value) {
        synchronized (lock) {
            return map.replace(key, value);
        }
    }
}
