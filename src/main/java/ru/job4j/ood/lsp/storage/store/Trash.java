package ru.job4j.ood.lsp.storage.store;

import ru.job4j.ood.lsp.storage.calculator.ExpirationCalculator;
import ru.job4j.ood.lsp.storage.model.Food;

import java.util.Calendar;

public class Trash extends AbstractStore {
    private final ExpirationCalculator<Calendar> expirationCalculator;
    public static final int ONE_HUNDRED_PERCENT_SHELF_LIFE = 100;

    public Trash(ExpirationCalculator<Calendar> expirationCalculator) {
        this.expirationCalculator = expirationCalculator;
    }

    @Override
    protected boolean isNotExpired(Food food) {
        double shelfLife = expirationCalculator
                .calculateInPercent(food.getCreateDate(), food.getExpiryDate());
        return shelfLife >= ONE_HUNDRED_PERCENT_SHELF_LIFE;
    }
}