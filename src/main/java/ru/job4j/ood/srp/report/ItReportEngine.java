package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;
import java.util.function.Predicate;

public class ItReportEngine implements Report {
    private final Store store;
    private final DateTimeParser<Calendar> dateTimeParser;
    private final String delimiter;

    public ItReportEngine(Store store, DateTimeParser<Calendar> dateTimeParser, String delimiter) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
        if (";".equals(delimiter) || ",".equals(delimiter)) {
            this.delimiter = delimiter;
        } else {
            throw new IllegalArgumentException("Passed delimiter invalid");
        }

    }
    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name").append(delimiter)
                .append("Hired").append(delimiter)
                .append("Fired").append(delimiter)
                .append("Salary").append(delimiter)
                .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(delimiter)
                    .append(dateTimeParser.parse(employee.getHired())).append(delimiter)
                    .append(dateTimeParser.parse(employee.getFired())).append(delimiter)
                    .append(employee.getSalary()).append(delimiter)
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
