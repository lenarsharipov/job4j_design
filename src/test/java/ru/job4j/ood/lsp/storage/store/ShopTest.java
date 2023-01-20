package ru.job4j.ood.lsp.storage.store;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.storage.calculator.CalendarExpirationCalculator;
import ru.job4j.ood.lsp.storage.model.Milk;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ShopTest {

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
        var shop = new Shop(expirationCalculator);
        assertTrue(shop.add(milk));
        assertThat(shop.findBy(s -> true)).isEqualTo(List.of(milk));
    }

    @Test
    void whenAddExpiredMilkThenNotAddedToShop() {
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
        var shop = new Shop(expirationCalculator);
        assertFalse(shop.add(milk));
        assertThat(shop.findBy(s -> true)).isNotEqualTo(List.of(milk));
    }

}