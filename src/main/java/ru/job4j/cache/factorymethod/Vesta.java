package ru.job4j.cache.factorymethod;

public class Vesta implements LadaVehicle {
    @Override
    public void drive() {
        System.out.println("speed of Vesta is up to 180km/h");
    }

    @Override
    public void fuel() {
        System.out.println("50lt of petrol");
    }
}