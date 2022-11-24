package ru.job4j.ood.isp.ispviolation.calculator;

public class BasicCalc implements Calculator {

    @Override
    public double add(double first, double second) {
        return first + second;
    }

    @Override
    public double subtract(double first, double second) {
        return first - second;
    }

    @Override
    public double divide(double first, double second) {
        return first / second;
    }

    @Override
    public double multiply(double first, double second) {
        return first * second;
    }

    @Override
    public double tan(double value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public double sin(double value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public double cos(double value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public double pow(double value, double power) {
        throw new UnsupportedOperationException();
    }
}
