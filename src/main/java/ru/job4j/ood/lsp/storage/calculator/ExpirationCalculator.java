package ru.job4j.ood.lsp.storage.calculator;

public interface ExpirationCalculator<T> {
    double calculateInPercent(T startDate, T endDate);
}
