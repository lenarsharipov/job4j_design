package ru.job4j.cache.factorymethod;

public class LadaMaker implements LadaCreator {
    @Override
    public LadaVehicle produceLada(String lada) {
        LadaVehicle rsl;
        if ("Vesta".equals(lada)) {
            rsl = new Vesta();
        } else if ("Niva".equals(lada)) {
            rsl = new Niva();
        } else {
            throw new IllegalArgumentException("Improper Lada model passed");
        }
        return rsl;
    }
}