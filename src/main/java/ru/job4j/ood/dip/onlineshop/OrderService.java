package ru.job4j.ood.dip.onlineshop;

public interface OrderService {
    boolean add(Product product);
    boolean remove(int id);
    void clear();
}
