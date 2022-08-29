package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int sizeIn;
    private int sizeOut;

    public T poll() {
        while (sizeIn > 0) {
            out.push(in.pop());
            sizeIn--;
            sizeOut++;
        }
        if (sizeOut == 0) {
            throw new NoSuchElementException();
        }
        sizeOut--;
        return out.pop();
    }

    public void push(T value) {
        while (sizeOut > 0) {
            sizeOut--;
            sizeIn++;
            in.push(out.pop());
        }
        sizeIn++;
        in.push(value);
    }
}