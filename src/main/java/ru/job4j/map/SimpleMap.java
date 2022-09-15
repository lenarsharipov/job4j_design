package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Map<K, V> {
    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if (count >= (LOAD_FACTOR * capacity)) {
            expand();
        }
        boolean rsl = false;
        if (key == null) {
            if (table[0] == null) {
                table[0] = new MapEntry<>(null, value);
                rsl = true;
                count++;
                modCount++;
            }
        } else {
            int index = indexFor(hash(key.hashCode()));
            if (table[index] == null) {
                table[index] = new MapEntry<>(key, value);
                rsl = true;
                count++;
                modCount++;
            }
        }
        return rsl;
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private void expand() {
        capacity = capacity * 2;
        MapEntry<K, V>[] tempTable = new MapEntry[capacity];
        for (MapEntry<K, V> bucket : table) {
            if (bucket != null) {
                if (bucket.key == null) {
                    tempTable[0] = bucket;
                } else {
                    int index = indexFor(hash(bucket.key.hashCode()));
                    tempTable[index] = bucket;
                }
            }
        }
        table = tempTable;
        modCount++;
    }

    @Override
    public V get(K key) {
        V val = null;
        if (key == null && table[0] != null && table[0].key == null) {
                val = table[0].value;
            } else if (key != null) {
                    int index = indexFor(key.hashCode());
                    if (table[index] != null
                            && table[index].key != null
                            && table[index].key.hashCode() == key.hashCode()
                            && table[index].key.equals(key)) {
                        val = table[index].value;
                    }
            }
        return val;
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        int index = key == null ? 0 : indexFor(hash(key.hashCode()));
        if (index == 0) {
            table[index] = null;
            rsl = true;
            modCount++;
            count--;
        } else if (table[index] != null
                    && table[index].key.hashCode() == key.hashCode()
                    && table[index].key.equals(key)) {
            rsl = true;
            table[index] = null;
            modCount++;
            count--;
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            private int index;
            private final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                while (index < capacity && table[index] == null) {
                    index++;
                }
                return index < capacity;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[index++].key;
            }
        };
    }

    private static class MapEntry<K, V> {
        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}