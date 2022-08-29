package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int sizeIn;
    private int sizeOut;

    public T poll() {
        if (sizeIn == 0 && sizeOut == 0) {
            throw new NoSuchElementException();
        }

        if (sizeOut == 0) {
            while (sizeIn != 0) {
                sizeOut++;
                sizeIn--;
                out.push(in.pop());
            }
        }
        sizeOut--;
        return out.pop();
    }

    public void push(T value) {
        sizeIn++;
        in.push(value);
    }
}