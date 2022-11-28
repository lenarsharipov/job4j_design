package ru.job4j.ood.lsp.parking.uml;

public abstract class Vehicle {

    abstract public int getSize();

    @Override
    public String toString() {
        return String.format(
                "%s{Size=%s}",
                this.getClass().getSimpleName(),
                getSize()
        );
    }

}
