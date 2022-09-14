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
        if (count == (LOAD_FACTOR * capacity)) {
            expand();
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
        MapEntry<K, V>[] tempTable = new MapEntry[capacity * 2];
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
        capacity = capacity * 2;
        table = tempTable;
        modCount++;

    }

    @Override
    public V get(K key) {
        V val = null;
        if (count > 0) {
            if (key == null && table[0] != null && table[0].key == null) {
                val = table[0].value;
            } else if (key != null) {
                int hash = hash(key.hashCode());
                for (MapEntry<K, V> bucket : table) {
                    if (bucket != null && bucket.key != null
                            && hash == hash(bucket.key.hashCode())
                            && bucket.key.equals(key)) {
                        val = bucket.value;
                        break;
                    }
                }
            }
        }
        return val;
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        for (int i = 0; i < table.length; i++) {
            if (key == null) {
                table[i] = null;
                rsl = true;
                modCount++;
                count--;
                break;
            }
            if (table[i] != null
                    && hash(table[i].key.hashCode()) == hash(key.hashCode())
                    && table[i].key.equals(key)) {
                rsl = true;
                table[i] = null;
                modCount++;
                count--;
                break;
            }
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

                while (table[index] == null && index < capacity - 1) {
                    index++;
                }

                return index < capacity - 1;
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
