package ru.job4j.ood.srp.report;

import com.google.gson.Gson;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.util.function.Predicate;

public class JsonReportEngine implements Report {
    private final Store store;
    private final Gson gson;

    public JsonReportEngine(Store store, Gson gson) {
        this.store = store;
        this.gson = gson;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder json = new StringBuilder();
        for (Employee employee : store.findBy(filter)) {
            json.append(gson.toJson(employee))
                    .append(System.lineSeparator());
        }
        return json.toString();
    }
}
