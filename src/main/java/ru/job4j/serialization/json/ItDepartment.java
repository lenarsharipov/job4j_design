package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ItDepartment {
    public static void main(String[] args) {
        final Employee employee = new Employee("Tom", 1, false,

                new Stack("Java", "Maven", "Spring"),
                "Java Middle Developer", "Java Junior Developer");

        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(employee));

        final String employeeJson =
                "{"
                        + "\"name\":Bob,"
                        + "\"id\":17,"
                        + "\"retired\":true,"
                        + "\"positions\":"
                        + "[\"Java Junior Developer\"],"
                        + "\"stack\":"
                        + "{"
                        + "\"pL\":\"Java\",\"buildTool\":\"Ant\",\"webFramework\":\"Spark\""
                        + "}"
                        + "}";
        final Employee employeeMod = gson.fromJson(employeeJson, Employee.class);
        System.out.println(employeeMod);
    }
}