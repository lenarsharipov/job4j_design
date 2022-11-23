package ru.job4j.ood.lsp.storage.service;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.storage.calculator.CalendarExpirationCalculator;
import ru.job4j.ood.lsp.storage.model.Milk;
import ru.job4j.ood.lsp.storage.store.Shop;
import ru.job4j.ood.lsp.storage.store.Store;
import ru.job4j.ood.lsp.storage.store.Trash;
import ru.job4j.ood.lsp.storage.store.Warehouse;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ControlQualityTest {


    @Test
    void whenAddMilkThenAddedToWarehouse() {
        var now = Calendar.getInstance();
        var produced = new GregorianCalendar(
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DATE) - 1,
                6, 0);
        var expired = new GregorianCalendar(
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DATE) + 6,
                6, 0);
        var milk = new Milk("молоко 3.2%", produced, expired, 45.90, 9.5);
        var expirationCalculator = new CalendarExpirationCalculator();
        var warehouse = new Warehouse(expirationCalculator);
        var shop = new Shop(expirationCalculator);
        var trash = new Trash(expirationCalculator);
        List<Store> stores = List.of(warehouse, shop, trash);
        var controlQuality = new ControlQuality(stores);
        var expectedStore = new Warehouse(expirationCalculator);
        expectedStore.add(milk);
        var result = controlQuality.check(milk);
        assertTrue(result instanceof Warehouse);
        assertThat(result.findBy(s -> true)).isEqualTo(expectedStore.findBy(s -> true));
    }

    @Test
    void whenAddExpiredMilkThenAddedToTrash() {
        var now = Calendar.getInstance();
        var produced = new GregorianCalendar(
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DATE) - 10,
                6, 0);
        var expired = new GregorianCalendar(
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DATE),
                6, 0);
        var milk = new Milk("молоко 3.2%", produced, expired, 45.90, 9.5);
        var expirationCalculator = new CalendarExpirationCalculator();
        var warehouse = new Warehouse(expirationCalculator);
        var shop = new Shop(expirationCalculator);
        var trash = new Trash(expirationCalculator);
        List<Store> stores = List.of(warehouse, shop, trash);
        var controlQuality = new ControlQuality(stores);
        var expectedStore = new Trash(expirationCalculator);
        expectedStore.add(milk);
        var result = controlQuality.check(milk);
        assertTrue(result instanceof Trash);
        assertThat(result.findBy(s -> true)).isEqualTo(expectedStore.findBy(s -> true));
    }

    @Test
    void whenAddMilkThenAddedToShopWithSamePrice() {
        var now = Calendar.getInstance();
        var produced = new GregorianCalendar(
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DATE) - 2,
                6, 0);
        var expired = new GregorianCalendar(
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DATE) + 6,
                6, 0);
        var milk = new Milk("молоко 3.2%", produced, expired, 45.90, 9.5);
        var expirationCalculator = new CalendarExpirationCalculator();
        var warehouse = new Warehouse(expirationCalculator);
        var shop = new Shop(expirationCalculator);
        var trash = new Trash(expirationCalculator);
        List<Store> stores = List.of(warehouse, shop, trash);
        var controlQuality = new ControlQuality(stores);
        var expectedStore = new Shop(expirationCalculator);
        expectedStore.add(milk);
        var result = controlQuality.check(milk);
        assertTrue(result instanceof Shop);
        assertThat(result.findBy(s -> true)).isEqualTo(expectedStore.findBy(s -> true));
    }

    @Test
    void whenAddMilkThenAddedToShopWithDiscount() {
        var now = Calendar.getInstance();
        var produced = new GregorianCalendar(
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DATE) - 2,
                6, 0);
        var expired = new GregorianCalendar(
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DATE) + 1,
                6, 0);
        var milk = new Milk("молоко 3.2%", produced, expired, 40, 10);
        var expirationCalculator = new CalendarExpirationCalculator();
        var warehouse = new Warehouse(expirationCalculator);
        var shop = new Shop(expirationCalculator);
        var trash = new Trash(expirationCalculator);
        List<Store> stores = List.of(warehouse, shop, trash);
        var controlQuality = new ControlQuality(stores);
        var expected = new Milk("молоко 3.2%", produced, expired, 36, 10);
        var result = controlQuality.check(milk);
        assertTrue(result instanceof Shop);
        assertThat(result.findBy(s -> true)).isEqualTo(List.of(expected));
    }

    @Test
    void whenAddMilkWithInvalidArgsThenException() {
        var now = Calendar.getInstance();
        var produced = new GregorianCalendar(
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DATE) - 2,
                6, 0);
        var expired = new GregorianCalendar(
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DATE) + 1,
                6, 0);
        assertThrows(IllegalArgumentException.class, () ->
                new Milk("молоко 3.2%", produced, expired, 40, 110)
        );
    }

}