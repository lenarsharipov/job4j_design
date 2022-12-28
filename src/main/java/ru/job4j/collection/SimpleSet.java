package ru.job4j.collection;

import java.util.Arrays;
import java.util.Objects;

/**
 * Функционал простого множества:
 * хранит только уникальные элементы
 * тип хранимых элементов – String
 * количество хранимых элементов не ограничено
 */

public class SimpleSet {
    private String[] container = new String[2];
    private int size = 0;

    private void grow() {
        container = Arrays.copyOf(container, container.length * 2);
    }

    private boolean contains(String value) {
        boolean result = false;
        for (String element : container) {
            if (Objects.equals(value, element)) {
                result = true;
                break;
            }
        }
        return result;
    }

    public boolean add(String value) {
        if (size == container.length) {
            grow();
        }
        boolean result = !contains(value);
        if (result) {
            container[size++] = value;
        }
        return result;
    }

}
