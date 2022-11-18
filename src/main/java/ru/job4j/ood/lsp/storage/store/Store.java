package ru.job4j.ood.lsp.storage.store;

import ru.job4j.ood.lsp.storage.model.Food;

import java.util.List;
import java.util.function.Predicate;

public interface Store {

    void add(Food food);

    List<Food> findBy(Predicate<Food> filter);

}