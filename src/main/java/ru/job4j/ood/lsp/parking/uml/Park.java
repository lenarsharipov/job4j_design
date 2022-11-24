package ru.job4j.ood.lsp.parking.uml;

import java.util.List;
import java.util.function.Predicate;

public interface Park {
    boolean add(Vehicle vehicle);
    List<Vehicle> findBy(Predicate<Vehicle> filter);
}
