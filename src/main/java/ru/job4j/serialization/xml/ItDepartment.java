package ru.job4j.serialization.xml;

public class ItDepartment {
    public static void main(String[] args) {
        final Employee employee = new Employee("Tom", 1, false,
                new String[] {"Java Middle Developer", "Java Junior Developer"},
                new Stack("Java", "Maven", "Spring"));
    }
}