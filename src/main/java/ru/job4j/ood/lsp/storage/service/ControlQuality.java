package ru.job4j.ood.lsp.storage.service;

import ru.job4j.ood.lsp.storage.model.Food;
import ru.job4j.ood.lsp.storage.store.Store;

import java.util.Calendar;
import java.util.List;

public class ControlQuality {
    private static final int LIMIT_ONE = 25;
    private static final int LIMIT_TWO = 76;
    private static final int LIMIT_THREE = 100;
    private final List<Store> stores;

    public ControlQuality(List<Store> stores) {
        this.stores = stores;
    }

    public Store control(Food food) {
        Store store;
        var rsl = calculate(food);
        if (rsl < LIMIT_ONE) {
            store = getStore(food, 0);
        } else if (rsl < LIMIT_TWO) {
            store = getStore(food, 1);
        } else if (rsl < LIMIT_THREE) {
            food.setPrice(food.getPrice() * (1 - food.getDiscount() / 100));
            store = getStore(food, 1);
        } else {
            store = getStore(food, 2);
        }
        return store;
    }

    private Store getStore(Food food, int index) {
        var store = stores.get(index);
        store.add(food);
        return store;
    }

    private double calculate(Food food) {
        double exp = food.getExpiryDate().getTimeInMillis()
                - food.getCreateDate().getTimeInMillis();
        double now = Calendar.getInstance().getTimeInMillis()
                - food.getCreateDate().getTimeInMillis();
        return now / exp * 100;
    }
}
