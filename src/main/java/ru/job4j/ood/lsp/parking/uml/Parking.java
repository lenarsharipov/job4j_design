package ru.job4j.ood.lsp.parking.uml;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Parking implements Park {
    private final List<Vehicle> vehicles = new ArrayList<>();
    private final int cars;
    private final int lorries;

    public Parking(int cars, int lorries) {
        if (cars < 0 || lorries < 0) {
            throw new IllegalArgumentException("Illegal size values");
        }
        this.cars = cars;
        this.lorries = lorries;
    }

    @Override
    public boolean add(Vehicle vehicle) {
        return false;
    }

    @Override
    public List<Vehicle> findBy(Predicate<Vehicle> filter) {
        return null;
    }

    private boolean isAdded(Vehicle vehicle) {
        return false;
    }
}
