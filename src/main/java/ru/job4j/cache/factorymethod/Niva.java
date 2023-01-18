package ru.job4j.cache.factorymethod;

public class Niva implements LadaVehicle {
    @Override
    public void drive() {
        System.out.println("speed of Niva is up to 150km/h");
    }

    @Override
    public void fuel() {
        System.out.println("40lt of petrol");
    }
}