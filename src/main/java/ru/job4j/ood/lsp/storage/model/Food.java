package ru.job4j.ood.lsp.storage.model;

import java.util.Calendar;
import java.util.Objects;

public abstract class Food {

    private String name;
    private final Calendar createDate;
    private final Calendar expiryDate;
    private double price;
    private final double discount;

    public Food(String name,
                Calendar createDate,
                Calendar expiryDate,
                double price,
                double discount) {
        if (name == null
                || createDate == null
                || expiryDate == null
                || name.length() == 0
                || price < 0
                || discount < 0
                || discount > 100) {
            throw new IllegalArgumentException("Invalid arguments");
        }

        this.name = name;
        this.createDate = createDate;
        this.expiryDate = expiryDate;
        this.price = price;
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getCreateDate() {
        return createDate;
    }

    public Calendar getExpiryDate() {
        return expiryDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    @Override
    public String toString() {
        return String.format(
                "Food{name=%s, createDate=%s, expiryDate=%s, price=%s, discount=%s}",
                name, createDate, expiryDate, price, discount
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Food food = (Food) o;
        return Double.compare(food.price, price) == 0
                && Double.compare(food.discount, discount) == 0
                && Objects.equals(name, food.name)
                && Objects.equals(createDate, food.createDate)
                && Objects.equals(expiryDate, food.expiryDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, createDate, expiryDate, price, discount);
    }

}
