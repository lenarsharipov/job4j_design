package ru.job4j.ood.lsp.parking.uml;

public class Truck extends Vehicle {
    private final int truckSize;

    public Truck(int size) {
        if (size <= Car.SIZE) {
            throw new IllegalArgumentException("not valid size");
        }
        this.truckSize = size;
    }

    @Override
    public int getSize() {
        return truckSize;
    }

}