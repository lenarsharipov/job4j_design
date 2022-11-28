package ru.job4j.ood.dip.violation;

import java.util.ArrayList;
import java.util.List;

public class Manager {
    List<String> employersList = new ArrayList<>();

    void addDeveloper(String name) {
        employersList.add(name);
    }

    void addDesigner(String name) {
        employersList.add(name);
    }

    void addTester(String name) {
        employersList.add(name);
    }
}