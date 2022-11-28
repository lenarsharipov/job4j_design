package ru.job4j.ood.lsp.parking.uml;

public class Car extends Vehicle {
    public static final int SIZE = 1;

    public Car() {
        super();
    }

    @Override
    public int getSize() {
        return SIZE;
    }

}
