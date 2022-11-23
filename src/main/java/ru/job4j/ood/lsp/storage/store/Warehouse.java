package ru.job4j.ood.lsp.storage.store;

import ru.job4j.ood.lsp.storage.calculator.ExpirationCalculator;
import ru.job4j.ood.lsp.storage.model.Food;

import java.util.Calendar;

public class Warehouse extends AbstractStore {
    public static final int TWENTY_FIVE_PERCENT_SHELF_LIFE = 25;

    private final ExpirationCalculator<Calendar> expirationCalculator;

    public Warehouse(ExpirationCalculator<Calendar> expirationCalculator) {
        this.expirationCalculator = expirationCalculator;
    }

    @Override
    protected boolean isNotExpired(Food food) {
        double shelfLife = expirationCalculator
                .calculateInPercent(food.getCreateDate(), food.getExpiryDate());
        return shelfLife < TWENTY_FIVE_PERCENT_SHELF_LIFE;
    }
}