package net.h4bbo.echo.common.util.collections;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate; /**
 * Thread-safe wrapper for List operations
 */
public class SynchronizedList<T> {
    private final List<T> list;
    private final Object lock = new Object();

    public SynchronizedList() {
        this.list = new ArrayList<>();
    }

    public SynchronizedList(Collection<T> initialList) {
        this.list = new ArrayList<>(initialList);
    }

    /**
     * Add element to the end
     */
    public boolean add(T element) {
        synchronized (lock) {
            return list.add(element);
        }
    }

    /**
     * Add element at specific index
     */
    public void add(int index, T element) {
        synchronized (lock) {
            list.add(index, element);
        }
    }

    /**
     * Add all elements from collection
     */
    public boolean addAll(Collection<T> elements) {
        synchronized (lock) {
            return list.addAll(elements);
        }
    }

    /**
     * Add all elements at specific index
     */
    public boolean addAll(int index, Collection<T> elements) {
        synchronized (lock) {
            return list.addAll(index, elements);
        }
    }

    /**
     * Get element at index
     */
    public T get(int index) {
        synchronized (lock) {
            return list.get(index);
        }
    }

    /**
     * Set element at index
     */
    public T set(int index, T element) {
        synchronized (lock) {
            return list.set(index, element);
        }
    }

    /**
     * Remove element at index
     */
    public T remove(int index) {
        synchronized (lock) {
            return list.remove(index);
        }
    }

    /**
     * Remove first occurrence of element
     */
    public boolean remove(T element) {
        synchronized (lock) {
            return list.remove(element);
        }
    }

    /**
     * Remove all elements matching predicate
     */
    public void removeIf(Predicate<T> filter) {
        synchronized (lock) {
            list.removeIf(filter);
        }
    }

    /**
     * Check if list contains element
     */
    public boolean contains(T element) {
        synchronized (lock) {
            return list.contains(element);
        }
    }

    /**
     * Get index of first occurrence
     */
    public int indexOf(T element) {
        synchronized (lock) {
            return list.indexOf(element);
        }
    }

    /**
     * Get index of last occurrence
     */
    public int lastIndexOf(T element) {
        synchronized (lock) {
            return list.lastIndexOf(element);
        }
    }

    /**
     * Get the size of the list
     */
    public int size() {
        synchronized (lock) {
            return list.size();
        }
    }

    /**
     * Check if empty
     */
    public boolean isEmpty() {
        synchronized (lock) {
            return list.isEmpty();
        }
    }

    /**
     * Clear all elements
     */
    public void clear() {
        synchronized (lock) {
            list.clear();
        }
    }

    /**
     * Iterate over all elements (thread-safe)
     */
    public void forEach(Consumer<T> action) {
        synchronized (lock) {
            list.forEach(action);
        }
    }

    /**
     * Get a snapshot copy of the list
     */
    public List<T> toList() {
        synchronized (lock) {
            return new ArrayList<>(list);
        }
    }

    /**
     * Sort the list using natural ordering
     */
    public void sort() {
        synchronized (lock) {
            Collections.sort((List) list);
        }
    }

    /**
     * Sort the list using a comparator
     */
    public void sort(Comparator<? super T> comparator) {
        synchronized (lock) {
            list.sort(comparator);
        }
    }

    /**
     * Get a sublist (returns a snapshot copy)
     */
    public List<T> subList(int fromIndex, int toIndex) {
        synchronized (lock) {
            return new ArrayList<>(list.subList(fromIndex, toIndex));
        }
    }
}
