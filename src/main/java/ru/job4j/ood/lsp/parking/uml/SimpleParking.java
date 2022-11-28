package ru.job4j.ood.lsp.parking.uml;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SimpleParking implements Parking {

    private final List<Vehicle> carsList = new ArrayList<>();
    private final List<Vehicle> trucksList = new ArrayList<>();
    private int cars;
    private int trucks;

    public SimpleParking(int cars, int trucks) {
        if (cars < 0 || trucks < 0) {
            throw new IllegalArgumentException("Illegal size values");
        }
        this.cars = cars;
        this.trucks = trucks;
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
        if (vehicle.getSize() == Car.SIZE && cars > 0) {
            carsList.add(vehicle);
            cars--;
            rsl = true;
        } else if (vehicle.getSize() > Car.SIZE && trucks > 0) {
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
