package ru.job4j.ood.lsp.storage.store;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.storage.calculator.CalendarExpirationCalculator;
import ru.job4j.ood.lsp.storage.model.Milk;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class TrashTest {

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
        var trash = new Trash(expirationCalculator);
        assertTrue(trash.add(milk));
        assertThat(trash.findBy(f -> true)).isEqualTo(List.of(milk));
    }

    @Test
    void whenAddFreshMilkThenNotAddedToTrash() {
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
        var trash = new Trash(expirationCalculator);
        assertFalse(trash.add(milk));
        assertThat(trash.findBy(f -> true)).isNotEqualTo(List.of(milk));
    }

}