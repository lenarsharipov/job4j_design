package ru.job4j.ood.lsp.storage.store;

import ru.job4j.ood.lsp.storage.model.Food;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class AbstractStore implements Store {
    private final List<Food> foods = new ArrayList<>();

    @Override
    public boolean add(Food food) {
        boolean rsl = false;
        if (isNotExpired(food)) {
            foods.add(food);
            rsl = true;
        }
        return rsl;
    }

    @Override
    public List<Food> findBy(Predicate<Food> filter) {
        return foods.stream()
                .filter(filter)
                .collect(Collectors.toList());
    }

    protected abstract boolean isNotExpired(Food food);

}
