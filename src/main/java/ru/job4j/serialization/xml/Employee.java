package ru.job4j.serialization.xml;

import java.util.Arrays;

public class Employee {
    private final String name;
    private final int id;
    private final boolean retired;
    private final String[] positions;
    private final Stack stack;

    public Employee(String name, int id, boolean retired, String[] positions, Stack stack) {
        this.name = name;
        this.id = id;
        this.retired = retired;
        this.positions = positions;
        this.stack = stack;
    }

    @Override
    public String toString() {
        return "Employee{"
                + "name='" + name + '\''
                + ", id=" + id
                + ", retired=" + retired
                + ", positions=" + Arrays.toString(positions)
                + ", stack=" + stack
                + '}';
    }
}
