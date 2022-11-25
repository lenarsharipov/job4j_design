package ru.job4j.ood.lsp.parking.uml;

import java.util.Objects;

public class Truck extends Vehicle {
    private final int truckSize;

    public Truck(int size) {
        if (size <= Car.SIZE) {
            throw new IllegalArgumentException("not valid size");
        }
        this.truckSize = size;
    }

    public int getTruckSize() {
        return truckSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Truck truck = (Truck) o;
        return truckSize == truck.truckSize;
    }

    @Override
    public int hashCode() {
        return Objects.hash(truckSize);
    }

    @Override
    public String toString() {
        return "Truck{"
                + "truckSize=" + truckSize
                + '}';
    }
}
