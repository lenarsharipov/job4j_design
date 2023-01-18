package ru.job4j.cache.factorymethod;

public class LadaMakerApp {
    public static void main(String[] args) {
        LadaMaker ladaMaker = new LadaMaker();
        LadaVehicle lada = ladaMaker.produceLada("Niva");
        lada.drive();
        lada.fuel();
    }
}
