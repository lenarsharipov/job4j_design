package ru.job4j.ood.isp.ispviolation.shape;

public class Circle implements Shape {

    @Override
    public String drawCircle() {
        return "circle";
    }

    @Override
    public String drawSquare() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String drawRectangle() {
        throw new UnsupportedOperationException();
    }
}
