package ru.job4j.ood.lsp.storage.store;

import ru.job4j.ood.lsp.storage.calculator.ExpirationCalculator;
import ru.job4j.ood.lsp.storage.model.Food;

import java.util.Calendar;

public class Shop extends AbstractStore {
    public static final int SEVENTY_FIVE_PERCENT_SHELF_LIFE = 75;

    private final ExpirationCalculator<Calendar> expirationCalculator;

    public Shop(ExpirationCalculator<Calendar> expirationCalculator) {
        this.expirationCalculator = expirationCalculator;
    }

    @Override
    protected boolean isNotExpired(Food food) {
        double shelfLife = expirationCalculator
                .calculateInPercent(food.getCreateDate(), food.getExpiryDate());
        boolean rsl = false;
        if (shelfLife >= Warehouse.TWENTY_FIVE_PERCENT_SHELF_LIFE
                && shelfLife < Trash.ONE_HUNDRED_PERCENT_SHELF_LIFE) {
            if (shelfLife >= SEVENTY_FIVE_PERCENT_SHELF_LIFE) {
                food.setPrice(calculateDiscountedPrice(food));
            }
            rsl = true;
        }
        return rsl;
    }

    private double calculateDiscountedPrice(Food food) {
        return food.getPrice() - food.getPrice() / 100 * food.getDiscount();
    }
}