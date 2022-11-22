package ru.job4j.ood.lsp.storage.calculator;

import java.util.Calendar;

public class CalendarExpirationCalculator implements ExpirationCalculator<Calendar> {
    @Override
    public double calculateInPercent(Calendar startDate, Calendar endDate) {
        double exp = endDate.getTimeInMillis()
                - startDate.getTimeInMillis();
        double now = Calendar.getInstance().getTimeInMillis()
                - startDate.getTimeInMillis();
        return now / exp * 100;
    }
}
