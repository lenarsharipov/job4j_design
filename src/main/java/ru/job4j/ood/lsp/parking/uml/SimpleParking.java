package ru.job4j.ood.lsp.parking.uml;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SimpleParking implements Parking {

    private final List<Vehicle> carsList;
    private final List<Vehicle> trucksList;
    private int cars;
    private int trucks;

    public SimpleParking(int cars, int trucks) {
        if (cars < 0 || trucks < 0) {
            throw new IllegalArgumentException("Illegal size values");
        }
        this.cars = cars;
        this.trucks = trucks;
        this.carsList = new ArrayList<>(cars);
        this.trucksList = new ArrayList<>(trucks);
    }

    @Override
    public boolean add(Vehicle vehicle) {
        return isAdded(vehicle);
    }

    @Override
    public List<Vehicle> findBy(Predicate<Vehicle> filter) {
        return Stream.concat(carsList.stream(), trucksList.stream())
                .filter(filter)
                .collect(Collectors.toList());
    }

    private boolean isAdded(Vehicle vehicle) {
        boolean rsl = false;
        if (vehicle.getSize() == Car.SIZE && cars >= Car.SIZE) {
            carsList.add(vehicle);
            cars--;
            rsl = true;
        } else if (vehicle.getSize() > Car.SIZE && trucks >= Car.SIZE) {
            trucks--;
            trucksList.add(vehicle);
            rsl = true;
        } else if (vehicle.getSize() > Car.SIZE && trucks == 0 && cars >= vehicle.getSize()) {
            cars = cars - vehicle.getSize();
            carsList.add(vehicle);
            rsl = true;
        }

        return rsl;
    }

}