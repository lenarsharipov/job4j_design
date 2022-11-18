package ru.job4j.ood.lsp.storage.model;

import java.util.Calendar;
import java.util.Objects;

public class Milk extends Food {

    public Milk(String name,
                Calendar createDate,
                Calendar expiryDate,
                 double price,
                 double discount) {
        super(name, createDate, expiryDate, price, discount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Milk milk = (Milk) o;
        return Double.compare(milk.price, price) == 0
                && Double.compare(milk.discount, discount) == 0
                && Objects.equals(name, milk.name)
                && Objects.equals(createDate, milk.createDate)
                && Objects.equals(expiryDate, milk.expiryDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, createDate, expiryDate, price, discount);
    }
}
