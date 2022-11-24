package ru.job4j.ood.isp.ispviolation.calculator;

public interface Calculator {
    double add(double first, double second);
    double subtract(double first, double second);
    double divide(double first, double second);
    double multiply(double first, double second);
    double tan(double value);
    double sin(double value);
    double cos(double value);
    double pow(double value, double power);
}
