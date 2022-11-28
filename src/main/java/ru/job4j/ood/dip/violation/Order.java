package ru.job4j.ood.dip.violation;

import ru.job4j.ood.dip.onlineshop.User;

public class Order {
    public void payOrder(User user, Order order) {
        if (user.getName().isEmpty()) {
            System.out.println("Get error with " + user + " " + order);
            throw new IllegalArgumentException("Invalid order");
        }
    }
}
