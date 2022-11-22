package ru.job4j.ood.lsp.storage.service;

import ru.job4j.ood.lsp.storage.model.Food;
import ru.job4j.ood.lsp.storage.store.Store;

import java.util.List;

public class ControlQuality {
    private final List<Store> stores;

    public ControlQuality(List<Store> stores) {
        this.stores = stores;
    }

    public Store check(Food food) {
        Store rsl = null;
        for (Store store : stores) {
            if (store.add(food)) {
                rsl = store;
                break;
            }
        }
        return rsl;
    }

}
