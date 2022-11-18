package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Employee {
    private final String name;
    private final int id;
    private final boolean retired;
    private final Stack stack;
    private final String[] positions;

    public Employee(String name, int id, boolean retired, Stack stack, String... positions) {
        this.name = name;
        this.id = id;
        this.retired = retired;
        this.stack = stack;
        this.positions = positions;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public boolean isRetired() {
        return retired;
    }

    public Stack getStack() {
        return stack;
    }

    public String[] getPositions() {
        return positions;
    }

    @Override
    public String toString() {
        return "Employee{"
                + "name='" + name + '\''
                + ", id=" + id
                + ", retired=" + retired
                + ", stack=" + stack
                + ", positions=" + Arrays.toString(positions)
                + '}';
    }

    public static void main(String[] args) {
        JSONObject jsonStack = new JSONObject(
                "{\"programmingLanguage\":\"Java\", "
                        + "\"buildTool\":\"Ant\", \"webFramework\":\"Java Servlet\"}"
        );

        List<String> list = new ArrayList<>();
        list.add("Java Junior Developer");
        JSONArray jsonPositions = new JSONArray(list);

        final Employee employee = new Employee("Tom", 1, true,
                new Stack("Java", "Maven", "Spring"),
                "Team Lead", "Java Senior Developer");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", employee.getName());
        jsonObject.put("id", employee.getId());
        jsonObject.put("retired", employee.isRetired());
        jsonObject.put("stack", jsonStack);
        jsonObject.put("positions", jsonPositions);

        System.out.println(jsonObject);
        System.out.println(new JSONObject(employee));
    }
}