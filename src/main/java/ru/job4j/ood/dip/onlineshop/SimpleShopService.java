package ru.job4j.ood.dip.onlineshop;

import org.apache.log4j.Logger;

import java.util.Optional;
import java.util.Set;

public class SimpleShopService {

    private static final Logger LOGGER = Logger.getLogger("Shop logger");
    private final ShopStore shopStore;
    private final OrderService orderService;

    public SimpleShopService(ShopStore shopStore, OrderService orderService) {
        this.shopStore = shopStore;
        this.orderService = orderService;
    }

    public boolean createBucket(User user) {
        return shopStore.saveUser(user);
    }

    public boolean createOrder(User user, Order order) {
        return shopStore.saveOrder(user, order);
    }

    public boolean addProduct(User user, Order order, Product product) {
        Optional<Order> orderDB = findOrder(user, order);
        if (orderDB.isEmpty()) {
            return false;
        }
        return orderService.add(product);
    }

    private Optional<Order> findOrder(User user, Order order) {
        return shopStore.getOrders(user).stream()
                .filter(o -> o.getId() == order.getId())
                .findFirst();
    }

    public boolean removeProduct(User user, Order order, Product product) {
        Optional<Order> orderDB = findOrder(user, order);
        if (orderDB.isEmpty()) {
            return false;
        }
        return orderService.remove(product.getId());
    }

    public boolean removeOrder(User user, Order order) {
        User u = shopStore.getUsers()
                        .stream()
                        .filter(user::equals)
                        .findFirst().get();
        Set<Order> orders = shopStore.getOrders(u);
        if (orders == null) {
            return false;
        }
        return orders.remove(order);
    }

    public Check payOrder(User user, Order order) {
        Optional<Order> orderDB = findOrder(user, order);
        if (orderDB.isEmpty()) {
            try {
                throw new IllegalArgumentException("Invalid order");
            } catch (IllegalArgumentException iae) {
                LOGGER.error(
                        String.format("Get error with %s %s", user, order),
                        iae
                );
            }
        }
        if (orderDB.get().isPaid()) {
            try {
                throw new IllegalArgumentException("Already paid!");
            } catch (IllegalArgumentException iae) {
                LOGGER.error(
                        String.format("Get error with %s %s", user, order),
                        iae
                );
            }
        }
        orderDB.get().setPaid(true);
        return new Check(
                (int) (System.currentTimeMillis() % 1_000_000),
                "Paid: " + orderDB.get().getId()
        );
    }

}
