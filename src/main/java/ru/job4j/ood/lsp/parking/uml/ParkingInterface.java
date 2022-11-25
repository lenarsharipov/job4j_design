package ru.job4j.ood.lsp.parking.uml;

import java.util.List;
import java.util.function.Predicate;

public interface ParkingInterface {
    boolean add(Vehicle vehicle);
    List<Vehicle> findBy(Predicate<Vehicle> filter);

}
