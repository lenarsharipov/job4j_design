package ru.job4j.ood.lsp.parking.uml;

import java.util.Objects;

public abstract class Vehicle {
    private final int size;

    public Vehicle() {
        this.size = 1;
    }

    public Vehicle(int size) {
        if (size < 2) {
            throw new IllegalArgumentException("Illegal size value");
        }
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Vehicle vehicle = (Vehicle) o;
        return size == vehicle.size;
    }

    @Override
    public int hashCode() {
        return Objects.hash(size);
    }

    @Override
    public String toString() {
        return "Vehicle{"
                + "size=" + size
                + '}';
    }

}
