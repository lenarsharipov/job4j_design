package ru.job4j.ood.lsp.parking.uml;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Parking implements ParkingInterface {

    private final List<Vehicle> carsList = new ArrayList<>();
    private final List<Vehicle> trucksList = new ArrayList<>();
    private final int cars;
    private final int trucks;

    public Parking(int cars, int trucks) {
        if (cars < 0 || trucks < 0) {
            throw new IllegalArgumentException("Illegal size values");
        }
        this.cars = cars;
        this.trucks = trucks;
    }

    @Override
    public boolean add(Vehicle vehicle) {
        return false;
    }

    @Override
    public List<Vehicle> findBy(Predicate<Vehicle> filter) {
        return new ArrayList<>();
    }

    private boolean isAdded(Vehicle vehicle) {
        return false;
    }

}
